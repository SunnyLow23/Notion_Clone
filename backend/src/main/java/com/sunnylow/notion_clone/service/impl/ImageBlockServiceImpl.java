package com.sunnylow.notion_clone.service.impl;

import com.sunnylow.notion_clone.dto.ImageBlockDTO;
import com.sunnylow.notion_clone.exception.EntityNotFoundException;
import com.sunnylow.notion_clone.exception.ErrorCode;
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
				.orElseThrow(() -> new EntityNotFoundException(
						"User not found with ID = " + dto.getCreatedById(),
						ErrorCode.USER_NOT_FOUND
				));
		Page page = pageRepository.findById(dto.getPageId())
				.orElseThrow(() -> new EntityNotFoundException(
						"Page not found with ID = " + dto.getPageId(),
						ErrorCode.PAGE_NOT_FOUND
				));

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
				.orElseThrow(() -> new EntityNotFoundException(
						"Image Block not found with ID = " + id,
						ErrorCode.IMAGE_NOT_FOUND
				));

		imageBlock.setUpdatedAt(LocalDate.now());
		imageBlock.setImageUrl(dto.getImageUrl());
		imageBlock.setCaption(dto.getCaption());

		return ImageBlockDTO.toImageBlockDTO(imageRepository.save(imageBlock));
	}

	@Override
	public ImageBlockDTO updatePosition(Integer id, Integer newPosition) {
		ImageBlock imageBlock = imageRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"Image Block not found with ID = " + id,
						ErrorCode.IMAGE_NOT_FOUND
				));

		imageBlock.setUpdatedAt(LocalDate.now());
		imageBlock.setPosition(newPosition);

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
				.orElseThrow(() -> new EntityNotFoundException(
						"Image Block not found with ID = " + id,
						ErrorCode.IMAGE_NOT_FOUND
				));
	}

	@Override
	public List<ImageBlockDTO> getAllByPageId(Integer pageId) {
		return imageRepository.findImageBlocksByPageId(pageId).stream()
				.map(ImageBlockDTO::toImageBlockDTO).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		ImageBlock imageBlock = imageRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"Image Block not found with ID = " + id,
						ErrorCode.IMAGE_NOT_FOUND
				));

		imageRepository.delete(imageBlock);
	}
}
