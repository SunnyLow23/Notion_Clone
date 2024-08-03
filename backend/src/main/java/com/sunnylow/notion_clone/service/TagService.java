package com.sunnylow.notion_clone.service;

import com.sunnylow.notion_clone.dto.TagDTO;

import java.util.List;

public interface TagService {

	TagDTO save(TagDTO tagDTO);

	TagDTO update(Integer id, TagDTO tagDTO);

	List<TagDTO> getAll();

	TagDTO getById(Integer id);

	void delete(Integer id);
}
