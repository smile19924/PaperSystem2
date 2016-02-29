package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.entity.Userbase;
import com.project.mapper.UserbaseMapper;
import com.project.service.UserbaseService;

@Service
@Transactional
public class UserbaseServiceImpl implements UserbaseService {

	@Resource(name="userbaseMapper")
	private UserbaseMapper userbaseMapper;
	
	@Override
	public List<Userbase> find() {
		// TODO Auto-generated method stub
		String sql = "select * from userbase";
		List<Userbase> lists = userbaseMapper.operateReturnBeans(sql);
		return lists;
	}
	
	@Override
	public void insertUser(Userbase userbase) {
		userbaseMapper.insertUser(userbase);
	}

}
