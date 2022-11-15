package com.mysite.ghb.controller;/*
 * @author GongHb
*/

import com.github.pagehelper.PageInfo;
import com.mysite.ghb.entity.Blog;
import com.mysite.ghb.administrator.service.BlogService;
import com.mysite.ghb.administrator.service.TagService;
import com.mysite.ghb.administrator.service.TypeService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
   @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    /*首页展示*/
   @GetMapping(value={"/" ,"/index","/index.html"})
    public String index( @RequestParam(value="pageNum", required=false, defaultValue ="1")int pageNum,
                         Model model) {
           PageInfo<Blog> pageResult =  blogService.listBlog(pageNum, 5);
           model.addAttribute("page", pageResult);
           model.addAttribute("types", typeService.listTypeTop(6));
      // System.out.println(typeService.listTypeTop(6));
           model.addAttribute("tags", tagService.listTag());
           PageInfo<Blog> recommendResult =  blogService.listRecommendBlogTop(5);
           model.addAttribute("recommendBlogs",recommendResult);
           return "index";
   }
    @PostMapping("/search")
    public String search(@RequestParam(value="pageNum", required=false, defaultValue ="1")int pageNum,
                         @RequestParam String query, Model model) {
        PageInfo<Blog> pageResult =  blogService.listBlog('%'+query+'%',pageNum, 5);
        model.addAttribute("page", pageResult);
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) throws NotFoundException {
        model.addAttribute("blog", blogService.getBlogAndConvert(id));
        return "blog";
    }

/*    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        PageInfo<Blog> newblogs = blogService.listRecommendBlogTop(3);
        System.out.println(newblogs);
        model.addAttribute("newblogs",newblogs);
        return "_fragments :: newblogList";
    }*/

}
