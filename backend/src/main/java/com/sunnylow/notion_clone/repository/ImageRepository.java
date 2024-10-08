package com.sunnylow.notion_clone.repository;

import com.sunnylow.notion_clone.model.ImageBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageBlock, Integer> {

	List<ImageBlock> findImageBlocksByPageId(int pageId);
}
