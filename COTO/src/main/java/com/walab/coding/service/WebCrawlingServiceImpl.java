package com.walab.coding.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walab.coding.model.CodingSiteDTO;
import com.walab.coding.model.ProblemDTO;
import com.walab.coding.repository.CodingSiteDAO;
import com.walab.coding.repository.ProblemDAO;

@Service
public class WebCrawlingServiceImpl implements WebCrawlingService {
	
	@Autowired
	CodingSiteDAO codingSitedao;
	
	@Autowired
	ProblemDAO problemdao;
	
	public List<ProblemDTO> crawlingBaekjoonByName(List<String> problem, int siteID) {
		
		CodingSiteDTO siteInfo = codingSitedao.readCodingSiteById(siteID);
		List<ProblemDTO> problemInfo = new ArrayList<ProblemDTO>();
		
		for(String prob: problem) {
			ProblemDTO pd = problemdao.readProblembyID(siteID, prob);
			
			if(pd == null) {
				pd = new ProblemDTO();
				String url = siteInfo.getMappingUrl().replace("${problem}", prob);
				System.out.println(url);
				System.out.println("----------------------------------------->>>>>>>>>>>>>>>>>>>>>>>>>>===========");
				try {
					Document doc = Jsoup.connect(url).get();
					System.out.println(prob+". "+doc.select("#problem_title").text());
					pd.setName(prob+". "+doc.select("#problem_title").text());
					pd.setLink(url);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("----------------------------------------->>>>>>>>>>>>>>>>>>>>>>>>>>----------");
				
				pd.setSiteID(siteID);
				pd.setSiteName(siteInfo.getSiteName());
				pd.setSiteUrl(siteInfo.getSiteUrl());
			}
			problemInfo.add(pd);
		}
		
		return problemInfo;
	}
}
