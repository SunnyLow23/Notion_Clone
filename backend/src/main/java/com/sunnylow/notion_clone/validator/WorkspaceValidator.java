package com.sunnylow.notion_clone.validator;

import com.sunnylow.notion_clone.dto.WorkspaceDTO;

import java.util.ArrayList;
import java.util.List;

public class WorkspaceValidator {

	public static List<String> validateWorkspace(WorkspaceDTO dto) {
		List<String> errors = new ArrayList<>();

		if (dto == null) {
			errors.add("Missing Name");
			return errors;
		}

		if (dto.getName() == null || dto.getName().isBlank()) {
			errors.add("Missing Name");
		}

		return errors;
	}
}
