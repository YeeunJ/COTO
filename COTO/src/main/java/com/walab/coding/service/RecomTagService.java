package com.walab.coding.service;

import java.util.List;

import com.walab.coding.model.RecomTagDTO;

public interface RecomTagService {

	void createTag(List<RecomTagDTO> recomTags);
	
	public List<RecomTagDTO> readTagList();
	
	public List<RecomTagDTO> readProblemTag();
	
	public int deleteRecomTag(int recomID);
	
	public int updateTag(List<RecomTagDTO> rt);
	
	public List<RecomTagDTO> readTagByID(int recomID);
}
