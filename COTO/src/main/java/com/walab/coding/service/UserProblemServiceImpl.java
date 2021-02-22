package com.walab.coding.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walab.coding.model.CodingSiteDTO;
import com.walab.coding.model.RankDTO;
import com.walab.coding.model.UserProblemDTO;
import com.walab.coding.repository.UserProblemDAO;

@Service
public class UserProblemServiceImpl implements UserProblemService{
	
	@Autowired
	UserProblemDAO userProblemDAO;
	
	@Override
	public List<UserProblemDTO> read(int userID) {
		
		List<UserProblemDTO> problems = userProblemDAO.readAll(userID);
		
		for(UserProblemDTO problem: problems) {
			if(problem.getSite() == null) {
				String[]problemSplit = problem.getLink().trim().split("://|/|\\.");
				System.out.println(problemSplit.length + " : "+problem.getLink());
				
				if(problemSplit[problemSplit.length-1].compareTo("")!=0 && problemSplit[problemSplit.length-1]!= null) {
					problem.setProblem(problemSplit[problemSplit.length-1]);
				} else {
					problem.setProblem(problemSplit[problemSplit.length-2]);
				}
				
				if(problemSplit[0].compareTo("https") == 0|| problemSplit[0].compareTo("http") == 0) {
					problem.setSite(problemSplit[1]);
				} else {
					problem.setSite(problemSplit[0]);
				}
			}
		}
		return problems;
	}
	
	public int update(UserProblemDTO upd) {
		return userProblemDAO.updateProblem(upd);
	}
	
	/**
	 * RecommendController
	 * 
	 */
	public int delete(int userProblemID) {
		return userProblemDAO.deleteProblem(userProblemID);
	}
	
	@Override
	public int readSolvedP(int userID){
		int solvedP = userProblemDAO.readSolvedP(userID);
		
		return solvedP;	
	}
	
	@Override
	public List<RankDTO> readTotalRankList() {
		List<RankDTO> result = userProblemDAO.readTotalRankList();
		return result;
	}
	
	@Override
	public List<RankDTO> readTodayRankList() {
		List<RankDTO> result = userProblemDAO.readTodayRankList();
		return result;
	}

	@Override
	public void createUserProblem(List<UserProblemDTO> prob) {
		for(UserProblemDTO p : prob) {
			userProblemDAO.createUserProblem(p);
		}
	}
	
	/**
	 * RecommendController
	 * 
	 */
	public void createUserProblembyID(UserProblemDTO p) {
		userProblemDAO.createUserProblembyID(p);
	}
	
	@Override
	public List<UserProblemDTO> search(int userID, String searchValue) {
		searchValue = "%".concat(searchValue).concat("%");
		return userProblemDAO.searchProblemByContent(userID, searchValue);
	}

	@Override
	public List<UserProblemDTO> readProblemList() {
		return userProblemDAO.readProblemOrderByCount();
	}
	
	@Override
	public List<UserProblemDTO> countSolvedProblemEachDay(int userID){
		return userProblemDAO.countSolvedProblemEachDay(userID);
	}
	/**
	 * MyactivitiesController
	 */
	@Override
	public List<UserProblemDTO> readProblemActivities(int userID, int goalID){
		List<UserProblemDTO> problems = userProblemDAO.readProblemActivities(userID, goalID);
		
		for(UserProblemDTO problem: problems){
			if(problem.getSite() == null) {
				String[]problemSplit = problem.getLink().trim().split("://|/|\\.");
				System.out.println(problemSplit.length + " : "+problem.getLink());
				
				if(problemSplit[problemSplit.length-1].compareTo("")!=0 && problemSplit[problemSplit.length-1]!= null) {
					problem.setProblem(problemSplit[problemSplit.length-1]);
				}else {
					problem.setProblem(problemSplit[problemSplit.length-2]);
				}
				
				if(problemSplit[0].compareTo("https") == 0|| problemSplit[0].compareTo("http") == 0) {
					problem.setSite(problemSplit[1]);
				}else {
					problem.setSite(problemSplit[0]);
				}
			}
		}
		return problems;
	}

	@Override
	public List<Map<String, Object>> readAvgForaWeek() {
		
		List<Map<String,Object>> average = userProblemDAO.readAvgForaWeek();
		List<Map<String,Object>> avgWeek = new ArrayList<Map<String,Object>>();
		
		Date time = new Date();
		time = new Date(time.getTime()+(1000*60*60*24*-6));
		
		for(int i=0 ; i<7 ; i++) {
			Map<String,Object> map = new HashMap<String,Object>();
			SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy/MM/dd");
		
			String time1 = format1.format(time);
			Object date = (Object)time1;
			
			map.put("date", date);
			map.put("average", 0);
			
			for(int j=0 ; j<average.size(); j++) {

				if(time1.equals(average.get(j).get("date"))) {
					map.put("average", average.get(j).get("average"));
				}
			}
			time = new Date(time.getTime()+(1000*60*60*24*+1));

			avgWeek.add(map);
		}
		
		return avgWeek;
		
	}

	@Override
	public List<UserProblemDTO> readProblemByPage(int userID, int s_point, int list) {
		return userProblemDAO.readProblemByPage(userID, s_point, list);
	}

	@Override
	public int readProblemCnt(int userID) {
		return userProblemDAO.readProblemCnt(userID);
	}
	
	@Override
	public List<UserProblemDTO> readOtherUserPage(int userID){
		return userProblemDAO.readOtherUserPage(userID);
	}
}
