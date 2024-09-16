package com.sunnylow.notion_clone.controller;

import com.sunnylow.notion_clone.controller.api.SharedPageAPI;
import com.sunnylow.notion_clone.dto.PageDTO;
import com.sunnylow.notion_clone.dto.SharedPageDTO;
import com.sunnylow.notion_clone.service.SharedPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SharedPageController implements SharedPageAPI {

	@Autowired
	private SharedPageService sharedPageService;

	@Override
	public ResponseEntity<SharedPageDTO> sharePage(SharedPageDTO dto) {
		return new ResponseEntity<>(sharedPageService.sharePage(dto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SharedPageDTO> unSharePage(SharedPageDTO dto) {
		return new ResponseEntity<>(sharedPageService.unSharePage(dto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<SharedPageDTO>> getAllSharedPages() {
		return new ResponseEntity<>(sharedPageService.getAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SharedPageDTO> getPageById(Integer id) {
		return new ResponseEntity<>(sharedPageService.getById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<PageDTO>> getAllSharedPagesByUserId(Integer userId) {
		return new ResponseEntity<>(sharedPageService.getAllPagesByUserId(userId), HttpStatus.OK);
	}
}
