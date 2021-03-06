package com.walab.coding.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.walab.coding.model.CodingSiteDTO;

@Repository
public class CodingSiteDAOImpl implements CodingSiteDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/**
	 * Codingsite Controller
	 */
	public int insertCodingSite(CodingSiteDTO dto) {
		int result = sqlSession.insert("CodingSite.insertCodingSite", dto);
		return result;
	}
	/**
	 * Codingsite Controller
	 */
	@Override
	public List<CodingSiteDTO> readCodingSite() {
		List<CodingSiteDTO> codingSitelist = new ArrayList<CodingSiteDTO>();
		codingSitelist = sqlSession.selectList("CodingSite.readCodingSite");

		return codingSitelist;
	}
	public List<CodingSiteDTO> readCodingSitebyYN() {
		List<CodingSiteDTO> codingSitelist = new ArrayList<CodingSiteDTO>();
		codingSitelist = sqlSession.selectList("CodingSite.readCodingSitebyYN");

		return codingSitelist;
	}
	
	public CodingSiteDTO readCodingSiteById(int siteID) {
		Map<String, Object> codingSiteParam = new HashMap<String, Object>();
		codingSiteParam.put("siteID", siteID);
		return sqlSession.selectOne("CodingSite.readCodingSiteById", codingSiteParam);
	}
	/**
	 * Codingsite Controller
	 */
	public int updateCodingSite(CodingSiteDTO dto) {
		int result = sqlSession.update("CodingSite.updateCodingSite", dto);
		return result;
	}
	/**
	 * Codingsite Controller
	 */
	public int deleteCodingSite(int seq) {
		int result = sqlSession.delete("CodingSite.deleteCodingSite", seq);
		return result;
	}
	

}
