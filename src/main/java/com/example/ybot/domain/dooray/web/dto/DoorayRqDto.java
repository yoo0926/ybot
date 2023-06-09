package com.example.ybot.domain.dooray.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class DoorayRqDto {
    private String tenantId;
    private String tenantDomain;
    private String channelId;
    private String channelName;
    private String userId;
    private String command;
    private String text;
    private String responseUrl;
    private String appToken;
    private String cmdToken;
    private String triggerId;
}
