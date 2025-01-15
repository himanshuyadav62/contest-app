package com.contest.api.contest.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "contents")
@Data
@AllArgsConstructor
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "content_id")
    private String contentId;

    private String contentType; 

    @NotBlank
    @Column(name = "content_url_id")
    private String contentUrlId;

    @Column(name = "content_order")
    private Integer contentOrder;
}
