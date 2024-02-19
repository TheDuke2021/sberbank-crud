package ru.theduke2021.sberbankcrud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDto {

    private Long id;

    @NotBlank(message = "Title of an item must not be empty!")
    private String title;

    @PositiveOrZero(message = "Quantity of an item must not be negative!")
    private int quantity;

}
