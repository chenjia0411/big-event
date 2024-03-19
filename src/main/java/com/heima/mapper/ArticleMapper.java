package com.heima.mapper;

import com.heima.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Auther: chenjia
 * @Date: 2023/12/1 - 12 - 01 - 21:11
 * @Description: com.heima.mapper
 * @version: 1.0
 */
@Mapper
public interface ArticleMapper {
    //新增
    @Insert("insert into article(title, content, cover_img, state, category_id, create_user, create_time, update_time) " +
            "values (#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);


    //分页列表
    List<Article> list(Integer userId, Integer categoryId, String state);


    //删除文章
    @Delete("delete from article where id=#{id}")
    void delate(Integer id);

    //更新文章
    @Update("update article set title=#{title},content=#{content},cover_img=#{coverImg},state=#{state},category_id=#{categoryId},update_time=now() where id=#{id}")
    void update(Article article);

    @Select("select * from article where id=#{id}")
    Article detail(Integer id);
}
