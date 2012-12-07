package com.mbpfefferle.bugj.service;

import com.mbpfefferle.bugj.dao.BugDao;
import com.mbpfefferle.bugj.model.Bug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BugServiceImpl implements BugService {

    private final BugDao bugDao;

    @Autowired
    public BugServiceImpl(BugDao bugDao) {
        this.bugDao = bugDao;
    }

    public Bug find(String id) {
        return bugDao.find(Integer.parseInt(id));
    }

}


