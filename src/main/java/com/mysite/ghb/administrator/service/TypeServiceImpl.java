package com.mysite.ghb.administrator.service;
/*
 */

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysite.ghb.entity.Type;
import com.mysite.ghb.administrator.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


@Service
public class TypeServiceImpl implements TypeService{
    @Autowired
    TypeMapper typeMapper;
    @Override
    public Type saveType(Type type) {
        if(type.getId()==null){
            type.setId(IdWorker.getId(Type.class));
        }
        int res=typeMapper.saveType(type);

        if (res==1){
            return type;
        }else {
            return null;
        }

    }

    @Override
    public Type getType(Long id) {
        return typeMapper.getType(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
    }

    @Override
    public PageInfo<Type> listType(int pageNum, int pageSize) {
        // TODO Auto-generated method stub
        PageHelper.startPage(pageNum, pageSize);
        List<Type> lists = typeMapper.listType();
        PageInfo<Type> pageInfo = new PageInfo<Type>(lists);
        return pageInfo;
    }

    @Override
    public Type updateType(Long id, Type type) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("id",id);
        map.put("type",type);
        int res=typeMapper.updateType(map);
        if (res==1){
            return type;
        }else {
            return null;
        }
    }

    @Override
    public void deleteType(Long id) {
        typeMapper.deleteType(id);
    }

    @Override
    public List<Type> listType() {
        return typeMapper.listType();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        List<Type> list=typeMapper.listTypeTop();
        /*将返回的数据进行排序（按照blog数量）*/
        Collections.sort(list, new Comparator<Type>() {
            @Override
            public int compare(Type t1, Type t2) {
                if(t1.getBlogs().size()>t2.getBlogs().size()) {
                    //return -1:即为正序排序
                    return -1;
                }else if (t1.getBlogs().size()==t2.getBlogs().size()) {
                    return 0;
                }else {
                    //return 1: 即为倒序排序
                    return 1;
                }
            }
        });
        if (list.size()>size) {
            return list.subList(0,size+1);
        } else {
            return list;
        }
    }
}
