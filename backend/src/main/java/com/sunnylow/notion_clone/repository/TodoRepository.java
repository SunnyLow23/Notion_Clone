package com.sunnylow.notion_clone.repository;

import com.sunnylow.notion_clone.model.TodoBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoBlock, Integer> {

	List<TodoBlock> findTodoBlocksByPageId(int pageId);
}
