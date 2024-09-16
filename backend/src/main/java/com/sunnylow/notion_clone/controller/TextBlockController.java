package com.sunnylow.notion_clone.controller;

import com.sunnylow.notion_clone.controller.api.TextBlockAPI;
import com.sunnylow.notion_clone.dto.TextBlockDTO;
import com.sunnylow.notion_clone.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TextBlockController implements TextBlockAPI {

	@Autowired
	private BlockService<TextBlockDTO> blockService;


	@Override
	public ResponseEntity<TextBlockDTO> createText(TextBlockDTO dto) {
		return new ResponseEntity<>(blockService.save(dto), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<TextBlockDTO> updateText(Integer id, TextBlockDTO dto) {
		return new ResponseEntity<>(blockService.update(id, dto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<TextBlockDTO> updatePosition(Integer id, Integer newPosition) {
		return new ResponseEntity<>(blockService.updatePosition(id, newPosition), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<TextBlockDTO>> getAllTexts() {
		return new ResponseEntity<>(blockService.getAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<TextBlockDTO>> getAllTextsByPageId(Integer pageId) {
		return new ResponseEntity<>(blockService.getAllByPageId(pageId), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<TextBlockDTO> getTextById(Integer id) {
		return new ResponseEntity<>(blockService.getById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity deleteTextById(Integer id) {
		blockService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}
}
