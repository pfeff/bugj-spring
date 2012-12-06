package com.mbpfefferle.bugj.web.bugs;

import static com.mbpfefferle.bugj.Deployments.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import com.mbpfefferle.bugj.model.Bug;
import com.mbpfefferle.bugj.web.BugsResource;
import com.mbpfefferle.bugj.web.ComponentScan;
import com.mbpfefferle.bugj.web.Initializer;
import com.mbpfefferle.bugj.web.MvcConfig;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class NewBugIT {

    private final BugsResource target = new BugsResource();
    private final HandlerAdapter adapter = new AnnotationMethodHandlerAdapter();
    private final MockHttpServletRequest request = new MockHttpServletRequest();
    private final MockHttpServletResponse response = new MockHttpServletResponse();

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

    @Test
    public void shouldPopulateFormWithEmptyBug() throws Exception {
        request.setMethod("GET");
        request.setRequestURI("/bugs/new");
        ModelAndView mav = adapter.handle(request, response, target);
        Bug actual = (Bug)mav.getModel().get("bug");
        assertThat(actual, not(nullValue()));
        assertThat(actual.getSynopsis(), is(nullValue()));
    }
}

