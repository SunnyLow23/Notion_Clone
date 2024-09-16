package com.sunnylow.notion_clone.service.impl;

import com.sunnylow.notion_clone.dto.BlockNoteDTO;
import com.sunnylow.notion_clone.dto.PageDTO;
import com.sunnylow.notion_clone.exception.EntityNotFoundException;
import com.sunnylow.notion_clone.exception.ErrorCode;
import com.sunnylow.notion_clone.exception.InvalidEntityException;
import com.sunnylow.notion_clone.model.*;
import com.sunnylow.notion_clone.repository.PageRepository;
import com.sunnylow.notion_clone.repository.SharedPageRepository;
import com.sunnylow.notion_clone.repository.UserRepository;
import com.sunnylow.notion_clone.repository.WorkspaceRepository;
import com.sunnylow.notion_clone.service.PageService;
import com.sunnylow.notion_clone.validator.PageValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PageServiceImpl implements PageService {

	@Autowired
	private PageRepository pageRepository;

	@Autowired
	private WorkspaceRepository workspaceRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SharedPageRepository sharedPageRepository;

	@Override
	public PageDTO save(PageDTO dto) {
		List<String> errors = PageValidator.validatePage(dto);
		if (!errors.isEmpty()) {
			log.error(errors.toString());
			throw new InvalidEntityException(
					"Page is not valid",
					ErrorCode.PAGE_NOT_VALID,
					errors
			);
		}

		User user = userRepository.findById(dto.getAuthorId())
				.orElseThrow(() -> new EntityNotFoundException(
						"User not found with ID = " + dto.getAuthorId(),
						ErrorCode.USER_NOT_FOUND
				));
		Workspace workspace = workspaceRepository.findById(dto.getWorkspaceId())
				.orElseThrow(() -> new EntityNotFoundException(
						"Workspace not found with ID = " + dto.getWorkspaceId(),
						ErrorCode.WORKSPACE_NOT_FOUND
				));

		Page page = PageDTO.toPage(dto);
		page.setWorkspace(workspace);
		page.setAuthor(user);
		page.setCreatedAt(LocalDate.now());
		page.setUpdatedAt(LocalDate.now());

		return PageDTO.toPageDTO(pageRepository.save(page));
	}

	@Override
	public PageDTO update(Integer id, PageDTO dto) {
		List<String> errors = PageValidator.validatePage(dto);
		if (!errors.isEmpty()) {
			log.error(errors.toString());
			throw new InvalidEntityException(
					"Page is not valid",
					ErrorCode.PAGE_NOT_VALID,
					errors
			);
		}

		Boolean isSharedPage = sharedPageRepository.existsByPageId(id);
		if (isSharedPage) {
			Page page = pageRepository.findById(id)
					.orElseThrow(() -> new EntityNotFoundException(
							"Page not found with id: " + id,
							ErrorCode.PAGE_NOT_FOUND
					));
			User user = userRepository.findById(dto.getAuthorId())
					.orElseThrow(() -> new EntityNotFoundException(
							"User not found with id: " + dto.getAuthorId(),
							ErrorCode.USER_NOT_FOUND
					));
			SharedPage sharedPage = sharedPageRepository.findByUserAndPage(user, page)
					.orElseThrow(() -> new EntityNotFoundException(
							"Current user does not have access to unshared this page",
							ErrorCode.PAGE_NOT_FOUND
					));

			if (!sharedPage.getRole().equals(UserRole.OWNER) &&
					!sharedPage.getRole().equals(UserRole.COLLABORATOR)) {
				return new PageDTO();
			}
		}

		Page page = pageRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("" +
						"Page not found with ID: " + id,
						ErrorCode.PAGE_NOT_FOUND
				));

		page.setTitle(dto.getTitle());
//		page.setBackground(dto.getBackground());
		page.setUpdatedAt(LocalDate.now());

		return PageDTO.toPageDTO(pageRepository.save(page));
	}

	@Override
	public PageDTO updateBackground(Integer id, PageDTO dto) {
		Boolean isSharedPage = sharedPageRepository.existsByPageId(id);
		if (isSharedPage) {
			Page page = pageRepository.findById(id)
					.orElseThrow(() -> new EntityNotFoundException(
							"Page not found with id: " + id,
							ErrorCode.PAGE_NOT_FOUND
					));
			User user = userRepository.findById(dto.getAuthorId())
					.orElseThrow(() -> new EntityNotFoundException(
							"User not found with id: " + dto.getAuthorId(),
							ErrorCode.USER_NOT_FOUND
					));
			SharedPage sharedPage = sharedPageRepository.findByUserAndPage(user, page)
					.orElseThrow(() -> new EntityNotFoundException(
							"Current user does not have access to unshared this page",
							ErrorCode.PAGE_NOT_FOUND
					));

			if (!sharedPage.getRole().equals(UserRole.OWNER) &&
					!sharedPage.getRole().equals(UserRole.COLLABORATOR)) {
				return new PageDTO();
			}
		}

		Page page = pageRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("" +
						"Page not found with ID: " + id,
						ErrorCode.PAGE_NOT_FOUND
				));

		page.setBackground(dto.getBackground());
		page.setUpdatedAt(LocalDate.now());

		return PageDTO.toPageDTO(pageRepository.save(page));
	}

	@Override
	public List<PageDTO> getAll() {
		return pageRepository.findAll().stream()
				.map(PageDTO::toPageDTO).collect(Collectors.toList());
	}

	@Override
	public PageDTO getById(Integer id) {
		return pageRepository.findById(id)
				.map(PageDTO::toPageDTO)
				.orElseThrow(() -> new EntityNotFoundException("" +
						"Page not found with ID = " + id,
						ErrorCode.PAGE_NOT_FOUND
				));
	}

	@Override
	public List<PageDTO> getAllByWorkspaceId(Integer workspaceId) {
		return pageRepository.findPageByWorkspaceId(workspaceId).stream()
				.map(PageDTO::toPageDTO).collect(Collectors.toList());
	}

	@Override
	public List<PageDTO> getAllByAuthorId(Integer authorId) {
		return pageRepository.findPageByAuthorId(authorId).stream()
				.map(PageDTO::toPageDTO).collect(Collectors.toList());
	}

	@Override
	public List<PageDTO> getAllByTagId(Integer tagId) {
		return pageRepository.findPagesByTagsId(tagId).stream()
				.map(PageDTO::toPageDTO).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		Page page = pageRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("" +
						"Page not found with ID = " + id,
						ErrorCode.PAGE_NOT_FOUND
				));

		List<SharedPage> list = sharedPageRepository.findAllByPageId(id);
		for (SharedPage shared : list) {
			sharedPageRepository.delete(shared);
		}

		for (Tag tag : page.getTags()) {
			tag.getPages().remove(page);
		}
		page.getTags().clear();

		pageRepository.delete(page);
	}
}
