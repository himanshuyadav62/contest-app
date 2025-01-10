package com.contest.api.contest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiRes<T> {
    private int statusCode;
    private boolean success;
    private String message;
    private T data;
}
