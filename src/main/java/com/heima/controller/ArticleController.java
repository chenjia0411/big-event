package com.heima.controller;

import com.heima.pojo.Article;
import com.heima.pojo.PageBean;
import com.heima.pojo.Result;
import com.heima.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: chenjia
 * @Date: 2023/11/23 - 11 - 23 - 23:52
 * @Description: com.heima.controller
 * @version: 1.0
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    //发布文章
    @PostMapping
    public Result add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();
    }

    //文章列表（条件分页）
    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId, //非必须请求参数
            @RequestParam(required = false) String  state       //非必须请求参数
    ){
        PageBean<Article> pb=articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pb);
    }
    //删除文章
    @DeleteMapping
    public Result delete(Integer id){
        articleService.delete(id);
        return Result.success();
    }

    //更新文章
    @PutMapping
    public Result update(@RequestBody @Validated Article article){
        articleService.update(article);
        return Result.success();
    }

    @GetMapping("/detail")
    public Result detail(Integer id){
        Article ac=articleService.detail(id);
        return  Result.success(ac);
    }
}
