package com.heima.service.impl;

/*import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;*/

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.heima.mapper.ArticleMapper;
import com.heima.pojo.Article;
import com.heima.pojo.PageBean;
import com.heima.service.ArticleService;
import com.heima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @Auther: chenjia
 * @Date: 2023/12/1 - 12 - 01 - 17:21
 * @Description: com.heima.service.impl
 * @version: 1.0
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public void add(Article article) {
        //补充属性值
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        //1.创建PageBean对象
        PageBean<Article> pb =new PageBean<>();

        //2.开启分页查询
        PageHelper.startPage(pageNum,pageSize);

        //3.调用mapper
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        //Page中提供了方法，可以获取PageHelper分页查询后，得到的总记录条数和当前页数据
        List<Article> as=articleMapper.list(userId,categoryId,state);

        Page<Article> p=(Page<Article>) as;

        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public void delete(Integer id) {
        articleMapper.delate(id);
    }

    @Override
    public void update(Article article) {
        articleMapper.update(article);

    }

    @Override
    public Article detail(Integer id) {
        Article ac = articleMapper.detail(id);
        return ac;
    }
}
