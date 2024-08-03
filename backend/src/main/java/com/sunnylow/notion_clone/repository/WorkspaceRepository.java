package com.sunnylow.notion_clone.repository;

import com.sunnylow.notion_clone.model.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Integer> {

	List<Workspace> findWorkspaceByUserId(Integer userId);
}
