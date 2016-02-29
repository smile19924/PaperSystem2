package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.entity.Userbase;
import com.project.service.UserbaseService;

@Controller
@RequestMapping("/project/user")
public class UserbaseController {

	@Autowired
	private UserbaseService userbaseService;
	
	@RequestMapping("/all")
	public ModelAndView queryall () {
		ModelAndView mav = null;
		List<Userbase> lists = userbaseService.find();
		return mav;
	}
	
	@RequestMapping("/add")
	public ModelAndView addUser () {
		ModelAndView mav = null;
		Userbase userbase = new Userbase();
		userbase.setAccountid("hahaah");
		userbase.setUsername("wowo");
		userbase.setPwd("123456");
		userbaseService.insertUser(userbase);
		return mav;
	}
	
}
