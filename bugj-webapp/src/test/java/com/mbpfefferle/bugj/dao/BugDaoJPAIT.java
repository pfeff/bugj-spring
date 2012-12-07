package com.mbpfefferle.bugj.dao;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import com.mbpfefferle.bugj.model.Bug;
import com.mbpfefferle.bugj.model.ExampleBugs;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={com.mbpfefferle.bugj.dao.Config.class})
public class BugDaoJPAIT {

    private static final int DOES_NOT_EXIST = Integer.MAX_VALUE;

    @Autowired
    private BugDaoJPA target;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void testSanity() {
        assertThat(true, is(true));
        assertThat(target, not(nullValue()));
    }

    @Test
    @Transactional
    public void shouldFindById() {
        Bug basicBug = ExampleBugs.basicBug();
        target.save(basicBug);
        em.flush();
        target.find(basicBug.getId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowExceptionWhenNotFound() {
        target.find(DOES_NOT_EXIST);
    }
}

