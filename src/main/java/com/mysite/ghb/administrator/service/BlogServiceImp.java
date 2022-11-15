package com.mysite.ghb.administrator.service;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysite.ghb.entity.Blog;
import com.mysite.ghb.administrator.mapper.BlogMapper;
import com.mysite.ghb.administrator.vo.BlogQuery;
import com.mysite.ghb.util.DateUtil;
import com.mysite.ghb.util.MarkdownUtil;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 */
@Service
public class BlogServiceImp implements  BlogService{
    @Autowired
    BlogMapper blogMapper;
    @Override
    public Blog getBlog(Long id) {
        return blogMapper.getBlog(id);
    }

    @Override
    public PageInfo<Blog> listBlog(int pageNum, int pageSize) {
        String orderBy = "'updateTime'  desc ";//按照排序字段 倒序 排序
        PageHelper.startPage(pageNum, pageSize,orderBy);
        List<Blog> lists = blogMapper.listBlog();
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(lists);
        return pageInfo;

    }

    @Override
    public PageInfo<Blog> searchBlog(int pageNum, int pageSize, BlogQuery blogQuery) {
        PageHelper.startPage(pageNum, pageSize);
       // System.out.println(blogQuery);
        List<Blog> lists = blogMapper.searchBlog(blogQuery);
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(lists);
        return pageInfo;
    }

    @Override
    public PageInfo<Blog> listBlog(String query, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> lists = blogMapper.searchBlogByString(query);
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(lists);
        return pageInfo;
    }

    @Override
    public PageInfo<Blog> searchBlogByTypeId(int pageNum, int pageSize, BlogQuery blogQuery) {
        PageHelper.startPage(pageNum, pageSize);
       // System.out.println(blogQuery);
        List<Blog> lists = blogMapper.searchBlogByTypeId(blogQuery);
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(lists);
        return pageInfo;
    }

    @Override
    public Blog saveBlog(Blog blog) {
        if(blog.getId()==null) {
            blog.setId(IdWorker.getId(Blog.class));
        }
        if(blog.getCreateDate()==null) {
            blog.setCreateDate(DateUtil.getCurrentTime());
        }
        if(blog.getUpdateTime()==null) {
            blog.setUpdateTime(DateUtil.getCurrentTime());
        }
        int res=blogMapper.saveBlog(blog);
        if (res==1){
            return blog;
        }else {
            return null;
        }
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {
        blog.setUpdateTime(DateUtil.getCurrentTime());
        HashMap<String,Object> map=new HashMap<>();
        map.put("id",id);
        map.put("blog",blog);
        int res=blogMapper.updateBlog(map);
        if (res==1){
            return blog;
        }else {
            return null;
        }
    }

    @Override
    public void deleteBlog(Long id) {
        blogMapper.deleteBlog(id);

    }

    @Override
    public  PageInfo<Blog> listRecommendBlogTop(Integer size) {
        String orderBy = "'updateTime'  desc ";//按照排序字段 倒序 排序
        PageHelper.startPage(1, 8,orderBy);
        List<Blog> lists = blogMapper.listRecommendBlogTop();
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(lists);
        return pageInfo;
    }

    @Override
    public Blog getBlogAndConvert(Long id) throws NotFoundException {
        Blog blog=blogMapper.getBlog(id);
        if (blog==null){
            throw new NotFoundException("该博客不存在");
        }
        String content=blog.getContent();
        content=MarkdownUtil.markdownToHtmlExtensions(content);
        blog.setContent(content);
        return blog;
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years=blogMapper.getGroupYears();
        Map<String,List<Blog>> map=new HashMap<>();
        for (String year:years){
            map.put(year,blogMapper.listBlogByYear(year));
        }
        return map;
    }

    @Override
    public int countBlog() {
        return blogMapper.countBlog();
    }
}
