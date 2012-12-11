package com.mbpfefferle.bugj.web;

import java.lang.annotation.Annotation;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

public abstract class GenericResource<T> {

    protected String getResourcePath() {
        return getPath(findRequestMapping());
    }

    public ModelAndView show(T t) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(getResourcePath() + "/show");
        mav.addObject(getResourcePath(), t);
        return mav;
    }

    //"/bug/" + BUG_ID
    public String pathTo(String id) {
        return String.format("/%s/%s", getResourcePath(), id);
    }

    private RequestMapping findRequestMapping() {
        Annotation[] annotations = this.getClass().getAnnotations();
        for (Annotation a : annotations) {
            if (a instanceof RequestMapping) {
                return (RequestMapping)a;
            }
        }
        return null;
    }

    private String getPath(RequestMapping mappingAnnotation) {
        String[] values = mappingAnnotation.value();
        if (0 == values.length) { return ""; }

        return values[0].replaceFirst("^/", "");
    }
}

