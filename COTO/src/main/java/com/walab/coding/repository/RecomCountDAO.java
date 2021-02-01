package com.walab.coding.repository;

import com.walab.coding.model.RecommendDTO;

public interface RecomCountDAO {

	public int readRecomCount(RecommendDTO recommend, int index);
}
