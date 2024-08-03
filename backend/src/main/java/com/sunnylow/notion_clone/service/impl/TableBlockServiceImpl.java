package com.sunnylow.notion_clone.service.impl;

import com.sunnylow.notion_clone.dto.TableBlockDTO;
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
				.orElseThrow(() -> new RuntimeException("User Not Found"));
		Page page = pageRepository.findById(dto.getPageId())
				.orElseThrow(() -> new RuntimeException("Page Not Found"));

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
				.orElseThrow(() -> new RuntimeException("Table Not Found"));

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
				.orElseThrow(() -> new RuntimeException("Table Not Found"));
	}

	@Override
	public void delete(Integer id) {
		TableBlock tableBlock = tableRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Table Not Found"));

		tableRepository.delete(tableBlock);
	}
}
