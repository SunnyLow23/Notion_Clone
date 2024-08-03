package com.sunnylow.notion_clone.controller;

import com.sunnylow.notion_clone.controller.api.TagAPI;
import com.sunnylow.notion_clone.dto.TagDTO;
import com.sunnylow.notion_clone.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TagController implements TagAPI {

	@Autowired
	private TagService tagService;

	@Override
	public ResponseEntity<TagDTO> createTag(TagDTO dto) {
		return new ResponseEntity<>(tagService.save(dto), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<TagDTO> updateTag(Integer id, TagDTO dto) {
		return new ResponseEntity<>(tagService.update(id, dto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<TagDTO>> getAllTags() {
		return new ResponseEntity<>(tagService.getAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<TagDTO> getTagById(Integer id) {
		return new ResponseEntity<>(tagService.getById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity deleteTagById(Integer id) {
		tagService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}
}
