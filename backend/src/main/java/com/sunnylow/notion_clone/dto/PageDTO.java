package com.sunnylow.notion_clone.dto;

import com.sunnylow.notion_clone.model.Page;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PageDTO {

	private Integer id;
	private String title;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	private Integer workspaceId;
	private Integer authorId;

	public static Page toPage(PageDTO dto) {
		final Page page = new Page();

		page.setTitle(dto.getTitle());
		page.setCreatedAt(dto.getCreatedAt());
		page.setUpdatedAt(dto.getUpdatedAt());

		return page;
	}

	public static PageDTO toPageDTO(Page page) {
		return PageDTO.builder()
				.id(page.getId())
				.title(page.getTitle())
				.createdAt(page.getCreatedAt())
				.updatedAt(page.getUpdatedAt())
				.workspaceId(page.getWorkspace().getId())
				.authorId(page.getAuthor().getId())
				.build();
	}
}
