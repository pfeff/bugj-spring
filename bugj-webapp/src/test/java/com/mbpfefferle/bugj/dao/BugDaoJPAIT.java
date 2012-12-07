package com.mbpfefferle.bugj.dao;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import com.mbpfefferle.bugj.model.Bug;
import com.mbpfefferle.bugj.model.ExampleBugs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={com.mbpfefferle.bugj.dao.Config.class})
public class BugDaoJPAIT {

    @Autowired
    private BugDaoJPA target;

    @Test
    public void testSanity() {
        assertThat(true, is(true));
        assertThat(target, not(nullValue()));
    }

    @Test
    public void shouldFindById() {
        Bug basicBug = ExampleBugs.basicBug();
        target.save(basicBug);
        target.find(basicBug.getId());
    }
}

