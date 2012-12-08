package com.mbpfefferle.bugj.web;

import java.lang.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@RequestMapping(value="/new", method=RequestMethod.GET)
public @interface New {}

