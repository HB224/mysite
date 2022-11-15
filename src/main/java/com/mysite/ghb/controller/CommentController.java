package com.mysite.ghb.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.mysite.ghb.entity.Blog;
import com.mysite.ghb.entity.Comment;
import com.mysite.ghb.administrator.service.BlogService;
import com.mysite.ghb.administrator.service.CommentService;

import com.mysite.ghb.service.UserService;
import com.mysite.ghb.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){

        model.addAttribute("comments",commentService.listCommentByBlogId(blogId));
        return "blog::commentList";
    }

    @PostMapping("/comments")
    public String postSave(@RequestParam("parentCommentId") Long parentCommentId,
                           @RequestParam("blogId") Long blogId,
                           @RequestParam("content") String content,
                           @RequestParam("userid") String userid){
        Comment comment=new Comment();
        Comment parentComment=commentService.getCommentById(Long.valueOf(parentCommentId));
        if (parentComment==null) {
            parentComment = new Comment();
            parentComment.setId(-1L);
        }
        comment.setId(IdWorker.getId(Comment.class));
        comment.setParentComment(parentComment);
        comment.setCreateDate(DateUtil.getCurrentTime());
        comment.setContent(content);
        comment.setBlog(blogService.getBlog(Long.valueOf(blogId)));
        comment.setUser(userService.getUserByid(Long.valueOf(userid)));
        commentService.saveComment(comment);
        return "redirect:/comments/"+blogId;
    }

/*    @GetMapping("/test")
    @ResponseBody
    public String test(){
        Long id=1354977782910414850L;
        Comment comment =new Comment();
        comment.setContent("sfds");

        Blog blog =new Blog();
        blog.setId(1354977782910414850L);
        comment.setBlog(blog);

       // System.out.println(comment.getBlog().getId());
        return commentService.saveComment(comment).toString();
    }*/
}
