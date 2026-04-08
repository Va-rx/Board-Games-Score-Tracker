package com.boardgames.hub.group.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GroupResponse {
    private Long id;
    private String name;
}
