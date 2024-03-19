package com.heima.mapper;

import com.heima.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Auther: chenjia
 * @Date: 2023/11/28 - 11 - 28 - 2:07
 * @Description: com.heima.mapper
 * @version: 1.0
 */
@Mapper
public interface CategoryMapper {
    //新增
    @Insert("insert into category(category_name, category_alias, create_user, create_time, update_time) " +
            "values (#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void add(Category category);

    //查询所有
    @Select("select * from category where create_user = #{userId}")
    List<Category> list(Integer userId);

    //根据id查询
    @Select("Select * from category where id =#{id}")
    Category findById(Integer id);

    //更新
    @Update("update category set category_name=#{categoryName},category_alias=#{categoryAlias}," +
            "update_time=#{updateTime} where id =#{id}")
    void update(Category category);

    //删除文章分类
    @Delete("delete from category where id=#{id}")
    void delate(Integer id);
}
