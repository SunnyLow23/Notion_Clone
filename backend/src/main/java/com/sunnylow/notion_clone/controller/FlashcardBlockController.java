package com.sunnylow.notion_clone.controller;

import com.sunnylow.notion_clone.controller.api.FlashcardBlockAPI;
import com.sunnylow.notion_clone.dto.FlashcardBlockDTO;
import com.sunnylow.notion_clone.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlashcardBlockController implements FlashcardBlockAPI {

	@Autowired
	private BlockService<FlashcardBlockDTO> blockService;

	@Override
	public ResponseEntity<FlashcardBlockDTO> createFlashcard(FlashcardBlockDTO dto) {
		return new ResponseEntity<>(blockService.save(dto), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<FlashcardBlockDTO> updateFlashcard(Integer id, FlashcardBlockDTO dto) {
		return new ResponseEntity<>(blockService.update(id, dto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<FlashcardBlockDTO>> getAllFlashcards() {
		return new ResponseEntity<>(blockService.getAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<FlashcardBlockDTO> getFlashcardById(Integer id) {
		return new ResponseEntity<>(blockService.getById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity deleteFlashcardById(Integer id) {
		blockService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}
}
