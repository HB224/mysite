package com.mysite.ghb.administrator.service;
/*
 */

import com.github.pagehelper.PageInfo;
import com.mysite.ghb.entity.Type;

import java.util.List;


public interface TypeService {
    Type saveType(Type type);
    Type getType(Long id);
    Type getTypeByName(String name);
    PageInfo<Type> listType(int pageNum, int pageSize);


    Type updateType(Long id,Type type);

    void deleteType(Long id);

    List<Type> listType();

    /*按分类下blog数进行排序*/
    List<Type> listTypeTop(Integer size);
}
