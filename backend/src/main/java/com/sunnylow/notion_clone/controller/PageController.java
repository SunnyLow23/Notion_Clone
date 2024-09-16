package com.sunnylow.notion_clone.controller;

import com.sunnylow.notion_clone.controller.api.PageAPI;
import com.sunnylow.notion_clone.dto.PageDTO;
import com.sunnylow.notion_clone.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PageController implements PageAPI {

	@Autowired
	private PageService pageService;

	@Override
	public ResponseEntity<PageDTO> createPage(PageDTO dto) {
		return new ResponseEntity<>(pageService.save(dto), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<PageDTO> updatePage(Integer id, PageDTO dto) {
		return new ResponseEntity<>(pageService.update(id, dto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PageDTO> updatePageBackground(Integer id, PageDTO dto) {
		return new ResponseEntity<>(pageService.updateBackground(id, dto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<PageDTO>> getAllPages() {
		return new ResponseEntity<>(pageService.getAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PageDTO> getPageById(Integer id) {
		return new ResponseEntity<>(pageService.getById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<PageDTO>> getAllPagesByAuthorId(Integer authorId) {
		return new ResponseEntity<>(pageService.getAllByAuthorId(authorId), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<PageDTO>> getAllPagesByWorkspaceId(Integer workspaceId) {
		return new ResponseEntity<>(pageService.getAllByWorkspaceId(workspaceId), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<PageDTO>> getAllPagesByTagId(Integer tagId) {
		return new ResponseEntity<>(pageService.getAllByTagId(tagId), HttpStatus.OK);
	}

	@Override
	public ResponseEntity deletePageById(Integer id) {
		pageService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}
}
