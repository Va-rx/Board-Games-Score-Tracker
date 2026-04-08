package com.boardgames.hub.group.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GroupUpdateRequest {
    @Size(min = 3, max = 50, message = "Group name must have from 3 to 50 characters")
    private String name;
}
