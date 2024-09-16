package com.sunnylow.notion_clone.controller;

import com.sunnylow.notion_clone.controller.api.TodoBlockAPI;
import com.sunnylow.notion_clone.dto.TodoBlockDTO;
import com.sunnylow.notion_clone.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoBlockController implements TodoBlockAPI {

	@Autowired
	private BlockService<TodoBlockDTO> blockService;

	@Override
	public ResponseEntity<TodoBlockDTO> createTodo(TodoBlockDTO dto) {
		return new ResponseEntity<>(blockService.save(dto), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<TodoBlockDTO> updateTodo(Integer id, TodoBlockDTO dto) {
		return new ResponseEntity<>(blockService.update(id, dto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<TodoBlockDTO> updatePosition(Integer id, Integer newPosition) {
		return new ResponseEntity<>(blockService.updatePosition(id, newPosition), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<TodoBlockDTO>> getAllTodos() {
		return new ResponseEntity<>(blockService.getAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<TodoBlockDTO>> getAllTodosByPageId(Integer pageId) {
		return new ResponseEntity<>(blockService.getAllByPageId(pageId), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<TodoBlockDTO> getTodoById(Integer id) {
		return new ResponseEntity<>(blockService.getById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity deleteTodoById(Integer id) {
		blockService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}
}
