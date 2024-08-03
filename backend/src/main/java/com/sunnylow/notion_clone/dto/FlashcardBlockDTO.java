package com.sunnylow.notion_clone.dto;

import com.sunnylow.notion_clone.model.FlashcardBlock;
import lombok.Data;

@Data
public class FlashcardBlockDTO extends BlockDTO {

	private String question;
	private String answer;

	public static FlashcardBlock toFlashcardBlock(FlashcardBlockDTO dto) {
		final FlashcardBlock block = new FlashcardBlock();

		block.setType(dto.getType());
		block.setCreatedAt(dto.getCreatedAt());
		block.setUpdatedAt(dto.getUpdatedAt());
		block.setQuestion(dto.getQuestion());
		block.setAnswer(dto.getAnswer());

		return block;
	}

	public static FlashcardBlockDTO toFlashcardBlockDTO(FlashcardBlock block) {
		final FlashcardBlockDTO dto = new FlashcardBlockDTO();

		dto.setId(block.getId());
		dto.setType(block.getType());
		dto.setCreatedAt(block.getCreatedAt());
		dto.setUpdatedAt(block.getUpdatedAt());
		dto.setPageId(block.getPage().getId());
		dto.setCreatedById(block.getCreatedBy().getId());
		dto.setQuestion(block.getQuestion());
		dto.setAnswer(block.getAnswer());

		return dto;
	}
}
