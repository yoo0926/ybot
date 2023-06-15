package com.example.ybot.domain.notion.service;

import com.example.ybot.common.config.DomainConfig;
import com.example.ybot.domain.dooray.web.dto.DoorayRsDto;
import com.example.ybot.domain.notion.repository.Notion;
import com.example.ybot.domain.notion.repository.NotionRepository;
import com.example.ybot.domain.notion.service.dto.NotionRsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotionService {
    private final RestTemplate template;
    private final DomainConfig domainConfig;
    private final NotionRepository notionRepository;

    /**
     * Notion API를 호출하여 문서에서 만들어놓은 테이블을 전체조회
     * */
    public NotionRsDto getAll() {
        String url = domainConfig.getDataBaseQueryUrl();

        HttpHeaders headers = new HttpHeaders();
        headers.add(DomainConfig.NOTION_VERSION, domainConfig.getNotionVersion());
        headers.setBearerAuth(domainConfig.getNotionKey());
        HttpEntity<String> entity = new HttpEntity<>("", headers);

        ResponseEntity<NotionRsDto> response = template.postForEntity(url, entity, NotionRsDto.class);
        return response.getBody();
    }

    /**
     * Notion API의 응답을 가공하여 필요한 정보를 인메모리DB에 저장
     * */
    @Transactional
    public List<Notion> saveAll(NotionRsDto notionRsDto) {
        List<Notion> list = convertNotionList(notionRsDto);
        return notionRepository.saveAll(list);
    }

    public DoorayRsDto getRandomDB() {
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);

        List<Notion> notionList = notionRepository.findAll();
        int index = random.nextInt(notionList.size());

        log.info("notionList size {}, index {}", notionList.size(), index);

        Notion notion = notionList.get(index);
        return DoorayRsDto.builder()
                .text("[" + notion.getTitle() + "] " + notion.getDescription())
                .build();
    }

    public DoorayRsDto getRandom() {
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);

        List<Notion> notionList = convertNotionList(getAll());
        int index = random.nextInt(notionList.size());

        log.info("notionList size {}, index {}", notionList.size(), index);

        Notion notion = notionList.get(index);
        return DoorayRsDto.builder()
                .text("[" + notion.getTitle() + "] " + notion.getDescription())
                .build();
    }

    private List<Notion> convertNotionList(NotionRsDto notionRsDto) {
        return notionRsDto.getResults().stream().map(result -> {
            String storeName = result.getProperties().getName().getTitle()
                    .stream().map(title -> title.getText().getContent())
                    .findFirst()
                    .orElseThrow(NoSuchElementException::new);

            String category = result.getProperties().getCategory().getSelect().getName();

            String description = result.getProperties().getRichTextType().getRichText()
                    .stream().map(title -> title.getText().getContent())
                    .findFirst()
                    .orElse("");

            return Notion.builder().title(storeName).category(category).description(description).build();
        }).toList();
    }
}
