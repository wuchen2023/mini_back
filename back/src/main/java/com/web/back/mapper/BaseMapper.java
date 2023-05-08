package com.web.back.mapper;

/**
 * @author by hongdou
 * @date 2023/5/8.
 * @DESC:
 */
public interface BaseMapper<T> {
    int deleteByPrimaryKey(Integer id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
