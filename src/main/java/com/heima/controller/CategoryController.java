package com.heima.controller;

import com.heima.pojo.Category;
import com.heima.pojo.Result;
import com.heima.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: chenjia
 * @Date: 2023/11/28 - 11 - 28 - 0:39
 * @Description: com.heima.controller
 * @version: 1.0
 */

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //添加
    @PostMapping
    public Result add(@RequestBody @Validated(Category.Add.class) Category category){
        categoryService.add(category);
        return Result.success();
    }
    //查询
    @GetMapping
    public Result<List<Category>> list(){
        List<Category> cs=categoryService.list();
        return Result.success(cs);
    }
    //查询单个
    @GetMapping("/detail")
    public Result<Category> detail(Integer id){
        Category c= categoryService.findById(id);
        return Result.success(c);
    }

    //修改
    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category){
        categoryService.update(category);
        return Result.success();
    }

    //删除
    @DeleteMapping
    public Result delete(Integer id){
        categoryService.delete(id);
        return Result.success();
    }
}
