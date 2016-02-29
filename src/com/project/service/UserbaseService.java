package com.project.service;

import java.util.List;

import com.project.entity.Userbase;

public interface UserbaseService {
	
	public List<Userbase> find();
	
	public void insertUser(Userbase userbase);

}
