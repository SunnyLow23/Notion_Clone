package com.sunnylow.notion_clone.repository;

import com.sunnylow.notion_clone.model.TextBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextRepository extends JpaRepository<TextBlock, Integer> {

	List<TextBlock> findTextBlocksByPageId(int pageId);
}
