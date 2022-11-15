package com.mysite.ghb.administrator.mapper;

import com.mysite.ghb.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/*
 */

@Mapper
public interface TagMapper {
    int saveTag(Tag Tag);
    Tag getTag(Long id);
    List<Tag> listTag();

    int updateTag(Map map);

    void deleteTag(Long id);

    Tag getTagByName(String name);
}
