package com.heima.service;

import com.heima.pojo.Article;
import com.heima.pojo.PageBean;

/**
 * @Auther: chenjia
 * @Date: 2023/12/1 - 12 - 01 - 17:21
 * @Description: com.heima.service.impl
 * @version: 1.0
 */
public interface ArticleService {
    //新增文章
    void add(Article article);

    //条件分页列表查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    //删除文章
    void delete(Integer id);

   void update(Article article);

    Article detail(Integer id);
}
