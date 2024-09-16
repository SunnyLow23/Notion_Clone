package com.sunnylow.notion_clone.dto;

import com.sunnylow.notion_clone.model.SharedPage;
import com.sunnylow.notion_clone.model.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SharedPageDTO {

	private Integer id;
	private UserRole role;
	private String email;
	private Integer pageId;
	private String emailAuthor;

	public static SharedPage toSharedPage(SharedPageDTO dto) {
		final SharedPage sharedPage = new SharedPage();

		sharedPage.setRole(dto.getRole());

		return sharedPage;
	}

	public static SharedPageDTO toSharedPageDTO(SharedPage sharedPage) {
		return SharedPageDTO.builder()
				.id(sharedPage.getId())
				.role(sharedPage.getRole())
				.email(sharedPage.getUser().getEmail())
				.pageId(sharedPage.getPage().getId())
				.emailAuthor(sharedPage.getPage().getAuthor().getEmail())
				.build();
	}
}
