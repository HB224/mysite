package com.mysite.ghb.administrator.mapper;


import com.mysite.ghb.entity.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/*
 */

@Mapper
public interface TypeMapper {
    int saveType(Type type);
    Type getType(Long id);
    List<Type> listType();

    int updateType(Map map);

    void deleteType(Long id);

    Type getTypeByName(String name);
/*按照blog数量查询分类*/
    List<Type> listTypeTop();
}
