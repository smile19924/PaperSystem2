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

import com.project.entity.Student;

@Repository(value="studentMapper")
public interface StudentMapper {

	@Results(value={@Result(id=true,property="",column=""),
			@Result(property="",column="")})
	public List<Student> operateReturnBeans(@Param(value="sql") String sql);
	
	@Select(value="select * from student where studentno=#{no} and password=#{password}")
	public Student selectByNoAndPwd(Map<String, String> maps);
	
	@Insert(value="INSERT INTO student (studentno, studentname, sex, telephone, password, department, major, classes, teacherno) "+
	"VALUES (#{studentno}, #{studentname}, #{sex}, #{telephone}, #{password}, #{department}, #{major}, #{classes}, #{teacherno})")
	public int insert(Student student);
	
	@Delete(value="delete from student where studentno=#{studentno}")
	public void delete(String studentno);
	
	@Update(value="UPDATE student SET studentno=#{studentno}, studentname=#{studentname}, sex=#{sex}, telephone=#{telephone}, "+
	"password=#{password}, department=#{department}, major=#{major}, classes=#{classes}, teacherno=#{teacherno} WHERE studentno=#{studentno}")
	public int update(Student student);
	
	@Update(value="update student set teacherno=#{teacherno} where studentno=#{studentno}")
	public int addTeacher(String studentno);
	
}
