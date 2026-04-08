package com.boardgames.hub.group.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GroupCreateRequest {
    @NotBlank(message = "Name can't be empty")
    @Size(min = 3, max = 50, message = "Name must have from 3 to 50 characters")
    private String name;
}
