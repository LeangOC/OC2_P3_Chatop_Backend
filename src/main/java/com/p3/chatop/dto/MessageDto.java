package com.p3.chatop.dto;

import lombok.Data;

@Data
public class MessageDto {
    private Integer user_id;
    private Integer rental_id;
    private String message;
}