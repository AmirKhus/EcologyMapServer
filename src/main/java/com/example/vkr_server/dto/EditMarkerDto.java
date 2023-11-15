package com.example.vkr_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EditMarkerDto {
    private Long id;
    private String img;
    private String type;
    private String coord;
    private String description;
}
