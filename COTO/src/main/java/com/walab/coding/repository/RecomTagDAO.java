package com.walab.coding.repository;

import java.util.List;

import com.walab.coding.model.RankDTO;
import com.walab.coding.model.RecomTagDTO;

public interface RecomTagDAO {

	void createTag(RecomTagDTO t);
	public List<RecomTagDTO> readTagOrderByCount();
}
