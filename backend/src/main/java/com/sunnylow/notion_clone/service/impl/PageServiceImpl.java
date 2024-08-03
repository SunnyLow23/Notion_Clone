package com.sunnylow.notion_clone.service.impl;

import com.sunnylow.notion_clone.dto.PageDTO;
import com.sunnylow.notion_clone.model.Page;
import com.sunnylow.notion_clone.model.Tag;
import com.sunnylow.notion_clone.model.User;
import com.sunnylow.notion_clone.model.Workspace;
import com.sunnylow.notion_clone.repository.PageRepository;
import com.sunnylow.notion_clone.repository.UserRepository;
import com.sunnylow.notion_clone.repository.WorkspaceRepository;
import com.sunnylow.notion_clone.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PageServiceImpl implements PageService {

	@Autowired
	private PageRepository pageRepository;

	@Autowired
	private WorkspaceRepository workspaceRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public PageDTO save(PageDTO dto) {
		User user = userRepository.findById(dto.getAuthorId())
				.orElseThrow(() -> new RuntimeException("User Not Found"));
		Workspace workspace = workspaceRepository.findById(dto.getWorkspaceId())
				.orElseThrow(() -> new RuntimeException("Workspace Not Found"));

		Page page = PageDTO.toPage(dto);
		page.setWorkspace(workspace);
		page.setAuthor(user);
		page.setCreatedAt(LocalDate.now());
		page.setUpdatedAt(LocalDate.now());

		return PageDTO.toPageDTO(pageRepository.save(page));
	}

	@Override
	public PageDTO update(Integer id, PageDTO dto) {
		Page page = pageRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Page Not Found"));

		page.setTitle(dto.getTitle());
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
				.orElseThrow(() -> new RuntimeException("Page Not Found"));
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
				.orElseThrow(() -> new RuntimeException("Page Not Found"));

		for (Tag tag : page.getTags()) {
			tag.getPages().remove(page);
		}
		page.getTags().clear();

		pageRepository.delete(page);
	}
}
