package com.walab.coding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walab.coding.model.RankDTO;
import com.walab.coding.model.UserProblemDTO;
import com.walab.coding.repository.UserProblemDAO;

@Service
public class UserProblemServiceImpl implements UserProblemService{
	
	@Autowired
	UserProblemDAO userProblemDAO;
	
	@Override
	public List<UserProblemDTO> read(int userID){
		
		List<UserProblemDTO> problems = userProblemDAO.readAll(userID);
		
		for(UserProblemDTO problem: problems){
			
			//링크로 입력 받았을 경우 이름이 없으면 url을 나누어서 가장 마지막에 적힌 값으로 이름을 대체함
			//메인 페이지 하는 사람이 problem setting 하는 부분을 문제 등록 service 부분에다가 넣어두면 될 거 같음!!
			//일단은 site, siteurl 작업하면서 같이 해뒀어요~:)
			//거기서 부터 url을 나누어서 problem 값으로 넣어두게끔!! 이해 안 가면 연락주세요!!
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
	
	public int update(UserProblemDTO upd) {
		return userProblemDAO.updateProblem(upd);
	}
	
	public int delete(int userProblemID) {
		return userProblemDAO.deleteProblem(userProblemID);
	}
	
	@Override
	public int readSolvedP(int userID){
		int solvedP = userProblemDAO.readSolvedP(userID);
		
		return solvedP;	
	}
	
	@Override
	public List<RankDTO> readRankList() {
		List<RankDTO> result = userProblemDAO.readRankList();
		return result;
	}

	@Override
	public void createUserProblem(List<UserProblemDTO> prob) {
		for(UserProblemDTO p : prob) {
			userProblemDAO.createUserProblem(p);
		}
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
	
	@Override
	public List<UserProblemDTO> readProblemActivities(int goalID){
		List<UserProblemDTO> problems = userProblemDAO.readProblemActivities(goalID);
		
		for(UserProblemDTO problem: problems){
			
			//링크로 입력 받았을 경우 이름이 없으면 url을 나누어서 가장 마지막에 적힌 값으로 이름을 대체함
			//메인 페이지 하는 사람이 problem setting 하는 부분을 문제 등록 service 부분에다가 넣어두면 될 거 같음!!
			//일단은 site, siteurl 작업하면서 같이 해뒀어요~:)
			//거기서 부터 url을 나누어서 problem 값으로 넣어두게끔!! 이해 안 가면 연락주세요!!
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
}
