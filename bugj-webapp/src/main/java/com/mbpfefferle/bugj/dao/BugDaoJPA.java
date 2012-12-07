package com.mbpfefferle.bugj.dao;

import com.mbpfefferle.bugj.model.Bug;

import org.springframework.stereotype.Repository;

@Repository
public class BugDaoJPA extends AbstractJpaDao<Bug> implements BugDao {

    public BugDaoJPA() {
        super(Bug.class);
    }
}

