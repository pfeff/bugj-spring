package com.mbpfefferle.bugj.web.bugs;

import static com.mbpfefferle.bugj.Deployments.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import com.mbpfefferle.bugj.model.Bug;
import com.mbpfefferle.bugj.service.BugService;
import com.mbpfefferle.bugj.web.BugsResource;
import com.mbpfefferle.bugj.web.ComponentScan;
import com.mbpfefferle.bugj.web.Initializer;
import com.mbpfefferle.bugj.web.MvcConfig;

import java.net.URL;

import org.jmock.Expectations;
import org.jmock.Mockery;

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

    private static final String BUG_ID = "37";

    private final HandlerAdapter adapter = new AnnotationMethodHandlerAdapter();
    private final MockHttpServletRequest request = new MockHttpServletRequest();
    private final MockHttpServletResponse response = new MockHttpServletResponse();

    private final Mockery context = new Mockery();
    private final BugService bugService = context.mock(BugService.class);

    private final BugsResource target = new BugsResource(bugService);

    @Test
    public void testSanity() {
        assertThat(true, is(true));
        assertThat(target, not(nullValue()));
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

    @Test
    public void shouldFindAndDisplayBug() throws Exception {
        final Bug expected = new Bug();

        context.checking(new Expectations() {{
            oneOf(bugService).find(BUG_ID); will(returnValue(expected));
        }});

        request.setMethod("GET");
        request.setRequestURI("/bugs/" + BUG_ID);
        ModelAndView mav = adapter.handle(request, response, target);
        Bug actual = (Bug)mav.getModel().get("bug");

        context.assertIsSatisfied();
        assertThat(actual, is(expected));
        assertThat(actual.getSynopsis(), is(nullValue()));
    }

    @Test
    public void shouldCreateBugFromFormPost() throws Exception {
        final Bug expected = new Bug();
        expected.setId(Integer.parseInt(BUG_ID));

        context.checking(new Expectations() {{
            oneOf(bugService).create(with(any(Bug.class))); will(returnValue(expected));
        }});

        request.setMethod("POST");
        request.setRequestURI("/bugs/create");
        ModelAndView mav = adapter.handle(request, response, target);

        context.assertIsSatisfied();
        assertThat(mav.getViewName(), is("redirect:" + BUG_ID));
    }

}

