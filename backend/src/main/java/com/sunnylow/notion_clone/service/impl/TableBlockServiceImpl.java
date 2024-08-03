package com.sunnylow.notion_clone.service.impl;

import com.sunnylow.notion_clone.dto.TableBlockDTO;
import com.sunnylow.notion_clone.exception.EntityNotFoundException;
import com.sunnylow.notion_clone.exception.ErrorCode;
import com.sunnylow.notion_clone.model.Page;
import com.sunnylow.notion_clone.model.TableBlock;
import com.sunnylow.notion_clone.model.User;
import com.sunnylow.notion_clone.repository.PageRepository;
import com.sunnylow.notion_clone.repository.TableRepository;
import com.sunnylow.notion_clone.repository.UserRepository;
import com.sunnylow.notion_clone.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableBlockServiceImpl implements BlockService<TableBlockDTO> {

	@Autowired
	private TableRepository tableRepository;

	@Autowired
	private PageRepository pageRepository;

	@Autowired
	private UserRepository userRepository;
	@Override
	public TableBlockDTO save(TableBlockDTO dto) {
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

		TableBlock tableBlock = TableBlockDTO.toTableBlock(dto);
		tableBlock.setCreatedAt(LocalDate.now());
		tableBlock.setUpdatedAt(LocalDate.now());
		tableBlock.setPage(page);
		tableBlock.setCreatedBy(user);

		return TableBlockDTO.toTableBlockDTO(tableRepository.save(tableBlock));
	}

	@Override
	public TableBlockDTO update(Integer id, TableBlockDTO dto) {
		TableBlock tableBlock = tableRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"Table not found with ID = " + id,
						ErrorCode.TABLE_NOT_FOUND
				));

		tableBlock.setUpdatedAt(LocalDate.now());
		tableBlock.setTableData(dto.getTableData());

		return TableBlockDTO.toTableBlockDTO(tableRepository.save(tableBlock));
	}

	@Override
	public List<TableBlockDTO> getAll() {
		return tableRepository.findAll().stream()
				.map(TableBlockDTO::toTableBlockDTO).collect(Collectors.toList());
	}

	@Override
	public TableBlockDTO getById(Integer id) {
		return tableRepository.findById(id)
				.map(TableBlockDTO::toTableBlockDTO)
				.orElseThrow(() -> new EntityNotFoundException(
						"Table not found with ID = " + id,
						ErrorCode.TABLE_NOT_FOUND
				));
	}

	@Override
	public void delete(Integer id) {
		TableBlock tableBlock = tableRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"Table not found with ID = " + id,
						ErrorCode.TABLE_NOT_FOUND
				));

		tableRepository.delete(tableBlock);
	}
}
