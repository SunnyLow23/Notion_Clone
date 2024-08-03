package com.sunnylow.notion_clone.service.impl;

import com.sunnylow.notion_clone.dto.JournalBlockDTO;
import com.sunnylow.notion_clone.exception.EntityNotFoundException;
import com.sunnylow.notion_clone.exception.ErrorCode;
import com.sunnylow.notion_clone.model.JournalBlock;
import com.sunnylow.notion_clone.model.Page;
import com.sunnylow.notion_clone.model.User;
import com.sunnylow.notion_clone.repository.JournalRepository;
import com.sunnylow.notion_clone.repository.PageRepository;
import com.sunnylow.notion_clone.repository.UserRepository;
import com.sunnylow.notion_clone.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JournalBlockServiceImpl implements BlockService<JournalBlockDTO> {

	@Autowired
	private JournalRepository journalRepository;

	@Autowired
	private PageRepository pageRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public JournalBlockDTO save(JournalBlockDTO dto) {
		User user = userRepository.findById(dto.getCreatedById())
				.orElseThrow(() -> new EntityNotFoundException(
						"User not found with ID = " + dto.getCreatedById(),
						ErrorCode.USER_NOT_FOUND
				));
		Page page = pageRepository.findById(dto.getPageId())
				.orElseThrow(() -> new EntityNotFoundException(
						"Page not found with ID = " + dto.getPageId(),
						ErrorCode.PAGE_NOT_FOUND
				));

		JournalBlock journalBlock = JournalBlockDTO.toJournalBlock(dto);
		journalBlock.setCreatedAt(LocalDate.now());
		journalBlock.setUpdatedAt(LocalDate.now());
		journalBlock.setPage(page);
		journalBlock.setCreatedBy(user);

		return JournalBlockDTO.toJournalBlockDTO(journalRepository.save(journalBlock));
	}

	@Override
	public JournalBlockDTO update(Integer id, JournalBlockDTO dto) {
		JournalBlock journalBlock = journalRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"Journal Block not found with ID = " + id,
						ErrorCode.JOURNAL_NOT_FOUND
				));

		journalBlock.setUpdatedAt(LocalDate.now());
		journalBlock.setJournalDate(dto.getJournalDate());
		journalBlock.setContent(dto.getContent());

		return JournalBlockDTO.toJournalBlockDTO(journalRepository.save(journalBlock));
	}

	@Override
	public List<JournalBlockDTO> getAll() {
		return journalRepository.findAll().stream()
				.map(JournalBlockDTO::toJournalBlockDTO).collect(Collectors.toList());
	}

	@Override
	public JournalBlockDTO getById(Integer id) {
		return journalRepository.findById(id)
				.map(JournalBlockDTO::toJournalBlockDTO)
				.orElseThrow(() -> new EntityNotFoundException(
						"Journal Block not found with ID = " + id,
						ErrorCode.JOURNAL_NOT_FOUND
				));
	}

	@Override
	public void delete(Integer id) {
		JournalBlock journalBlock = journalRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"Journal Block not found with ID = " + id,
						ErrorCode.JOURNAL_NOT_FOUND
				));

		journalRepository.delete(journalBlock);
	}
}
