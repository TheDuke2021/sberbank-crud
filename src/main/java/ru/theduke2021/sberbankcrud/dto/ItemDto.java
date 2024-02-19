package ru.theduke2021.sberbankcrud.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDto {

    private Long id;
    private String title;
    private int quantity;

}
