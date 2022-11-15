package com.mysite.ghb.administrator.service;
/*
 */

import com.github.pagehelper.PageInfo;
import com.mysite.ghb.entity.Tag;

import java.util.List;

public interface TagService {
    Tag saveTag(Tag type);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    PageInfo<Tag> listTag(int pageNum, int pageSize);

    List<Tag> listTag();

    List<Tag> listTag(String ids);

    Tag updateTag(Long id, Tag type);

    void deleteTag(Long id);
}
