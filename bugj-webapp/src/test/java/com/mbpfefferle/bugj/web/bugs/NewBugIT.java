package com.mbpfefferle.bugj.web.bugs;

import static com.mbpfefferle.bugj.Deployments.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import com.mbpfefferle.bugj.model.Bug;
import com.mbpfefferle.bugj.web.BugsResource;
import com.mbpfefferle.bugj.web.ComponentScan;
import com.mbpfefferle.bugj.web.Initializer;
import com.mbpfefferle.bugj.web.MvcConfig;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.spring.integration.test.annotation.SpringWebConfiguration;

import org.jboss.shrinkwrap.api.spec.WebArchive;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

@RunWith(Arquillian.class)
@SpringWebConfiguration(servletName="dispatcher")
public class NewBugIT {

    @Autowired
    private BugsResource target;

    @Deployment
    @OverProtocol("Servlet 3.0")
    public static WebArchive createDeployment() {
        return bugWar();
    }

    @Test
    public void testSanity() {
        assertThat(true, is(true));
        assertThat(target, not(nullValue()));
    }

    @Test
    public void shouldCreateEmptyBugWhenParamMissing() {
        Bug emptyBug = target.populateBug("");
        assertThat(emptyBug.getSynopsis(), is(nullValue()));
    }
}

