package com.sunnylow.notion_clone.controller;

import com.sunnylow.notion_clone.controller.api.TableBlockAPI;
import com.sunnylow.notion_clone.dto.TableBlockDTO;
import com.sunnylow.notion_clone.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TableBlockController implements TableBlockAPI {

	@Autowired
	private BlockService<TableBlockDTO> blockService;

	@Override
	public ResponseEntity<TableBlockDTO> createTable(TableBlockDTO dto) {
		return new ResponseEntity<>(blockService.save(dto), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<TableBlockDTO> updateTable(Integer id, TableBlockDTO dto) {
		return new ResponseEntity<>(blockService.update(id, dto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<TableBlockDTO> updatePosition(Integer id, Integer newPosition) {
		return new ResponseEntity<>(blockService.updatePosition(id, newPosition), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<TableBlockDTO>> getAllTables() {
		return new ResponseEntity<>(blockService.getAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<TableBlockDTO>> getAllTablesByPageId(Integer pageId) {
		return new ResponseEntity<>(blockService.getAllByPageId(pageId), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<TableBlockDTO> getTableById(Integer id) {
		return new ResponseEntity<>(blockService.getById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity deleteTableById(Integer id) {
		blockService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}
}
