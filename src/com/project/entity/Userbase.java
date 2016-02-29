package com.project.entity;

import java.util.Date;
   /**
    * userbase 实体类
    * Sat Jan 23 13:37:44 CST 2016 chenww
    */ 


public class Userbase{

	private String accountid;

	private String username;

	private String pwd;

	private String sex;

	private int age;

	private Date birthday;

	private int cityid;

	private String address;

	private String tel;

	private String role;

	private int counts;

	private Date prelogintime;

	private Date updatetime;

	public void setAccountid(String accountid){
		this.accountid=accountid;

	}
	public String getAccountid(){
		return accountid;

	}
	public void setUsername(String username){
		this.username=username;

	}
	public String getUsername(){
		return username;

	}
	public void setPwd(String pwd){
		this.pwd=pwd;

	}
	public String getPwd(){
		return pwd;

	}
	public void setSex(String sex){
		this.sex=sex;

	}
	public String getSex(){
		return sex;

	}
	public void setAge(int age){
		this.age=age;

	}
	public int getAge(){
		return age;

	}
	public void setBirthday(Date birthday){
		this.birthday=birthday;

	}
	public Date getBirthday(){
		return birthday;

	}
	public void setCityid(int cityid){
		this.cityid=cityid;

	}
	public int getCityid(){
		return cityid;

	}
	public void setAddress(String address){
		this.address=address;

	}
	public String getAddress(){
		return address;

	}
	public void setTel(String tel){
		this.tel=tel;

	}
	public String getTel(){
		return tel;

	}
	public void setRole(String role){
		this.role=role;

	}
	public String getRole(){
		return role;

	}
	public void setCounts(int counts){
		this.counts=counts;

	}
	public int getCounts(){
		return counts;

	}
	public void setPrelogintime(Date prelogintime){
		this.prelogintime=prelogintime;

	}
	public Date getPrelogintime(){
		return prelogintime;

	}
	public void setUpdatetime(Date updatetime){
		this.updatetime=updatetime;

	}
	public Date getUpdatetime(){
		return updatetime;

	}
}

