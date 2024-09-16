package com.sunnylow.notion_clone.service;

import com.sunnylow.notion_clone.dto.PageDTO;
import com.sunnylow.notion_clone.dto.SharedPageDTO;

import java.util.List;

public interface SharedPageService {

	SharedPageDTO sharePage(SharedPageDTO dto);

	SharedPageDTO unSharePage(SharedPageDTO dto);

	List<SharedPageDTO> getAll();

	SharedPageDTO getById(Integer id);

	List<SharedPageDTO> getAllByUserId(Integer userId);

	List<PageDTO> getAllPagesByUserId(Integer userId);
}
