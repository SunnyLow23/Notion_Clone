package com.sunnylow.notion_clone.dto;

import com.sunnylow.notion_clone.model.ImageBlock;
import lombok.Data;

@Data
public class ImageBlockDTO extends BlockDTO {

	private String imageUrl;
	private String caption;

	public static ImageBlock toImageBlock(ImageBlockDTO dto) {
		final ImageBlock block = new ImageBlock();

		block.setType(dto.getType());
		block.setCreatedAt(dto.getCreatedAt());
		block.setUpdatedAt(dto.getUpdatedAt());
		block.setImageUrl(dto.getImageUrl());
		block.setCaption(dto.getCaption());

		return block;
	}

	public static ImageBlockDTO toImageBlockDTO(ImageBlock block) {
		final ImageBlockDTO dto = new ImageBlockDTO();

		dto.setId(block.getId());
		dto.setType(block.getType());
		dto.setCreatedAt(block.getCreatedAt());
		dto.setUpdatedAt(block.getUpdatedAt());
		dto.setPageId(block.getPage().getId());
		dto.setCreatedById(block.getCreatedBy().getId());
		dto.setImageUrl(block.getImageUrl());
		dto.setCaption(block.getCaption());

		return dto;
	}
}
