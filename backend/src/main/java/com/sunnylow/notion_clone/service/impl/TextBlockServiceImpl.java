package com.sunnylow.notion_clone.service.impl;

import com.sunnylow.notion_clone.dto.TextBlockDTO;
import com.sunnylow.notion_clone.model.Page;
import com.sunnylow.notion_clone.model.TextBlock;
import com.sunnylow.notion_clone.model.User;
import com.sunnylow.notion_clone.repository.PageRepository;
import com.sunnylow.notion_clone.repository.TextRepository;
import com.sunnylow.notion_clone.repository.UserRepository;
import com.sunnylow.notion_clone.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TextBlockServiceImpl implements BlockService<TextBlockDTO> {

	@Autowired
	private TextRepository textRepository;

	@Autowired
	private PageRepository pageRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public TextBlockDTO save(TextBlockDTO dto) {
		User user = userRepository.findById(dto.getCreatedById())
				.orElseThrow(() -> new RuntimeException("User Not Found"));
		Page page = pageRepository.findById(dto.getPageId())
				.orElseThrow(() -> new RuntimeException("Page Not Found"));

		TextBlock textBlock = TextBlockDTO.toTextBlock(dto);
		textBlock.setCreatedAt(LocalDate.now());
		textBlock.setUpdatedAt(LocalDate.now());
		textBlock.setPage(page);
		textBlock.setCreatedBy(user);

		return TextBlockDTO.toTextBlockDTO(textRepository.save(textBlock));
	}

	@Override
	public TextBlockDTO update(Integer id, TextBlockDTO dto) {
		TextBlock textBlock = textRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Text Block Not Found"));

		textBlock.setUpdatedAt(LocalDate.now());
		textBlock.setContent(dto.getContent());

		return TextBlockDTO.toTextBlockDTO(textRepository.save(textBlock));
	}

	@Override
	public List<TextBlockDTO> getAll() {
		return textRepository.findAll().stream()
				.map(TextBlockDTO::toTextBlockDTO).collect(Collectors.toList());
	}

	@Override
	public TextBlockDTO getById(Integer id) {
		return textRepository.findById(id)
				.map(TextBlockDTO::toTextBlockDTO)
				.orElseThrow(() -> new RuntimeException("Text Block Not Found"));
	}

	@Override
	public void delete(Integer id) {
		TextBlock textBlock = textRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Text Block Not Found"));

		textRepository.delete(textBlock);
	}
}
