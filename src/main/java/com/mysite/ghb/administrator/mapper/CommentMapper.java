package com.mysite.ghb.administrator.mapper;
/*
 */

import com.mysite.ghb.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CommentMapper {


    List<Comment> listCommentByBlogId(Long id);

    int saveComment(Comment comment);

    Comment getCommentById(Long id);
}
