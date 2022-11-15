package com.mysite.ghb.administrator.service;
/*
 */

import com.github.pagehelper.PageInfo;
import com.mysite.ghb.entity.Blog;
import com.mysite.ghb.administrator.vo.BlogQuery;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;
import java.util.Map;

public interface BlogService {
    Blog getBlog(Long id);

    PageInfo<Blog> listBlog(int pageNum, int pageSize);//返回所有blog
    PageInfo<Blog> searchBlog(int pageNum, int pageSize, BlogQuery blogQuery);//返回搜索blog,按分类标签等
    PageInfo<Blog> listBlog(String query,int pageNum, int pageSize);//返回搜索的字符串blog
    PageInfo<Blog> searchBlogByTypeId(int pageNum, int pageSize, BlogQuery blogQuery);//返回搜索blog,按分类标签等

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);

    /*推荐页按更新时间排序*/
    PageInfo<Blog> listRecommendBlogTop(Integer size);
/*找到并把blog内容转换成makedown格式*/
    Blog getBlogAndConvert(Long id) throws NotFoundException;

    /*按年份归档*/
    Map<String,List<Blog>> archiveBlog();

    int countBlog();
}
