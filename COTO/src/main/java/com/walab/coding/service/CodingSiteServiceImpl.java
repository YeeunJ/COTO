package com.walab.coding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walab.coding.model.CodingSiteDTO;
import com.walab.coding.repository.CodingSiteDAO;

@Service
public class CodingSiteServiceImpl implements CodingSiteService {
	
	@Autowired
	CodingSiteDAO codingDAO;

	/**
	 * Codingsite Controller
	 */
	@Override
	public int insertCodingSite(CodingSiteDTO dto) {
		return codingDAO.insertCodingSite(dto);
	}
	/**
	 * RecommendController
	 * 
	 */
	@Override
	public List<CodingSiteDTO> readCodingSite() {
		List<CodingSiteDTO> result = codingDAO.readCodingSite();

		return result;
	}
	/**
	 * Codingsite Controller
	 */
	@Override
	public int updateCodingSite(CodingSiteDTO dto) {
		return codingDAO.updateCodingSite(dto);
	}
	/**
	 * Codingsite Controller
	 */
	@Override
	public int deleteCodingSite(int id) {
		return codingDAO.deleteCodingSite(id);
	}


	
	

}
