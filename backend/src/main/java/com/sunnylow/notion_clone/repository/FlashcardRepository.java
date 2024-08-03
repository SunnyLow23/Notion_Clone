package com.sunnylow.notion_clone.repository;

import com.sunnylow.notion_clone.model.FlashcardBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardRepository extends JpaRepository<FlashcardBlock, Integer> {
}
