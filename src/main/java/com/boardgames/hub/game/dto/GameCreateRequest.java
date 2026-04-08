package com.boardgames.hub.game.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GameCreateRequest {
    @NotBlank(message = "Name can't be empty")
    @Size(min = 3, max = 100, message = "Name must have from 3 to 100 characters")
    private String name;
}
