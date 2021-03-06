package com.walab.coding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walab.coding.model.RecomCartDTO;
import com.walab.coding.model.RecommendDTO;
import com.walab.coding.repository.RecomCartDAO;
import com.walab.coding.repository.RecommendDAO;

@Service
public class RecomCartServiceImpl implements RecomCartService {
	
	@Autowired
	RecomCartDAO recomCartDAO;
	
	@Autowired
	RecommendDAO recommendDAO;
	
	@Override
	public void createRecomCart(RecomCartDTO cart) {
		
		recomCartDAO.createRecomCart(cart);
		
	}
	
	public List<RecommendDTO> readCartRecommendList(int userID){
		List<RecommendDTO> result = recomCartDAO.readCartRecommendList(userID);
		
		for(int i=0 ; i<result.size() ; i++) {
			int recomID = result.get(i).getId();
			int totalProbCnt = recomCartDAO.readTotalProbCnt(recomID);
			int userProbCnt = recomCartDAO.readUserProbCnt(userID,recomID);
			
			result.get(i).setTotalProbCnt(totalProbCnt);
			result.get(i).setUserProbCnt(userProbCnt);
			
			if(totalProbCnt == userProbCnt && totalProbCnt != 0) {
				RecommendDTO recom = result.get(i);
				result.remove(i);
				result.add(recom);
			}
			
		}
		
		
		return result;
	}
	
	@Override
	public int readCartByID(int recomID, int userID) {
		return recomCartDAO.readCartByID(recomID, userID);
	}
	
	@Override
	public List<RecommendDTO> readCartByRecommend(int userID) {
		
		List<RecommendDTO> recomList = recommendDAO.readRecommendList();
		List<RecommendDTO> myList = recomCartDAO.readCartRecommendList(userID);
	
		for(int i=0 ; i<recomList.size() ; i++) {
			for(int j=0 ; j<myList.size() ; j++) {
				int reID = recomList.get(i).getId();
				int myCartID = myList.get(j).getId();
				RecommendDTO recom = recomList.get(i);
				if( reID == myCartID) {
					recom.setUserCart(1);
				}
				else {
					recom.setUserCart(0);
				}
			}
		}

		return recomList;
	}

	@Override
	public void deleteRecomCart(RecomCartDTO cart) {
		
		recomCartDAO.deleteRecomCart(cart);
		
	}


}
