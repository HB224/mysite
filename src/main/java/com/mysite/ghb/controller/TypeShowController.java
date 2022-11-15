package com.mysite.ghb.controller;

import com.github.pagehelper.PageInfo;
import com.mysite.ghb.entity.Blog;
import com.mysite.ghb.entity.Type;
import com.mysite.ghb.administrator.service.BlogService;
import com.mysite.ghb.administrator.service.TagService;
import com.mysite.ghb.administrator.service.TypeService;
import com.mysite.ghb.administrator.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/*
 */
@Controller
public class TypeShowController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;

    @GetMapping("/types/{id}")
    public String types(@RequestParam(value="pageNum", required=false, defaultValue ="1")int pageNum,
                        @PathVariable Long id, Model model){
        List<Type> types=typeService.listTypeTop(1000);
        if (types.isEmpty()){
            return "types";
        }
        if (id==-1){
            id=types.get(0).getId();
        }
        model.addAttribute("types", types);
        BlogQuery blogQuery=new BlogQuery();
        blogQuery.setTypeId(id);

        PageInfo<Blog> pageResult =  blogService.searchBlogByTypeId(pageNum,5,blogQuery);

        model.addAttribute("page", pageResult);
        model.addAttribute("currentTypeId",id);
        return "types";
    }

/*
    @GetMapping("/tags/{id}")
    public String tags(@RequestParam(value="pageNum", required=false, defaultValue ="1")int pageNum,
                        @PathVariable Long id, Model model){
        List<Tag> tags=tagService.listTag();
        if (id==-1){
            id=tags.get(0).getId();
        }
        model.addAttribute("tags", tags);
        BlogQuery blogQuery=new BlogQuery();
        blogQuery.setTypeId(id);

        PageInfo<Blog> pageResult =  blogService.searchBlogByTypeId(pageNum,5,blogQuery);

        model.addAttribute("page", pageResult);
        model.addAttribute("currentTagId",id);
        return "tags";
    }*/

    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("archiveMap",blogService.archiveBlog());
        model.addAttribute("blogCount",blogService.countBlog());
        return "archives";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

}
