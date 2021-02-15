package com.walab.coding.service;

import java.util.List;

import com.walab.coding.model.CodingSiteDTO;

public interface CodingSiteService {
	public int insertCodingSite(CodingSiteDTO dto);

	// Home Controller
	List<CodingSiteDTO> readCodingSite();
	
	public int updateCodingSite(CodingSiteDTO dto);

	public int deleteCodingSite(int seq);


}
