package com.mbpfefferle.bugj.web;

import com.mbpfefferle.bugj.model.Bug;
import com.mbpfefferle.bugj.service.BugService;

import org.apache.commons.lang3.StringUtils;

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

    @ModelAttribute
    public Bug populateBug(String bugId) {
        if (StringUtils.isEmpty(bugId)) {
            return new Bug();
        }
        return this.bugService.find(bugId);
    }

    @RequestMapping(value="/new", method=RequestMethod.GET)
    public ModelAndView form() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("bug/new");
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

        Bug bug = new Bug();
        bug.setSynopsis("fake synopsis");
        mav.addObject(bug);

        return mav;
    }

}

