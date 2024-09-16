package com.sunnylow.notion_clone.service;

import com.sunnylow.notion_clone.dto.StaticNoteDTO;

import java.util.List;

public interface StaticNoteService {

	StaticNoteDTO save(StaticNoteDTO dto);

	List<StaticNoteDTO> getAll();

	StaticNoteDTO getById(Integer id);

	StaticNoteDTO getByPageId(Integer pageId);

	void delete(Integer id);
}
