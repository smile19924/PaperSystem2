package com.project.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.entity.Teacher;

@Service
public class TeacherService extends BaseService<Teacher> {
	
	public Teacher login(Map<String, String> map){
		return dao.getEntity("paper.TeacherMapper.login", map);
	}
	
}
