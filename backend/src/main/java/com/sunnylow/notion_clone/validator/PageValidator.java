package com.sunnylow.notion_clone.validator;

import com.sunnylow.notion_clone.dto.PageDTO;

import java.util.ArrayList;
import java.util.List;

public class PageValidator {

	public static List<String> validatePage(PageDTO dto) {
		List<String> errors = new ArrayList<>();

		if (dto == null) {
			errors.add("Missing Title");
			return errors;
		}

		if (dto.getTitle() == null || dto.getTitle().isBlank()) {
			errors.add("Missing Title");
		}

		return errors;
	}
}
