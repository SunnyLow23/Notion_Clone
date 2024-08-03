package com.sunnylow.notion_clone.repository;

import com.sunnylow.notion_clone.model.TextBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository extends JpaRepository<TextBlock, Integer> {
}
