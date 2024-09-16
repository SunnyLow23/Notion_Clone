package com.sunnylow.notion_clone.repository;

import com.sunnylow.notion_clone.model.BlockNote;
import com.sunnylow.notion_clone.model.StaticNote;
import com.sunnylow.notion_clone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaticNoteRepository extends JpaRepository<StaticNote, Integer> {

	Boolean existsByPageId(Integer pageId);

	StaticNote findByPageId(Integer pageId);
}
