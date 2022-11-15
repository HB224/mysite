package com.mysite.ghb.administrator.controller;


import com.github.pagehelper.PageInfo;
import com.mysite.ghb.entity.Blog;
import com.mysite.ghb.administrator.vo.BlogQuery;
import com.mysite.ghb.administrator.service.BlogService;
import com.mysite.ghb.administrator.service.TagService;
import com.mysite.ghb.administrator.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/*
 */
@Controller
@RequestMapping("/admin")
public class BlogController {



    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(@RequestParam(value="pageNum", required=false, defaultValue ="1")int pageNum,
                        BlogQuery blog, Model model) {
        PageInfo<Blog> queryResult =  blogService.listBlog(pageNum, 7);
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", queryResult);
        return "admin/blogs";
    }


    @PostMapping("/blogs/search")
    public String search(@RequestParam(value="pageNum", required=false, defaultValue ="1")int pageNum,
                         BlogQuery blogQuery, Model model) {
        PageInfo<Blog> queryResult =  blogService.searchBlog(pageNum, 7,blogQuery);
        model.addAttribute("page", queryResult);
        return "admin/blogs::blogList";
    }


    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        model.addAttribute("blog", new Blog());
        return "admin/blogs-input";
    }




    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        return "admin/blogs-input";
    }



    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
       // blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
       // System.out.println(blog.toString());
        Blog b;
        if (blog.getId() == null) {
            b =  blogService.saveBlog(blog);
        } else {
            b = blogService.updateBlog(blog.getId(), blog);
        }

        if (b == null ) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/blogs";
    }


    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }
}
