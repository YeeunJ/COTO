package com.walab.coding.service;

import java.util.List;

import com.walab.coding.model.CodingSiteDTO;

public interface CodingSiteService {
	
	public int insertCodingSite(CodingSiteDTO dto);

	public int deleteCodingSite(int seq);

	public int updateCodingSite(CodingSiteDTO dto);

	List<CodingSiteDTO> read();
}
