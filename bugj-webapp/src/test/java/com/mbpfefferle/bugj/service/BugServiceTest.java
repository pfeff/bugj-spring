package com.mbpfefferle.bugj.service;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import com.mbpfefferle.bugj.dao.BugDao;
import com.mbpfefferle.bugj.model.Bug;

import org.jmock.Expectations;
import org.jmock.Mockery;

import org.junit.Test;

public class BugServiceTest {

    private static final int intId = 37;
    private static final String stringId = Integer.toString(intId);

    private final Mockery context = new Mockery();
    private final BugDao bugsDao = context.mock(BugDao.class);
    private final BugServiceImpl target = new BugServiceImpl(bugsDao);

    @Test
    public void testSanity() {
        assertThat(true, is(true));
        assertThat(target, not(nullValue()));
    }

    @Test
    public void shouldFindByQueryingDAO() {
        final Bug expected = new Bug();

        context.checking(new Expectations() {{
            oneOf(bugsDao).find(intId); will(returnValue(expected));
        }});

        Bug actual = this.target.find(stringId);
        context.assertIsSatisfied();

        assertThat(actual, is(expected));
    }


}

