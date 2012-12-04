package com.mbpfefferle.bugj.web;

import com.mbpfefferle.bugj.model.Bug;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/bugs")
public class BugsResource {

    @RequestMapping(value="/new", method=RequestMethod.GET)
    public ModelAndView form() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("bug/new");
        mav.addObject("command", new Bug());
        return mav;
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public String create(Bug bug) {
        return "redirect:1";
    }

    @RequestMapping(value="/{bugId}", method=RequestMethod.GET)
    public ModelAndView show(@PathVariable String bugId) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("bug/show");
        mav.addObject(bugId);
        return mav;
    }

}

