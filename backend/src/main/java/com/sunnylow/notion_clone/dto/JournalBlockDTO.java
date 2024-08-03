package com.sunnylow.notion_clone.dto;

import com.sunnylow.notion_clone.model.JournalBlock;
import lombok.Data;

import java.time.LocalDate;

@Data
public class JournalBlockDTO extends BlockDTO {

	private LocalDate journalDate;
	private String content;

	public static JournalBlock toJournalBlock(JournalBlockDTO dto) {
		final JournalBlock block = new JournalBlock();

		block.setType(dto.getType());
		block.setCreatedAt(dto.getCreatedAt());
		block.setUpdatedAt(dto.getUpdatedAt());
		block.setJournalDate(dto.getJournalDate());
		block.setContent(dto.getContent());

		return block;
	}

	public static JournalBlockDTO toJournalBlockDTO(JournalBlock block) {
		final JournalBlockDTO dto = new JournalBlockDTO();

		dto.setId(block.getId());
		dto.setType(block.getType());
		dto.setCreatedAt(block.getCreatedAt());
		dto.setUpdatedAt(block.getUpdatedAt());
		dto.setPageId(block.getPage().getId());
		dto.setCreatedById(block.getCreatedBy().getId());
		dto.setJournalDate(block.getJournalDate());
		dto.setContent(block.getContent());

		return dto;
	}
}
