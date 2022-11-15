package com.mysite.ghb.administrator.service;
/*
 */

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.mysite.ghb.entity.Comment;
import com.mysite.ghb.administrator.mapper.CommentMapper;
import com.mysite.ghb.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentServiceImp implements CommentService{
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public List<Comment> listCommentByBlogId(Long id) {
        List<Comment> list=commentMapper.listCommentByBlogId(id);
        /*将返回的数据进行排序（按照updateTime数量）*/
        Collections.sort(list, new Comparator<Comment>() {
            @Override
            public int compare(Comment t1, Comment t2) {
                if(t1.getCreateDate().before(t2.getCreateDate())) {
                    //return -1:即为正序排序
                    return -1;
                }else {
                    //return 1: 即为倒序排序
                    return 1;
                }
            }
        });


        return eachComment(list);
    }

    @Override
    public Comment saveComment(Comment comment) {
        /* Comment parentComment=comment.getParentComment();
        if (parentComment!=null){
            Long parentCommentId=comment.getParentComment().getId();
            comment.setParentComment(commentMapper.getCommentById(parentCommentId));
        }
        else {
            comment.setParentComment(null);
        }
        comment.setId(IdWorker.getId(Comment.class));
        comment.setCreateDate(DateUtil.getCurrentTime());*/
        int falg=commentMapper.saveComment(comment);
        if (falg==1){
            return comment;
        }else {
            return null;
        }
    }

    @Override
    public Comment getCommentById(Long id) {


        return commentMapper.getCommentById(id);
    }


    /*
    */
    public List<Comment> eachComment(List<Comment> list){
        for (Comment comment:list){
            List<Comment> replyComments=comment.getReplyComments();
            for (Comment replyComment:replyComments){
                recursively(replyComment);
            }
            comment.setReplyComments(temReply);
            temReply=new LinkedList<>();//清楚临时存放区
        }
        return list;
    }

    private List<Comment> temReply=new LinkedList<>();
    /*递归处理子回复*/
    public void recursively(Comment comment){
        temReply.add(comment);
        if (comment.getReplyComments().size()>0){
            List<Comment> replys =comment.getReplyComments();
            for (Comment reply:replys){
                temReply.add(reply);
                if (reply.getReplyComments().size()>0){
                    recursively(reply);
                }
            }
        }
    }

}
