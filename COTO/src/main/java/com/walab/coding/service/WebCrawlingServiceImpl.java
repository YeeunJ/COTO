package com.walab.coding.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walab.coding.model.CodingSiteDTO;
import com.walab.coding.model.ProblemDTO;
import com.walab.coding.repository.CodingSiteDAO;

@Service
public class WebCrawlingServiceImpl implements WebCrawlingService {
	
	@Autowired
	CodingSiteDAO codingSitedao;
	
	public List<ProblemDTO> crawlingBaekjoonByName(List<String> problem, int siteID) {
		
		CodingSiteDTO siteInfo = codingSitedao.readCodingSiteById(siteID);
		List<ProblemDTO> problemInfo = new ArrayList<ProblemDTO>();
		
		for(String prob: problem) {
			String url = siteInfo.getMappingUrl().replace("${problem}", prob);
			System.out.println(url);
			
			try {
				Connection.Response response = Jsoup.connect(url)
                        .method(Connection.Method.GET)
                        .execute();
				Document doc = response.parse();
				System.out.println("----------------------------------------->>>>>>>>>>>>>>>>>>>>>>>>>>===========");
				Elements elem = doc.select(".inner-results");
				System.out.println(doc);
				//System.out.println(elem.first().attr("h3"));
				System.out.println("----------------------------------------->>>>>>>>>>>>>>>>>>>>>>>>>>----------");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			ProblemDTO pd = new ProblemDTO();
			pd.setSiteID(siteID);
			pd.setSiteName(siteInfo.getSiteName());
			pd.setSiteUrl(siteInfo.getSiteUrl());
			problemInfo.add(pd);
		}
		
		return problemInfo;
	}
}
