package org.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "t_marker")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Marker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "coord")
    private String coord;

    @Column(name = "author")
    private String author;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private String date;

    @Column(name = "rating")
    private Long rating;

    @Column(name = "region")
    private String region;

}
