package com.sunnylow.notion_clone.repository;

import com.sunnylow.notion_clone.model.JournalBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalRepository extends JpaRepository<JournalBlock, Integer> {

	List<JournalBlock> findJournalBlocksByPageId(int pageId);
}
