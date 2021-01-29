package com.walab.coding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walab.coding.model.RecomTagDTO;
import com.walab.coding.repository.RecomTagDAO;

@Service
public class RecomTagServiceImpl implements RecomTagService {

	@Autowired
	RecomTagDAO tagDAO;

	@Override
	public void createTag(List<RecomTagDTO> recomTags) {
		for(RecomTagDTO t : recomTags) {
			tagDAO.createTag(t);
		}
	}

	@Override
	public List<RecomTagDTO> readTagList() {
		return tagDAO.readTagOrderByCount();
	}
}
