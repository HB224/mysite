package com.mysite.ghb.entity;
/*
 */
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class Comment {
    private Long id;
  /*  private String name;*/
    private User user;
    private String content;
    private Date createDate;

    private Blog blog;
    private List<Comment> replyComments=new ArrayList<>();
    private Comment parentComment;


}
