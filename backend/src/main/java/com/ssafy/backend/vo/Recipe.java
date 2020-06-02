package com.ssafy.backend.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Recipe {
    private int id;
    private int userId;
    private String ingredient;
    private String date;
    private String title;
    private String content;
}