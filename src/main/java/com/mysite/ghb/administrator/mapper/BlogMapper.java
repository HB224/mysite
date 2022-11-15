package com.mysite.ghb.administrator.mapper;
/*
 */

import com.mysite.ghb.entity.Blog;
import com.mysite.ghb.administrator.vo.BlogQuery;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;
import java.util.Map;

@Mapper
public interface BlogMapper {
    Blog getBlog(Long id);

    List<Blog> listBlog();

    int saveBlog(Blog blog);

    List<Blog> searchBlog(BlogQuery blogQuery);
    List<Blog> searchBlogByTypeId(BlogQuery blogQuery);

    int updateBlog(Map map);

    void deleteBlog(Long id);

    /*查询是推荐的blog*/
    List<Blog> listRecommendBlogTop();

    List<Blog> searchBlogByString(String  query);


    List<String> getGroupYears();
    List<Blog> listBlogByYear(String year);
    int countBlog();
}
