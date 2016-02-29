package com.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.project.entity.Paper;

@Repository("paperMapper")
public interface PaperMapper {

	@Select(value="select * from paper where studentno=#{studentno}")
	public List<Paper> selectByStudent(String studentno);
	
	public List<Paper> selectByTeacher();
	
}
