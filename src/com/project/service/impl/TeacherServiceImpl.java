package com.project.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.project.entity.Teacher;
import com.project.mapper.TeacherMapper;
import com.project.service.BaseService;
import com.project.service.TeacherService;

@Service
public class TeacherServiceImpl extends BaseService<Teacher> implements TeacherService {
	
	@Resource(name="teacherMapper")
	private TeacherMapper teacherMapper;

	/**
	 * 老师登录
	 */
	@Override
	public Teacher login(Map<String, String> map) {
		return teacherMapper.selectByNoAndPwd(map);
	}
	

}
