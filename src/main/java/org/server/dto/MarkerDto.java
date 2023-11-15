package org.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MarkerDto implements Serializable {
    private Long author;
    private String type;
    private String date;
    private Long rating;
}
