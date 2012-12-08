package com.mbpfefferle.bugj.web;

import org.springframework.web.servlet.ModelAndView;

public abstract class GenericResource<T> {

    protected abstract String getResourcePath();

    public ModelAndView show(T t) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(getResourcePath() + "/show");
        mav.addObject(getResourcePath(), t);
        return mav;
    }

}

