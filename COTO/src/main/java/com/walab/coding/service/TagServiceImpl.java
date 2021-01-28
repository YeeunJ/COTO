package com.walab.coding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walab.coding.model.TagDTO;
import com.walab.coding.repository.TagDAO;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	TagDAO tagDAO;

	@Override
	public void createTag(List<TagDTO> recomTags) {
		for(TagDTO t : recomTags) {
			tagDAO.createTag(t);
		}
	}
}
