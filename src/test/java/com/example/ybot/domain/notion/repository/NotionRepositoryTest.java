package com.example.ybot.domain.notion.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class NotionRepositoryTest {
    @Autowired
    NotionRepository notionRepository;

    @AfterEach
    public void cleanup() {
        notionRepository.deleteAll();
    }

    @DisplayName("등록 테스트")
    @Test
    void NotionRepository_insert() {
        List<Notion> sampleList = new ArrayList<>();

        for(int i=0; i < 10; i++) {
            sampleList.add(Notion.builder()
                    .title("title_" + i)
                    .category("category_" + i)
                    .description("description_" + i)
                    .build());
        }

        notionRepository.saveAll(sampleList);

        assertThat(sampleList.size()).isEqualTo(10);
    }
}
