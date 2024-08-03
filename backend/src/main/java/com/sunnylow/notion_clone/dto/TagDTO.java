package com.sunnylow.notion_clone.dto;

import com.sunnylow.notion_clone.model.Tag;
import com.sunnylow.notion_clone.model.TagColor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TagDTO {

	private Integer id;
	private String name;
	private TagColor color;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	private Integer createdBy;

	public static Tag toTag(TagDTO dto) {
		final Tag tag = new Tag();

		tag.setName(dto.getName());
		tag.setColor(dto.getColor());
		tag.setCreatedAt(dto.getCreatedAt());
		tag.setUpdatedAt(dto.getUpdatedAt());

		return tag;
	}

	public static TagDTO toTagDTO(Tag tag) {
		return TagDTO.builder()
				.id(tag.getId())
				.name(tag.getName())
				.color(tag.getColor())
				.createdAt(tag.getCreatedAt())
				.updatedAt(tag.getUpdatedAt())
				.createdBy(tag.getCreatedBy().getId())
				.build();
	}
}
