package com.project.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.project.entity.Student;
import com.project.mapper.StudentMapper;
import com.project.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Resource(name="studentMapper")
	private StudentMapper studentMapper;
	
	/**
	 * 学生登录
	 */
	@Override
	public Student login(Map<String, String> map) {
		return studentMapper.selectByNoAndPwd(map);
	}

}
