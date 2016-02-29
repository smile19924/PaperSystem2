package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.project.entity.Score;
import com.project.mapper.ScoreMapper;
import com.project.service.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService {

	@Resource(name="scoreMapper")
	private ScoreMapper scoreMapper;
	
	@Override
	public List<Score> selectScoreByStudent(String studentno) {
		return scoreMapper.selectScoreByStudent(studentno);
	}

	@Override
	public int insertScore(Score score) {
		return scoreMapper.insert(score);
	}

}
