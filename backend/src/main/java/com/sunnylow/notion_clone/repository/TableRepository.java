package com.sunnylow.notion_clone.repository;

import com.sunnylow.notion_clone.model.TableBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<TableBlock, Integer> {
}
