package com.project.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.entity.Student;

@Service
public class StudentService extends BaseService<Student> {
	
	
	public Student login(Map<String, String> map){
		return dao.getEntity("paper.StudentMapper.login", map);
	}

}
