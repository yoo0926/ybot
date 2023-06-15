package com.example.ybot;

import com.example.ybot.domain.notion.repository.Notion;
import com.example.ybot.domain.notion.service.NotionService;
import com.example.ybot.domain.notion.service.dto.NotionRsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Slf4j
@SpringBootApplication
public class YbotApplication {

    public static void main(String[] args) {
        SpringApplication.run(YbotApplication.class, args);
    }

    /**
     * 스프링부트 애플리케이션이 실행될때 Spring applicationContext 의 초기화가 완료되면(모든 Bean이 초기화 되면) 1번 실행된다.
	 * Notion openAPI가 종종 느려서 인메모리DB에 동기화 후 사용하기 위해 개발했으나 현재 속도는 충분한 상태
     * 하지만 JPA 써볼겸해서 일단 유지
     */
    @Bean
    public CommandLineRunner run(NotionService notionService) {
        return (String[] args) -> {
            NotionRsDto rsDto = notionService.getAll();
            List<Notion> list = notionService.saveAll(rsDto);
            log.info("init notion DB: {} rows", list.size());
        };
    }
}
