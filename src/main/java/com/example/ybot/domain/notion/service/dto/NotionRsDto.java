package com.example.ybot.domain.notion.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
public class NotionRsDto {
    private String object;
    private ArrayList<Result> results;
    private String next_cursor;
    private boolean has_more;
    private String type;
    private Object page;
    private String developer_survey;

    @Getter
    @Setter
    public static class Annotations{
        private boolean bold;
        private boolean italic;
        private boolean strikethrough;
        private boolean underline;
        private boolean code;
        private String color;
    }

    @Getter
    @Setter
    public static class Category{
        private String id;
        private String type;
        private Select select;
    }

    @Getter
    @Setter
    public static class CreatedBy{
        private String object;
        private String id;
    }

    @Getter
    @Setter
    public static class LastEditedBy{
        private String object;
        private String id;
    }

    @Getter
    @Setter
    public static class Parent{
        private String type;
        private String database_id;
    }

    @Getter
    @Setter
    public static class Properties{
        @JsonProperty("분류")
        private Category category;
        @JsonProperty("이름")
        private Title name;
    }

    @Getter
    @Setter
    public static class Result{
        private String object;
        private String id;
        private Date created_time;
        private Date last_edited_time;
        private CreatedBy created_by;
        private LastEditedBy last_edited_by;
        private Object cover;
        private Object icon;
        private Parent parent;
        private boolean archived;
        private Properties properties;
        private String url;
        private Object private_url;
    }

    @Getter
    @Setter
    public static class Select{
        private String id;
        private String name;
        private String color;
    }

    @Getter
    @Setter
    public static class Text{
        private String content;
        private Object link;
    }

    @Getter
    @Setter
    public static class Title{
        private String id;
        private String type;
        private ArrayList<Title> title;
        private Text text;
        private Annotations annotations;
        private String plain_text;
        private Object href;
    }
}
