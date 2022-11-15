package com.mysite.ghb.entity;
/*
 */
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
public class Tag {
    private Long id;
    private String name;

    private List<Blog> blogs=new LinkedList<>();
}
