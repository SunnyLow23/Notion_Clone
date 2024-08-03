package com.sunnylow.notion_clone.service.impl;

import com.sunnylow.notion_clone.dto.JournalBlockDTO;
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
				.orElseThrow(() -> new RuntimeException("User Not Found"));
		Page page = pageRepository.findById(dto.getPageId())
				.orElseThrow(() -> new RuntimeException("Page Not Found"));

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
				.orElseThrow(() -> new RuntimeException("JournalBlock Not Found"));

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
				.orElseThrow(() -> new RuntimeException("JournalBlock Not Found"));
	}

	@Override
	public void delete(Integer id) {
		JournalBlock journalBlock = journalRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("JournalBlock Not Found"));

		journalRepository.delete(journalBlock);
	}
}
