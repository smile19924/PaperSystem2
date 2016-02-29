package com.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.project.entity.Userbase;

@Repository(value="userbaseMapper")
public interface UserbaseMapper {

	/**
	 * 查询所有用户
	 * @param sql
	 * @return
	 */
	@Select(value = "${sql}")
	@Results(value = {@Result(id=true,property="accountid",column="accountid"),
			@Result(property="username",column="username"),
			@Result(property="tel",column="tel")})
	public List<Userbase> operateReturnBeans(@Param(value = "sql") String sql);
	
	/**
	 * 插入用户
	 * @param userbase
	 */
	@Insert(value = "insert into userbase(accountid,username, pwd) values (#{accountid},#{username},#{pwd})")
	public void insertUser(Userbase userbase);
	
	/**
	 * 更新用户
	 */
	@Update(value = "")
	public void updateUser();
}
