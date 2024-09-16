package com.sunnylow.notion_clone.repository;

import com.sunnylow.notion_clone.model.BlockNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlockNoteRepository extends JpaRepository<BlockNote, Integer> {

	List<BlockNote> findBlockNotesByPageId(int pageId);

	BlockNote findBlockNoteByPageId(int pageId);
}
