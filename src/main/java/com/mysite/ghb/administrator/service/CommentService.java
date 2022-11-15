package com.mysite.ghb.administrator.service;
/*
 */

import com.mysite.ghb.entity.Comment;

import java.util.List;

public interface CommentService {


    List<Comment> listCommentByBlogId(Long id);

    Comment saveComment(Comment comment);

    Comment getCommentById(Long id);

}
