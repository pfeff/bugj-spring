package com.mbpfefferle.bugj.web;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.Test;

import org.springframework.web.servlet.ModelAndView;

public class GenericResourceTest {

    private static final String RESOURCE_PATH = "foo";

    private final GenericResource<Object> target = new GenericResource() {
        protected String getResourcePath() {
            return RESOURCE_PATH;
        }
    };

    @Test
    public void testSanity() {
        assertThat(true, is(true));
    }

    @Test
    public void shouldCreateModelAndViewToShowResource() {
        Object foo = new Object();

        ModelAndView actual = target.show(foo);
        assertThat(actual.getViewName(), is(RESOURCE_PATH + "/show"));
        assertThat(actual.getModel(), hasKey(RESOURCE_PATH));
        assertThat(actual.getModel().get(RESOURCE_PATH), not(nullValue()));

    }

}

