package com.sunnylow.notion_clone.repository;

import com.sunnylow.notion_clone.model.Page;
import com.sunnylow.notion_clone.model.SharedPage;
import com.sunnylow.notion_clone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SharedPageRepository extends JpaRepository<SharedPage, Integer> {

	Optional<SharedPage> findByUserAndPage(User user, Page page);

	Optional<SharedPage> findByPageId(Integer pageId);

	List<SharedPage> findAllByUserId(Integer userId);

	Boolean existsByPageId(Integer pageId);

	Boolean existsByUserAndPage(User user, Page page);

	List<SharedPage> findAllByPageId(Integer pageId);
}
