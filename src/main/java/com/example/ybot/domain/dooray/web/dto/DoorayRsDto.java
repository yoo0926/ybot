package com.example.ybot.domain.dooray.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class DoorayRsDto {
    private String text;
    @Builder.Default
    private String responseType = "inChannel";
}
