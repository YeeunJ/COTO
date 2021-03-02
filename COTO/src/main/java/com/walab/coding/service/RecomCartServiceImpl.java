package com.walab.coding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walab.coding.model.RecomCartDTO;
import com.walab.coding.model.RecommendDTO;
import com.walab.coding.repository.RecomCartDAO;
import com.walab.coding.repository.RecomCommentDAO;
import com.walab.coding.repository.RecomCountDAO;
import com.walab.coding.repository.RecommendDAO;

@Service
public class RecomCartServiceImpl implements RecomCartService {
	
	@Autowired
	RecomCartDAO recomCartDAO;
	
	@Autowired
	RecommendDAO recommendDAO;
	
	@Autowired
	RecomCountDAO recomCountDAO;
	
	@Autowired
	RecomCommentDAO recomCommentDAO;
	
	@Override
	public void createRecomCart(RecomCartDTO cart) {
		
		recomCartDAO.createRecomCart(cart);
		
	}
	
	@Override
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
	public List<RecommendDTO> readCartByRecommend(String searchValue, String orderValue, int s_point, int list, int userID) {
		List<RecommendDTO> recomList = recommendDAO.readRecomByPage(searchValue, orderValue, s_point, list);
		
		//List<RecommendDTO> recomList = recommendDAO.readRecommendList();
		List<RecommendDTO> myList = recomCartDAO.readCartRecommendList(userID);
		
	
		for(int i=0 ; i<recomList.size() ; i++) {
			recomList.get(i).setRecomCount(recomCountDAO.readRecomCount(recomList.get(i).getId()));
			
			int recomID = recomList.get(i).getId();
			recomList.get(i).setRecomCommentCount(recomCommentDAO.readRecomCommentCount(recomID));
			for(int j=0 ; j<myList.size() ; j++) {
				int myCartID = myList.get(j).getId();
				if( myCartID == recomID) {
					recomList.get(i).setUserCart(1);
					break;
				}
				else {
					recomList.get(i).setUserCart(0);
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
