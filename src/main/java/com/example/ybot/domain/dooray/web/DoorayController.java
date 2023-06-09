package com.example.ybot.domain.dooray.web;

import com.example.ybot.domain.dooray.web.dto.DoorayRqDto;
import com.example.ybot.domain.dooray.web.dto.DoorayRsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DoorayController {

    @PostMapping("/hello")
    public DoorayRsDto hello(@RequestBody DoorayRqDto rqDto) {
        log.info("rqDto : {}", rqDto.toString());
        return DoorayRsDto.builder()
                .text("Hello World!")
                .responseType("inChannel")
                .build();
    }
}
