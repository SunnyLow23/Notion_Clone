package com.sunnylow.notion_clone.service.impl;

import com.sunnylow.notion_clone.dto.StaticNoteDTO;
import com.sunnylow.notion_clone.exception.EntityNotFoundException;
import com.sunnylow.notion_clone.exception.ErrorCode;
import com.sunnylow.notion_clone.model.Page;
import com.sunnylow.notion_clone.model.StaticNote;
import com.sunnylow.notion_clone.model.User;
import com.sunnylow.notion_clone.repository.PageRepository;
import com.sunnylow.notion_clone.repository.StaticNoteRepository;
import com.sunnylow.notion_clone.repository.UserRepository;
import com.sunnylow.notion_clone.service.StaticNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaticNoteServiceImpl implements StaticNoteService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PageRepository pageRepository;

	@Autowired
	private StaticNoteRepository staticNoteRepository;

	@Override
	public StaticNoteDTO save(StaticNoteDTO dto) {
		if (staticNoteRepository.existsByPageId(dto.getPageId())) {
			StaticNote staticNote = staticNoteRepository.findByPageId(dto.getPageId());
			staticNote.setContent(dto.getContent());

			return StaticNoteDTO.toStaticNoteDTO(staticNoteRepository.save(staticNote));
		} else {
			User user = userRepository.findById(dto.getUserId())
					.orElseThrow(() -> new EntityNotFoundException(
							"User not found with ID = " + dto.getUserId(),
							ErrorCode.USER_NOT_FOUND
					));
			Page page = pageRepository.findById(dto.getPageId())
					.orElseThrow(() -> new EntityNotFoundException(
							"Page not found with ID = " + dto.getPageId(),
							ErrorCode.PAGE_NOT_FOUND
					));

			StaticNote staticNote = StaticNoteDTO.toStaticNote(dto);
			staticNote.setUser(user);
			staticNote.setPage(page);

			return StaticNoteDTO.toStaticNoteDTO(staticNoteRepository.save(staticNote));
		}
	}

	@Override
	public List<StaticNoteDTO> getAll() {
		return staticNoteRepository.findAll().stream()
				.map(StaticNoteDTO::toStaticNoteDTO).collect(Collectors.toList());
	}

	@Override
	public StaticNoteDTO getById(Integer id) {
		return staticNoteRepository.findById(id)
				.map(StaticNoteDTO::toStaticNoteDTO)
				.orElseThrow(() -> new EntityNotFoundException(
						"No note found with id: " + id,
						ErrorCode.PAGE_NOT_FOUND
				));
	}

	@Override
	public StaticNoteDTO getByPageId(Integer pageId) {
		return StaticNoteDTO.toStaticNoteDTO(staticNoteRepository.findByPageId(pageId));

	}

	@Override
	public void delete(Integer id) {
		staticNoteRepository.deleteById(id);
	}
}
