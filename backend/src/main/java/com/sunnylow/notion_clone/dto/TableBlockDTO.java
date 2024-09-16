package com.sunnylow.notion_clone.dto;

import com.sunnylow.notion_clone.model.TableBlock;
import lombok.Data;

@Data
public class TableBlockDTO extends BlockDTO {

	private String tableData;

	public static TableBlock toTableBlock(TableBlockDTO dto) {
		final TableBlock block = new TableBlock();

		block.setType(dto.getType());
		block.setPosition(dto.getPosition());
		block.setTableData(dto.getTableData());

		return block;
	}

	public static TableBlockDTO toTableBlockDTO(TableBlock block) {
		final TableBlockDTO dto = new TableBlockDTO();

		dto.setId(block.getId());
		dto.setType(block.getType());
		dto.setPosition(block.getPosition());
		dto.setCreatedAt(block.getCreatedAt());
		dto.setUpdatedAt(block.getUpdatedAt());
		dto.setPageId(block.getPage().getId());
		dto.setCreatedById(block.getCreatedBy().getId());
		dto.setTableData(block.getTableData());

		return dto;
	}
}
