package com.project.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.entity.Student;
import com.project.entity.Teacher;
import com.project.service.StudentService;
import com.project.service.TeacherService;
import com.project.util.ApplicationObject;

@Controller("/project/index")
public class IndexController {
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private StudentService studentService;
	
	/**
	 * 登录功能
	 * @param map
	 * @param request
	 * @param reponse
	 * @return
	 */
	@RequestMapping(value="login", method=RequestMethod.POST)
	public ModelAndView login(@RequestParam Map<String, String> map, HttpServletRequest request, HttpServletResponse reponse) {
		ModelAndView mav = new ModelAndView("index");
		String type = map.get("logintype");
		mav.addObject("logintype", type);
		if (ApplicationObject.TEACHER.equals(type)) {
			Teacher teacher = teacherService.login(map);
			if (null == teacher) {
				mav.setViewName("login");
				mav.addObject("error", "用户名或密码错误");
			} else {
				mav.addObject("teacher", teacher);
				request.getSession().setAttribute("teacher", teacher);
			}
		} else {
			Student student = studentService.login(map);
			if (null == student) {
				mav.setViewName("login");
				mav.addObject("error", "用户名或密码错误");
			} else {
				mav.addObject("student", student);
				request.getSession().setAttribute("student", student);
			}
		}
		return mav;
	}

}
