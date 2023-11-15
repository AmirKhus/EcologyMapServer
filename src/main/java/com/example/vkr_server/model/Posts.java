package com.example.vkr_server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "t_posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "postTitle")
    private String postTitle;

    @Column(name = "postObject")
    private String postObject;

    @ManyToOne(fetch = FetchType.EAGER)
    private User posterUser;

    @Column(name = "postDate")
    private Date postDate;

}
