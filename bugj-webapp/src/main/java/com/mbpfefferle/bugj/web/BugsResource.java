package com.mbpfefferle.bugj.web;

import com.mbpfefferle.bugj.model.Bug;
import com.mbpfefferle.bugj.service.BugService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/bugs")
public class BugsResource {

    private final BugService bugService;

    @Autowired
    public BugsResource(BugService bugService) {
        this.bugService = bugService;
    }

    @RequestMapping(value="/new", method=RequestMethod.GET)
    public ModelAndView form() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("bug/new");
        mav.addObject(new Bug());
        return mav;
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public String create(Bug bug) {

        Bug created = this.bugService.create(bug);
        return "redirect:" + created.getId();
    }

    @RequestMapping(value="/{bugId}", method=RequestMethod.GET)
    public ModelAndView show(@PathVariable String bugId) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("bug/show");
        mav.addObject(bugId);
        mav.addObject(bugService.find(bugId));

        return mav;
    }

}

