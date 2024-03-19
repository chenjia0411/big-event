import request from '@/utils/request.js'
import {useTokenStore} from '@/stores/token.js'


//文章分类列表查询
export const articleCategoryListService =() =>{
 /*    const TokenStore = useTokenStore();
    //在pinia中定义的响应式数据，都不需要.value
    return  request.get('/category',{headers:{'Authorization':TokenStore.token}}) */
    return  request.get('/category')
}


//文章分类添加
export const articleCategoryAddService=(category)=>{
     return request.post('/category',category)
}

//文章分类修改
export const articleCategoryUpdateService =(categoryDate) =>{
   return request.put('/category',categoryDate)
} 

//文章分类删除
export const articleCategoryDeleteService = (id)=>{
    return request.delete('/category?id='+id)
}

//文章列表查询
export const articleListService = (params)=>{
    return request.get('/article',{params})
}

//文章添加
export const articleAddService =(articleData)=>{
    return request.post('/article',articleData)
}

//文章修改
export const articleUpdateService = (articleModel)=>{
    return request.put('/article',articleModel)
}

//文章删除
export const articleDeleteService =(id)=>{
    return request.delete('/article?id='+id)
}