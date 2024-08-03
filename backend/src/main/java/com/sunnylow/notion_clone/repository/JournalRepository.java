package com.sunnylow.notion_clone.repository;

import com.sunnylow.notion_clone.model.JournalBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalRepository extends JpaRepository<JournalBlock, Integer> {
}
