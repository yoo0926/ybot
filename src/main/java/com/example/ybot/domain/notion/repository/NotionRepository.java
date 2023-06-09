package com.example.ybot.domain.notion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotionRepository extends JpaRepository<Notion, Long> {
}
