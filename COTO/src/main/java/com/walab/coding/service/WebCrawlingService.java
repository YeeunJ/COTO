package com.walab.coding.service;

import java.util.List;

import com.walab.coding.model.ProblemDTO;


public interface WebCrawlingService {
	public List<ProblemDTO> crawlingBaekjoonByName(List<String> problem, int siteID);
	public List<ProblemDTO> crawlingLeetcodeByName(List<String> problem, int siteID);
}
