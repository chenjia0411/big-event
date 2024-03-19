package com.heima.service;

import com.heima.pojo.Category;

import java.util.List;

/**
 * @Auther: chenjia
 * @Date: 2023/11/28 - 11 - 28 - 1:03
 * @Description: com.heima.service
 * @version: 1.0
 */
public interface CategoryService {
    //新增分类
    void add(Category category);

    //列表查询
    List<Category> list();

    //根据id查询分类信息
    Category findById(Integer id);

    //更新分类
    void update(Category category);

    //删除文章分类
    void delete(Integer id);
}
