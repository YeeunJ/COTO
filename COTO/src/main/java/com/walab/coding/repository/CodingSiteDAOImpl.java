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


	public int insertCodingSite(CodingSiteDTO dto) {
		int result = sqlSession.insert("CodingSite.insertCodingSite", dto);
		return result;
	}

	public int updateCodingSite(CodingSiteDTO dto) {
		int result = sqlSession.update("CodingSite.updateCodingSite", dto);
		return result;
	}

	public int deleteCodingSite(int seq) {
		int result = sqlSession.delete("CodingSite.deleteCodingSite", seq);
		return result;
	}
	@Override
	public List<CodingSiteDTO> readCodingSite() {
		List<CodingSiteDTO> codingSitelist = new ArrayList<CodingSiteDTO>();
		codingSitelist = sqlSession.selectList("CodingSite.readCodingSite");

		return codingSitelist;
	}
	
	public CodingSiteDTO readCodingSiteById(int siteID) {
		Map<String, Object> codingSiteParam = new HashMap<String, Object>();
		codingSiteParam.put("siteID", siteID);
		return sqlSession.selectOne("CodingSite.readCodingSiteById", codingSiteParam);
	}

}
