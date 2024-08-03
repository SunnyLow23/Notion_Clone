package com.sunnylow.notion_clone.service.impl;

import com.sunnylow.notion_clone.dto.FlashcardBlockDTO;
import com.sunnylow.notion_clone.model.FlashcardBlock;
import com.sunnylow.notion_clone.model.Page;
import com.sunnylow.notion_clone.model.User;
import com.sunnylow.notion_clone.repository.FlashcardRepository;
import com.sunnylow.notion_clone.repository.PageRepository;
import com.sunnylow.notion_clone.repository.UserRepository;
import com.sunnylow.notion_clone.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlashcardBlockServiceImpl implements BlockService<FlashcardBlockDTO> {

	@Autowired
	private FlashcardRepository flashcardRepository;

	@Autowired
	private PageRepository pageRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public FlashcardBlockDTO save(FlashcardBlockDTO dto) {
		User user = userRepository.findById(dto.getCreatedById())
				.orElseThrow(() -> new RuntimeException("User Not Found"));
		Page page = pageRepository.findById(dto.getPageId())
				.orElseThrow(() -> new RuntimeException("Page Not Found"));

		FlashcardBlock flashcardBlock = FlashcardBlockDTO.toFlashcardBlock(dto);
		flashcardBlock.setCreatedAt(LocalDate.now());
		flashcardBlock.setUpdatedAt(LocalDate.now());
		flashcardBlock.setPage(page);
		flashcardBlock.setCreatedBy(user);

		return FlashcardBlockDTO.toFlashcardBlockDTO(flashcardRepository.save(flashcardBlock));
	}

	@Override
	public FlashcardBlockDTO update(Integer id, FlashcardBlockDTO dto) {
		FlashcardBlock flashcardBlock = flashcardRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User Not Found"));

		flashcardBlock.setUpdatedAt(LocalDate.now());
		flashcardBlock.setQuestion(dto.getQuestion());
		flashcardBlock.setAnswer(dto.getAnswer());

		return FlashcardBlockDTO.toFlashcardBlockDTO(flashcardRepository.save(flashcardBlock));
	}

	@Override
	public List<FlashcardBlockDTO> getAll() {
		return flashcardRepository.findAll().stream()
				.map(FlashcardBlockDTO::toFlashcardBlockDTO).collect(Collectors.toList());
	}

	@Override
	public FlashcardBlockDTO getById(Integer id) {
		return flashcardRepository.findById(id)
				.map(FlashcardBlockDTO::toFlashcardBlockDTO)
				.orElseThrow(() -> new RuntimeException("User Not Found"));
	}

	@Override
	public void delete(Integer id) {
		FlashcardBlock flashcardBlock = flashcardRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User Not Found"));

		flashcardRepository.delete(flashcardBlock);
	}
}
