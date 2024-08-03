package com.sunnylow.notion_clone.service;

import com.sunnylow.notion_clone.dto.WorkspaceDTO;

import java.util.List;

public interface WorkspaceService {

	WorkspaceDTO save(WorkspaceDTO workspaceDTO);

	WorkspaceDTO update(Integer id, WorkspaceDTO workspaceDTO);

	List<WorkspaceDTO> getAll();

	WorkspaceDTO getById(Integer id);

	List<WorkspaceDTO> getAllByUserId(Integer userId);

	void delete(Integer id);
}
