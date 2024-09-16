package com.sunnylow.notion_clone.repository;

import com.sunnylow.notion_clone.model.TableBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<TableBlock, Integer> {

	List<TableBlock> findTableBlocksByPageId(int pageId);
}
