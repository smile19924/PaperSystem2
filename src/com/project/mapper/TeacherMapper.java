package com.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.project.entity.Teacher;

@Repository(value="teacherMapper")
public interface TeacherMapper {
	
	@Select(value="${sql}")
	@Results(value = {@Result(id=true,property="teacherno",column="teacherno"),
			@Result(property="teachername",column="teachername"),
			@Result(property="sex",column="sex"),
			@Result(property="level",column="level"),
			@Result(property="telephone",column="telephone"),
			@Result(property="email",column="email"),
			@Result(property="papers",column="papers"),
			@Result(property="direction",column="direction"),
			@Result(property="department",column="department")})
	public List<Teacher> operateReturnBeans(@Param(value="sql") String sql);
	
	@Select(value="select * from teacher where teacherno=#{no} and password=#{password}")
	public Teacher selectByNoAndPwd(Map<String, String> map);

	@Insert(value="INSERT INTO teacher (teacherno, teachername, sex, level, telephone, email, papers, direction, department, password)"+
	" VALUES (#{teacherno}, #{teachername}, #{sex}, #{level}, #{telephone}, #{email}, #{papers}, #{direction}, #{department}, #{password})")
	public int insert(Teacher teacher);
	
	@Delete(value="delete from teacher where teacherno=#{teacherno}")
	public void delete(String teacherno);
	
	@Update(value="UPDATE teacher SET teacherno=#{teacherno}, teachername=#{teachername}, sex=#{sex}, level=#{level}, telephone=#{telephone}, "+
	"email=#{email}, papers=#{papers}, direction=#{direction}, department=#{department}, password=#{password} WHERE teacherno=#{teacherno}")
	public int update(Teacher teacher);
	
}
