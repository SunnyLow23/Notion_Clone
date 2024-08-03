package com.sunnylow.notion_clone.dto;

import com.sunnylow.notion_clone.model.TodoBlock;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TodoBlockDTO extends BlockDTO {

	private String content;
	private Boolean completed;
	private LocalDate dueDate;

	public static TodoBlock toTodoBlock(TodoBlockDTO dto) {
		final TodoBlock block = new TodoBlock();

		block.setType(dto.getType());
		block.setCreatedAt(dto.getCreatedAt());
		block.setUpdatedAt(dto.getUpdatedAt());
		block.setContent(dto.getContent());
		block.setCompleted(dto.getCompleted());
		block.setDueDate(dto.getDueDate());

		return block;
	}

	public static TodoBlockDTO toTodoBlockDTO(TodoBlock block) {
		final TodoBlockDTO dto = new TodoBlockDTO();

		dto.setId(block.getId());
		dto.setType(block.getType());
		dto.setCreatedAt(block.getCreatedAt());
		dto.setUpdatedAt(block.getUpdatedAt());
		dto.setPageId(block.getPage().getId());
		dto.setCreatedById(block.getCreatedBy().getId());
		dto.setContent(block.getContent());
		dto.setCompleted(block.getCompleted());

		return dto;
	}
}
