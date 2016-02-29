package com.project.service;

import java.util.List;

import com.project.entity.Score;

public interface ScoreService {
	
	public List<Score> selectScoreByStudent(String studentno);
	
	public int insertScore(Score score);

}
