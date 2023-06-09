package com.example.ybot.domain.notion.repository;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Notion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String category;

    @Builder
    public Notion(Long seq, String title, String category) {
        this.seq = seq;
        this.title = title;
        this.category = category;
    }

    public void update(String title, String category) {
        this.title = title;
        this.category = category;
    }
}
