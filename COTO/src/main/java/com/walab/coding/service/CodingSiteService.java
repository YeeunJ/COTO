package com.walab.coding.service;

import java.util.List;

import com.walab.coding.model.CodingSiteDTO;

public interface CodingSiteService {
	public int insertCodingSite(CodingSiteDTO dto);

	/**
	 * read the name of the coding sites
	 * usage: HomeController, ProblemlistController, MyproblemsController
	 */
	List<CodingSiteDTO> readCodingSite();
	
	List<CodingSiteDTO> readCodingSitebyYN();
	
	public int updateCodingSite(CodingSiteDTO dto);

	public int deleteCodingSite(int seq);


}
