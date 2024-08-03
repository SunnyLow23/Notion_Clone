package com.sunnylow.notion_clone.service.impl;

import com.sunnylow.notion_clone.dto.TodoBlockDTO;
import com.sunnylow.notion_clone.exception.EntityNotFoundException;
import com.sunnylow.notion_clone.exception.ErrorCode;
import com.sunnylow.notion_clone.model.Page;
import com.sunnylow.notion_clone.model.TodoBlock;
import com.sunnylow.notion_clone.model.User;
import com.sunnylow.notion_clone.repository.PageRepository;
import com.sunnylow.notion_clone.repository.TodoRepository;
import com.sunnylow.notion_clone.repository.UserRepository;
import com.sunnylow.notion_clone.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoBlockServiceImpl implements BlockService<TodoBlockDTO> {

	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private PageRepository pageRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public TodoBlockDTO save(TodoBlockDTO dto) {
		User user = userRepository.findById(dto.getCreatedById())
				.orElseThrow(() -> new EntityNotFoundException(
						"User not found with ID = " + dto.getCreatedById(),
						ErrorCode.USER_NOT_FOUND
				));
		Page page = pageRepository.findById(dto.getPageId())
				.orElseThrow(() -> new EntityNotFoundException(
						"Page not found with ID = " + dto.getPageId(),
						ErrorCode.PAGE_NOT_FOUND
				));

		TodoBlock todoBlock = TodoBlockDTO.toTodoBlock(dto);
		todoBlock.setCreatedAt(LocalDate.now());
		todoBlock.setUpdatedAt(LocalDate.now());
		todoBlock.setPage(page);
		todoBlock.setCreatedBy(user);

		return TodoBlockDTO.toTodoBlockDTO(todoRepository.save(todoBlock));
	}

	@Override
	public TodoBlockDTO update(Integer id, TodoBlockDTO dto) {
		TodoBlock todoBlock = todoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"Todo Block not found with ID = " + id,
						ErrorCode.TODO_NOT_FOUND
				));

		todoBlock.setUpdatedAt(LocalDate.now());
		todoBlock.setContent(dto.getContent());
		todoBlock.setCompleted(dto.getCompleted());
		todoBlock.setDueDate(dto.getDueDate());

		return TodoBlockDTO.toTodoBlockDTO(todoRepository.save(todoBlock));
	}

	@Override
	public List<TodoBlockDTO> getAll() {
		return todoRepository.findAll().stream()
				.map(TodoBlockDTO::toTodoBlockDTO).collect(Collectors.toList());
	}

	@Override
	public TodoBlockDTO getById(Integer id) {
		return todoRepository.findById(id)
				.map(TodoBlockDTO::toTodoBlockDTO)
				.orElseThrow(() -> new EntityNotFoundException(
						"Todo Block not found with ID = " + id,
						ErrorCode.TODO_NOT_FOUND
				));
	}

	@Override
	public void delete(Integer id) {
		TodoBlock todoBlock = todoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"Todo Block not found with ID = " + id,
						ErrorCode.TODO_NOT_FOUND
				));

		todoRepository.delete(todoBlock);
	}
}
