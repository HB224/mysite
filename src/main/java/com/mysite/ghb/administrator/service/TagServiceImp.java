package com.mysite.ghb.administrator.service;
/*
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysite.ghb.entity.Tag;
import com.mysite.ghb.administrator.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
@Service
public class TagServiceImp implements TagService{
    @Autowired
    TagMapper tagMapper;
    @Override
    public Tag saveTag(Tag Tag) {
        int res=tagMapper.saveTag(Tag);
        if (res==1){
            return Tag;
        }else {
            return null;
        }
    }

    @Override
    public Tag getTag(Long id) {
        return tagMapper.getTag(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }

    @Override
    public PageInfo<Tag> listTag(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Tag> lists = tagMapper.listTag();
        PageInfo<Tag> pageInfo = new PageInfo<Tag>(lists);
        return pageInfo;
    }

    @Override
    public List<Tag> listTag() {
        return tagMapper.listTag();
    }

    @Override
    public List<Tag> listTag(String ids) {
        return null;
    }

    @Override
    public Tag updateTag(Long id, Tag Tag) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("id",id);
        map.put("Tag",Tag);
        int res=tagMapper.updateTag(map);
        if (res==1){
            return Tag;
        }else {
            return null;
        }
    }

    @Override
    public void deleteTag(Long id) {
        tagMapper.deleteTag(id);
    }
}
