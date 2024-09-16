package com.sunnylow.notion_clone.service.impl;

import com.sunnylow.notion_clone.dto.PageDTO;
import com.sunnylow.notion_clone.dto.SharedPageDTO;
import com.sunnylow.notion_clone.exception.EntityNotFoundException;
import com.sunnylow.notion_clone.exception.ErrorCode;
import com.sunnylow.notion_clone.model.Page;
import com.sunnylow.notion_clone.model.SharedPage;
import com.sunnylow.notion_clone.model.User;
import com.sunnylow.notion_clone.model.UserRole;
import com.sunnylow.notion_clone.repository.PageRepository;
import com.sunnylow.notion_clone.repository.SharedPageRepository;
import com.sunnylow.notion_clone.repository.UserRepository;
import com.sunnylow.notion_clone.service.SharedPageService;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SharedPageServiceImpl implements SharedPageService {

	@Autowired
	private SharedPageRepository sharedPageRepository;

	@Autowired
	private PageRepository pageRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public SharedPageDTO sharePage(SharedPageDTO dto) {
		Page page = pageRepository.findById(dto.getPageId())
				.orElseThrow(() -> new EntityNotFoundException(
						"Page not found with id: " + dto.getPageId(),
						ErrorCode.PAGE_NOT_FOUND
				));
		User user = userRepository.findByEmail(dto.getEmail())
				.orElseThrow(() -> new EntityNotFoundException(
						"User not found with email: " + dto.getEmail(),
						ErrorCode.USER_NOT_FOUND
				));
		User author = userRepository.findByEmail(dto.getEmailAuthor())
				.orElseThrow(() -> new EntityNotFoundException(
						"Author not found with email: " + dto.getEmailAuthor(),
						ErrorCode.USER_NOT_FOUND
				));

		if (!page.getAuthor().equals(author) && !dto.getRole().equals(UserRole.OWNER)) {
			throw new EntityExistsException("Current user does not have access to share this page.");
		}

//		Optional<SharedPage> existingSharedPage = sharedPageRepository.findByUserAndPage(user, page);
//		if (existingSharedPage.isPresent()) {
//			throw new EntityExistsException("Shared page already exists");
//		}

		if (!sharedPageRepository.existsByUserAndPage(author, page)) {
			SharedPage sharedPageOwner = new SharedPage();
			sharedPageOwner.setUser(author);
			sharedPageOwner.setPage(page);
			sharedPageOwner.setRole(UserRole.OWNER);
			sharedPageRepository.save(sharedPageOwner);
		}

		SharedPage sharedPage = new SharedPage();
		sharedPage.setUser(user);
		sharedPage.setPage(page);
		sharedPage.setRole(dto.getRole());

		return SharedPageDTO.toSharedPageDTO(sharedPageRepository.save(sharedPage));
	}

	@Override
	public SharedPageDTO unSharePage(SharedPageDTO dto) {
		Page page = pageRepository.findById(dto.getPageId())
				.orElseThrow(() -> new EntityNotFoundException(
						"Page not found with id: " + dto.getPageId(),
						ErrorCode.PAGE_NOT_FOUND
				));
		User author = userRepository.findByEmail(dto.getEmailAuthor())
				.orElseThrow(() -> new EntityNotFoundException(
						"Author not found with email: " + dto.getEmailAuthor(),
						ErrorCode.USER_NOT_FOUND
				));
		SharedPage sharedPage = sharedPageRepository.findByUserAndPage(author, page)
				.orElseThrow(() -> new EntityNotFoundException(
						"Current user does not have access to unshared this page",
						ErrorCode.PAGE_NOT_FOUND
				));

		if (!sharedPage.getRole().equals(UserRole.OWNER)) {
			throw new EntityExistsException("Current user does not have access to unshared this page.");
		}

		List<SharedPage> list = sharedPageRepository.findAllByPageId(dto.getPageId());
		for (SharedPage shared : list) {
			sharedPageRepository.delete(shared);
		}

		return SharedPageDTO.toSharedPageDTO(sharedPage);
	}

	@Override
	public List<SharedPageDTO> getAll() {
		return sharedPageRepository.findAll().stream()
				.map(SharedPageDTO::toSharedPageDTO).collect(Collectors.toList());
	}

	@Override
	public SharedPageDTO getById(Integer id) {
		return sharedPageRepository.findById(id)
				.map(SharedPageDTO::toSharedPageDTO)
				.orElseThrow(() -> new EntityNotFoundException(
						"Shared Page not found with id = " + id,
						ErrorCode.PAGE_NOT_FOUND
				));
	}

	@Override
	public List<SharedPageDTO> getAllByUserId(Integer userId) {
		return sharedPageRepository.findAllByUserId(userId).stream()
				.map(SharedPageDTO::toSharedPageDTO).collect(Collectors.toList());
	}

	@Override
	public List<PageDTO> getAllPagesByUserId(Integer userId) {
		List<SharedPage> listSharedPages = sharedPageRepository.findAllByUserId(userId).stream()
				.collect(Collectors.toList());

		List<Page> listPages = new ArrayList<>();

		for (SharedPage sharedPage : listSharedPages) {
			listPages.add(sharedPage.getPage());
		}

		return listPages.stream().map(PageDTO::toPageDTO).collect(Collectors.toList());
	}
}
