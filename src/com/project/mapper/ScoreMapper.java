package com.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.project.entity.Score;

@Repository("scoreMapper")
public interface ScoreMapper {

	@Select(value="select sc.*,st.studentname,cr.coursename from score sc,student st,course cr where sc.studentn0=#{studentno}"+
	" and sc.studentno=st.studentno and sc.courseid=cr.courseid")
	public List<Score> selectScoreByStudent(String studentno);
	
	@Insert(value="insert into score(studentno,courseid,score) values(#{studentno},#{courseid},#{score})")
	public int insert(Score score);
	
}
