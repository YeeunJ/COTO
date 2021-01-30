package com.walab.coding.repository;

import java.util.List;

import com.walab.coding.model.RecomProblemDTO;
import com.walab.coding.model.RecommendDTO;

public interface RecommendDAO {

	public List<RecommendDTO> readRecom();
	
	public void readRecomCount(RecommendDTO recommend, int index);
	
	public List<RecomProblemDTO> readRecomProblems(int recomID);
	
	public int createRecomProblem(RecommendDTO recommend);
	
<<<<<<< HEAD
	public List<RecommendDTO> searchProblemByContents(String searchValue, String orderValue);
=======
	public int deleteRecom(int recomID);
>>>>>>> branch 'master' of https://github.com/YeeunJ/COTO.git
}
