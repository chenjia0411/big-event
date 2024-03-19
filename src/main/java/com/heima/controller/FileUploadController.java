package com.heima.controller;

import com.heima.pojo.Result;
import com.heima.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @Auther: chenjia
 * @Date: 2023/12/5 - 12 - 05 - 15:08
 * @Description: com.heima.controller
 * @version: 1.0
 */
@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        //把文件的内容存储到本地磁盘上
        String originalFilename = file.getOriginalFilename(); //得到文件的原始文件名，
        //原始文件名指的是用户上传文件时的文件名，而不是文件在服务器上保存的名字
        //保证文件的名字是唯一的，从而防止文件覆盖
        String filename= UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        //file.transferTo(new File("C:\\Users\\86151\\Desktop\\files\\"+filename));
        String url = AliOssUtil.uploadFile(filename,file.getInputStream());
        return Result.success(url);
    }
}
