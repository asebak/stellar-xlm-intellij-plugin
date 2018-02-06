package org.stellar.tools.project;

import com.google.common.io.Files;
import com.intellij.ide.util.projectWizard.*;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.module.JavaModuleType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.StdModuleTypes;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.DumbAwareRunnable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectType;
import com.intellij.openapi.projectRoots.JavaSdk;
import com.intellij.openapi.projectRoots.ProjectJdkTable;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.impl.JavaAwareProjectJdkTableImpl;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.testFramework.PsiTestUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.stellar.tools.localization.StellarBundle;
import org.stellar.tools.ui.StellarJavaModuleStep;
import org.stellar.tools.utils.FileUtilities;
import org.stellar.tools.utils.ProjectUtils;
import org.stellar.tools.utils.Template;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import static org.stellar.tools.utils.FileUtilities.SEPARATOR;

@Getter
@Setter
@NoArgsConstructor
public class StellarJavaModuleBuilder extends JavaModuleBuilder {
    private static final ProjectType PROJECT_TYPE = new ProjectType("STELLAR_JAVA");
    private static final String PROJECT_NAME = "Stellar";
    private String packageName;
    @Nullable
    private File[] jarsToAdd;
    private boolean noLibrariesNeeded;

    /**
     * Used to define the hierachy of the project definition
     *
     * @param rootModel
     * @throws ConfigurationException
     */
    @Override
    public void setupRootModel(final ModifiableRootModel rootModel) throws ConfigurationException {
        super.setupRootModel(rootModel);

        final Project project = rootModel.getProject();

        final VirtualFile root = createAndGetContentEntry();
        rootModel.addContentEntry(root);
//        final JavaSdk javaSdkType = JavaSdk.getInstance();
//        final ProjectJdkTable jdkTable = ProjectJdkTable.getInstance();
//        final Sdk sdkByName = jdkTable.findJdk(pathOrName, javaSdkType.getName());
        Sdk[] allJdks = ProjectJdkTable.getInstance().getAllJdks();
        if(allJdks.length > 0) {
            myJdk = allJdks[0];
        }
        if (myJdk != null) {
            rootModel.setSdk(myJdk);
        } else {
            rootModel.inheritSdk();
        }

        createProjectFiles(rootModel, project);

    }

    /**
     * Runs a new thread to create the required files
     *
     * @param rootModel
     * @param project
     */
    private void createProjectFiles(@NotNull final ModifiableRootModel rootModel, @NotNull final Project project) {
        ProjectUtils.runWhenInitialized(project, new DumbAwareRunnable() {
            public void run() {
//                ProjectRootManager instance = ProjectRootManager.getInstance(project);
//                Sdk projectSdk = instance.getProjectSdk();
//                rootModel.setSdk(projectSdk);
                String srcPath = project.getBasePath() + File.separator + "src";
                addJarFiles(rootModel.getModule());
                if(packageName == null){
                    packageName = "org.stellar.sample";
                }
                String[] directoriesToMake = packageName.split(Pattern.quote("."));
                for (String directory : directoriesToMake) {
                    try {
                        VfsUtil.createDirectories(srcPath + SEPARATOR + directory);
                    } catch (IOException e) {

                    }
                    srcPath += SEPARATOR + directory;
                }
                Template.builder().name(getMainClassTemplateName())
                        .classContext(this.getClass())
                        .outputFile(srcPath + SEPARATOR + "Main.java")
                        .data(new HashMap<String, Object>() {{
                            put("packagename", packageName);
                        }}).build()
                        .toFile();
            }
        });
    }

    /**
     * Creates directory for project
     * @return
     */
    private VirtualFile createAndGetContentEntry() {
        String path = FileUtil.toSystemIndependentName(getContentEntryPath());
        new File(path).mkdirs();
        return LocalFileSystem.getInstance().refreshAndFindFileByPath(path);
    }

    /**
     * Returns the Module type
     * @return
     */
    @Override
    public ModuleType getModuleType() {
        return StdModuleTypes.JAVA;
    }


    /**
     * Big icon is Java modules
     * @return
     */
    //@Override
    public Icon getBigIcon() {
        return IconLoader.findIcon("/stellarlogo.png");
    }

    /**
     * The icon displayed in the project creator dialog
     * @return
     */
    @Override
    public Icon getNodeIcon() {
        return IconLoader.findIcon("/stellarlogo.png");
    }

    /**
     * Build for module
     * @return
     */
    //@Override
    public String getBuilderId() {
        return getClass().getName();
    }

    /**
     * Module name
     * @return
     */
    @Override
    public String getPresentableName() {
        return PROJECT_NAME;
    }

    /**
     * Parent group in project creator dialog
     * @return
     */
    @Override
    public String getParentGroup() {
        return JavaModuleType.BUILD_TOOLS_GROUP;
    }

    /**
     * get weight
     * @return
     */
    @Override
    public int getWeight() {
        return JavaModuleBuilder.BUILD_SYSTEM_WEIGHT;
    }

    /**
     * Help description of the module
     * @return
     */
    @Override
    public String getDescription() {
        return StellarBundle.getString("app.project.description");
    }

    /**
     * Setup module with PI4J
     *
     * @param module
     * @throws ConfigurationException
     */
    @SneakyThrows(IOException.class)
    @Override
    protected void setupModule(Module module) throws ConfigurationException {
        super.setupModule(module);
        if(!noLibrariesNeeded) {
            final String libPath = module.getProject().getBasePath() + File.separator + "lib";
            VfsUtil.createDirectories(libPath);
            File outputFiles = new File(libPath);
//            FileUtilities.unzip(getClass().getResourceAsStream("/stellar-sdk.jar"), outputFiles.getAbsolutePath());

            InputStream initialStream = getClass().getResourceAsStream("/stellar-sdk.jar");
            byte[] buffer = new byte[initialStream.available()];
            initialStream.read(buffer);

            File targetFile = new File(outputFiles.getAbsolutePath()  + SEPARATOR + "stellar-sdk.jar");
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(buffer);

            jarsToAdd = outputFiles.listFiles();
        }
    }

    /**
     * Project Type
     * @return
     */
    @Override
    protected ProjectType getProjectType() {
        return PROJECT_TYPE;
    }

    private void addJarFiles(Module module) {
        if (jarsToAdd == null) {
            return;
        }

        for (final File fileEntry : jarsToAdd) {
            if (!fileEntry.isDirectory() && Files.getFileExtension(fileEntry.getName()).contains("jar")) {
                PsiTestUtil.addLibrary(module, fileEntry.getName(), fileEntry.getParentFile().getPath(), fileEntry.getName());
            }
        }
    }

    @Override
    public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext, @NotNull ModulesProvider modulesProvider) {
        return new ModuleWizardStep[]{new StellarJavaModuleStep(this)};
//        return ModuleWizardStep.EMPTY_ARRAY;
    }


    /**
     * Not Needed
     * @param list
     */
    @Override
    public void setSourcePaths(List<Pair<String, String>> list) {

    }

    /**
     * Not Needed
     * @param pair
     */
    @Override
    public void addSourcePath(Pair<String, String> pair) {

    }

    /**
     * gets file marker file name
     * @return
     */
    public String getMainClassTemplateName() {
        return "stellarmain.ftl";
    }


}
