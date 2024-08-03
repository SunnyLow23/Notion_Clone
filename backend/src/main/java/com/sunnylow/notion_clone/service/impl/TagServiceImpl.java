package com.sunnylow.notion_clone.service.impl;

import com.sunnylow.notion_clone.dto.TagDTO;
import com.sunnylow.notion_clone.model.Page;
import com.sunnylow.notion_clone.model.Tag;
import com.sunnylow.notion_clone.model.User;
import com.sunnylow.notion_clone.repository.TagRepository;
import com.sunnylow.notion_clone.repository.UserRepository;
import com.sunnylow.notion_clone.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public TagDTO save(TagDTO dto) {
		User createdBy = userRepository.findById(dto.getCreatedBy())
				.orElseThrow(() -> new RuntimeException("User Not Found"));

		Tag tag = TagDTO.toTag(dto);

		tag.setCreatedAt(LocalDate.now());
		tag.setUpdatedAt(LocalDate.now());
		tag.setCreatedBy(createdBy);

		return TagDTO.toTagDTO(tagRepository.save(tag));
	}

	@Override
	public TagDTO update(Integer id, TagDTO dto) {
		Tag tag = tagRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Tag Not Found"));

		tag.setName(dto.getName());
		tag.setColor(dto.getColor());
		tag.setUpdatedAt(LocalDate.now());

		return TagDTO.toTagDTO(tagRepository.save(tag));
	}

	@Override
	public List<TagDTO> getAll() {
		return tagRepository.findAll().stream()
				.map(TagDTO::toTagDTO).collect(Collectors.toList());
	}

	@Override
	public TagDTO getById(Integer id) {
		return tagRepository.findById(id)
				.map(TagDTO::toTagDTO)
				.orElseThrow(() -> new RuntimeException("Tag Not Found"));
	}

	@Override
	public void delete(Integer id) {
		Tag tag = tagRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Tag Not Found"));

		for (Page page : tag.getPages()) {
			page.getTags().remove(tag);
		}
		tag.getPages().clear();

		tagRepository.delete(tag);
	}
}
