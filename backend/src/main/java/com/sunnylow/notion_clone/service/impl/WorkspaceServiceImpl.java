package com.sunnylow.notion_clone.service.impl;

import com.sunnylow.notion_clone.dto.WorkspaceDTO;
import com.sunnylow.notion_clone.exception.EntityNotFoundException;
import com.sunnylow.notion_clone.exception.ErrorCode;
import com.sunnylow.notion_clone.exception.InvalidEntityException;
import com.sunnylow.notion_clone.model.User;
import com.sunnylow.notion_clone.model.Workspace;
import com.sunnylow.notion_clone.repository.UserRepository;
import com.sunnylow.notion_clone.repository.WorkspaceRepository;
import com.sunnylow.notion_clone.service.WorkspaceService;
import com.sunnylow.notion_clone.validator.WorkspaceValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WorkspaceServiceImpl implements WorkspaceService {

	@Autowired
	private WorkspaceRepository workspaceRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public WorkspaceDTO save(WorkspaceDTO dto) {
		List<String> errors = WorkspaceValidator.validateWorkspace(dto);
		if (!errors.isEmpty()) {
			log.error(errors.toString());
			throw new InvalidEntityException(
					"Workspace is not valid",
					ErrorCode.WORKSPACE_NOT_VALID,
					errors
			);
		}

		User user = userRepository.findById(dto.getUserId())
				.orElseThrow(() -> new EntityNotFoundException(
						"User not found with ID = " + dto.getUserId(),
						ErrorCode.USER_NOT_FOUND
				));

		Workspace workspace = WorkspaceDTO.toWorkspace(dto);
		workspace.setUser(user);
		workspace.setCreatedAt(LocalDate.now());
		workspace.setUpdatedAt(LocalDate.now());
		workspace.setEditable(true);

		return WorkspaceDTO.toWorkspaceDTO(workspaceRepository.save(workspace));
	}

	@Override
	public WorkspaceDTO update(Integer id, WorkspaceDTO dto) {
		List<String> errors = WorkspaceValidator.validateWorkspace(dto);
		if (!errors.isEmpty()) {
			log.error(errors.toString());
			throw new InvalidEntityException(
					"Workspace is not valid",
					ErrorCode.WORKSPACE_NOT_VALID,
					errors
			);
		}

		Workspace workspace = workspaceRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"Workspace not found with ID = " + id,
						ErrorCode.WORKSPACE_NOT_FOUND
				));

		if (!workspace.isEditable()) {
			throw new InvalidEntityException(
					"Workspace is not valid",
					ErrorCode.WORKSPACE_NOT_VALID,
					errors
			);
		}

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
				.orElseThrow(() -> new EntityNotFoundException(
						"Workspace not found with ID = " + id,
						ErrorCode.WORKSPACE_NOT_FOUND
				));
	}

	@Override
	public List<WorkspaceDTO> getAllByUserId(Integer userId) {
		return workspaceRepository.findWorkspaceByUserId(userId).stream()
				.map(WorkspaceDTO::toWorkspaceDTO).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		Workspace workspace = workspaceRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"Workspace not found with ID = " + id,
						ErrorCode.WORKSPACE_NOT_FOUND
				));

		workspaceRepository.delete(workspace);
	}
}
