package com.walab.coding.repository;

import java.util.List;

import com.walab.coding.model.RankDTO;
import com.walab.coding.model.RecomTagDTO;

public interface RecomTagDAO {

	void createTag(RecomTagDTO t);
	
	public List<RecomTagDTO> readTagOrderByCount();
	
	public List<RecomTagDTO> readProblemTag();
	
	public int deleteRecomTag(int recomID);
	
	public int updateTag(RecomTagDTO rt);

	List<RecomTagDTO> readTagByID(int recomID);
}
