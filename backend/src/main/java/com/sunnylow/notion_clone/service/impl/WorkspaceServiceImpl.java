package com.sunnylow.notion_clone.service.impl;

import com.sunnylow.notion_clone.dto.WorkspaceDTO;
import com.sunnylow.notion_clone.model.User;
import com.sunnylow.notion_clone.model.Workspace;
import com.sunnylow.notion_clone.repository.UserRepository;
import com.sunnylow.notion_clone.repository.WorkspaceRepository;
import com.sunnylow.notion_clone.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {

	@Autowired
	private WorkspaceRepository workspaceRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public WorkspaceDTO save(WorkspaceDTO dto) {
		Integer userId = dto.getUserId();

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("user not found"));

		Workspace workspace = WorkspaceDTO.toWorkspace(dto);
		workspace.setUser(user);
		workspace.setCreatedAt(LocalDate.now());
		workspace.setUpdatedAt(LocalDate.now());

		return WorkspaceDTO.toWorkspaceDTO(workspaceRepository.save(workspace));
	}

	@Override
	public WorkspaceDTO update(Integer id, WorkspaceDTO dto) {
		Workspace workspace = workspaceRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("workspace not found"));

		workspace.setName(dto.getName());
		workspace.setUpdatedAt(LocalDate.now());

		return WorkspaceDTO.toWorkspaceDTO(workspaceRepository.save(workspace));
	}

	@Override
	public List<WorkspaceDTO> getAll() {
		return workspaceRepository.findAll().stream()
				.map(WorkspaceDTO::toWorkspaceDTO).collect(Collectors.toList());
	}

	@Override
	public WorkspaceDTO getById(Integer id) {
		return workspaceRepository.findById(id)
				.map(WorkspaceDTO::toWorkspaceDTO)
				.orElseThrow(() -> new RuntimeException("workspace not found"));
	}

	@Override
	public List<WorkspaceDTO> getAllByUserId(Integer userId) {
		return workspaceRepository.findWorkspaceByUserId(userId).stream()
				.map(WorkspaceDTO::toWorkspaceDTO).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		Workspace workspace = workspaceRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("workspace not found"));

		workspaceRepository.delete(workspace);
	}
}
