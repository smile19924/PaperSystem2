package com.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.entity.Score;

@Service
public class ScoreService extends BaseService<Score> {
	
	public List<Score> selectScoreByStudent(String studentno) {
		return dao.getEntitys("", studentno);
	}
	
	public int insertScore(Score score) {
		return dao.addEntity("paper.ScoreMapper.insert", score);
	}

}
