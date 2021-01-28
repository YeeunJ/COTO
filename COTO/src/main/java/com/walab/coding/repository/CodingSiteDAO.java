package com.walab.coding.repository;

import java.util.ArrayList;
import java.util.List;

import com.walab.coding.model.CodingSiteDTO;

public interface CodingSiteDAO {
	
	public int insertCodingSite(CodingSiteDTO dto);
	public int updateCodingSite(CodingSiteDTO dto);
	public int deleteCodingSite(int seq);
	public List<CodingSiteDTO> readCodingSite();
	
}
