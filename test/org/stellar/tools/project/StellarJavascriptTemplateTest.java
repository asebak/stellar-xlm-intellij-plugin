package org.stellar.tools.project;

import org.hamcrest.CoreMatchers;
import org.junit.*;

import static org.junit.Assert.*;

public class StellarJavascriptTemplateTest {
    private StellarJavascriptTemplate template;
    @Before
    public void setUp() throws Exception {
        template = new StellarJavascriptTemplate();
    }


    @Ignore
    public void getGithubUserName() throws Exception {
        assertThat(template.getGithubUserName(), CoreMatchers.is("asebak"));
    }

    @Ignore

    public void getGithubRepositoryName() throws Exception {
        assertThat(template.getGithubRepositoryName(), CoreMatchers.is("stellar-js-sample-intellij"));
    }


}