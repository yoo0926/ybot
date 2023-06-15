package com.example.ybot.domain.dooray.web;

import com.example.ybot.domain.dooray.web.dto.DoorayRqDto;
import com.example.ybot.domain.dooray.web.dto.DoorayRsDto;
import com.example.ybot.domain.notion.service.NotionService;
import com.example.ybot.domain.notion.service.dto.NotionRsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class DoorayController {
    private final NotionService notionService;

    @PostMapping("/hello")
    public ResponseEntity<DoorayRsDto> hello(@RequestBody DoorayRqDto rqDto) {
        log.info("rqDto : {}", rqDto.toString());
        DoorayRsDto rsDto = DoorayRsDto.builder()
                .text("Hello World!")
                .responseType("inChannel")
                .build();

         return new ResponseEntity<>(rsDto, HttpStatus.OK);
    }

    @GetMapping("/notion/all")
    public ResponseEntity<NotionRsDto> getNotionAll() {
        NotionRsDto result = notionService.getAll();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = {"lunch", "점심"})
    public ResponseEntity<DoorayRsDto> getRecommendLunch(@RequestBody DoorayRqDto rqDto) {
        log.info("rqDto : {}", rqDto.toString());
        DoorayRsDto rsDto = notionService.getRandom();

        return new ResponseEntity<>(rsDto, HttpStatus.OK);
    }
}
