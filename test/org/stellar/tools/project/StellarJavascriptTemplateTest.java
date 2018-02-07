package org.stellar.tools.project;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StellarJavascriptTemplateTest {
    private StellarJavascriptTemplate template;
    @Before
    public void setUp() throws Exception {
        template = new StellarJavascriptTemplate();
    }


    @Test
    public void getGithubUserName() throws Exception {
        assertThat(template.getGithubUserName(), CoreMatchers.is("asebak"));
    }

    @Test
    public void getGithubRepositoryName() throws Exception {
        assertThat(template.getGithubRepositoryName(), CoreMatchers.is("stellar-js-sample-intellij"));
    }


}