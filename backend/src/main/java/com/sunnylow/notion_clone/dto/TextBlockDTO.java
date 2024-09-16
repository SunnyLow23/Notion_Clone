package com.sunnylow.notion_clone.dto;

import com.sunnylow.notion_clone.model.TextBlock;
import lombok.Data;

@Data
public class TextBlockDTO extends BlockDTO {

	private String content;

	public static TextBlock toTextBlock(TextBlockDTO dto) {
		final TextBlock block = new TextBlock();

		block.setType(dto.getType());
		block.setPosition(dto.getPosition());
		block.setContent(dto.getContent());

		return block;
	}

	public static TextBlockDTO toTextBlockDTO(TextBlock block) {
		final TextBlockDTO dto = new TextBlockDTO();

		dto.setId(block.getId());
		dto.setType(block.getType());
		dto.setPosition(block.getPosition());
		dto.setCreatedAt(block.getCreatedAt());
		dto.setUpdatedAt(block.getUpdatedAt());
		dto.setPageId(block.getPage().getId());
		dto.setCreatedById(block.getCreatedBy().getId());
		dto.setContent(block.getContent());

		return dto;
	}
}
