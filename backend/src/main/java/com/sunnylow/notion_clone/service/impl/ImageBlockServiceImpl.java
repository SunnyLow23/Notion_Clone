package com.sunnylow.notion_clone.service.impl;

import com.sunnylow.notion_clone.dto.ImageBlockDTO;
import com.sunnylow.notion_clone.model.ImageBlock;
import com.sunnylow.notion_clone.model.Page;
import com.sunnylow.notion_clone.model.User;
import com.sunnylow.notion_clone.repository.ImageRepository;
import com.sunnylow.notion_clone.repository.PageRepository;
import com.sunnylow.notion_clone.repository.UserRepository;
import com.sunnylow.notion_clone.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageBlockServiceImpl implements BlockService<ImageBlockDTO> {

	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private PageRepository pageRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public ImageBlockDTO save(ImageBlockDTO dto) {
		User user = userRepository.findById(dto.getCreatedById())
				.orElseThrow(() -> new RuntimeException("User Not Found"));
		Page page = pageRepository.findById(dto.getPageId())
				.orElseThrow(() -> new RuntimeException("Page Not Found"));

		ImageBlock imageBlock = ImageBlockDTO.toImageBlock(dto);
		imageBlock.setCreatedAt(LocalDate.now());
		imageBlock.setUpdatedAt(LocalDate.now());
		imageBlock.setPage(page);
		imageBlock.setCreatedBy(user);

		return ImageBlockDTO.toImageBlockDTO(imageRepository.save(imageBlock));
	}

	@Override
	public ImageBlockDTO update(Integer id, ImageBlockDTO dto) {
		ImageBlock imageBlock = imageRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Image Not Found"));

		imageBlock.setUpdatedAt(LocalDate.now());
		imageBlock.setImageUrl(dto.getImageUrl());
		imageBlock.setCaption(dto.getCaption());

		return ImageBlockDTO.toImageBlockDTO(imageRepository.save(imageBlock));
	}

	@Override
	public List<ImageBlockDTO> getAll() {
		return imageRepository.findAll().stream()
				.map(ImageBlockDTO::toImageBlockDTO).collect(Collectors.toList());
	}

	@Override
	public ImageBlockDTO getById(Integer id) {
		return imageRepository.findById(id)
				.map(ImageBlockDTO::toImageBlockDTO)
				.orElseThrow(() -> new RuntimeException("Image Not Found"));
	}

	@Override
	public void delete(Integer id) {
		ImageBlock imageBlock = imageRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Image Not Found"));

		imageRepository.delete(imageBlock);
	}
}
