package com.sunnylow.notion_clone.controller;

import com.sunnylow.notion_clone.controller.api.ImageBlockAPI;
import com.sunnylow.notion_clone.dto.ImageBlockDTO;
import com.sunnylow.notion_clone.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImageBlockController implements ImageBlockAPI {

	@Autowired
	private BlockService<ImageBlockDTO> blockService;

	@Override
	public ResponseEntity<ImageBlockDTO> createImage(ImageBlockDTO dto) {
		return new ResponseEntity<>(blockService.save(dto), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ImageBlockDTO> updateImage(Integer id, ImageBlockDTO dto) {
		return new ResponseEntity<>(blockService.update(id, dto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ImageBlockDTO>> getAllImages() {
		return new ResponseEntity<>(blockService.getAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ImageBlockDTO> getImageById(Integer id) {
		return new ResponseEntity<>(blockService.getById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity deleteImageById(Integer id) {
		blockService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}
}
