package com.sunnylow.notion_clone.controller;

import com.sunnylow.notion_clone.controller.api.JournalBlockAPI;
import com.sunnylow.notion_clone.dto.JournalBlockDTO;
import com.sunnylow.notion_clone.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JournalBlockController implements JournalBlockAPI {

	@Autowired
	private BlockService<JournalBlockDTO> blockService;

	@Override
	public ResponseEntity<JournalBlockDTO> createJournal(JournalBlockDTO dto) {
		return new ResponseEntity<>(blockService.save(dto), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<JournalBlockDTO> updateJournal(Integer id, JournalBlockDTO dto) {
		return new ResponseEntity<>(blockService.update(id, dto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<JournalBlockDTO>> getAllJournals() {
		return new ResponseEntity<>(blockService.getAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<JournalBlockDTO> getJournalById(Integer id) {
		return new ResponseEntity<>(blockService.getById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity deleteJournalById(Integer id) {
		blockService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}
}
