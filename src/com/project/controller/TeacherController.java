package com.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.common.tabletool.MybatisDaoUtil;
import com.project.entity.Teacher;
import com.project.service.BaseService;
import com.project.service.TeacherService;

@Controller
@RequestMapping("/project/teacher")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	/*@Autowired
	private BaseService<Teacher> service;*/
	
	@ModelAttribute
	public void setClass() {
		teacherService.setEntityClass(Teacher.class);
	}
	
	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView mav = null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("teacherno", "2011123001");
		map.put("password", "888888");
		Teacher teacher = teacherService.login(map);
		return mav;
	}
	
	@RequestMapping("/look")
	public void look() {
//		service.setEntityClass(Teacher.class);
		Teacher teacher = teacherService.selectByPrimaryKey("2011123001");
//		Teacher teacher = dao.getEntity("paper.TeacherMapper.selectByPrimaryKey", "2011123001");
		System.out.println(teacher);
	}

}
