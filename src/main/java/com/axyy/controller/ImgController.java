package com.axyy.controller;

import com.axyy.service.ImgService;
import com.axyy.util.RequestResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * @date 2020/4/15--14:19
 */
@Slf4j
@RestController
@RequestMapping("img")
public class ImgController {

    @Resource
    private ImgService imgService;

    @Value("${web.upload-path}")
    String filePath;

    @ApiOperation("上传图片")
    @PostMapping("upload")
    public RequestResult upload(Integer type, Long foreignid, MultipartFile file, Model model, HttpServletRequest request) {
        log.info("[upload]");
        if (file.isEmpty()) {
            System.out.println("文件为空");
        }
        File file2 = new File(filePath);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        String originalFileName = file.getOriginalFilename();//获取原始图片的扩展名
        String newFileName = UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
        String newFilePath = filePath + newFileName; //新文件的路径
        try {
            file.transferTo(new File(newFilePath));//将传来的文件写入新建的文件
            System.out.println("上传图片成功进行上传文件测试");
        } catch (Exception e) {
            //处理异常
            log.error("[upload img error],error={}", e);
            return RequestResult.ERROR("添加失败");
        }

        imgService.insert(type, foreignid, newFileName);


        return RequestResult.SUCCESS("", newFileName);
    }

    @ApiOperation("uploadSome")
    @PostMapping("uploadSome")
    public RequestResult uploadSome(Integer type, Integer foreignid, MultipartFile[] files, Model model, HttpServletRequest request) {
        log.info("[uploadSome]");
        if (files.length < 0) {
            return RequestResult.ERROR("没有图片");
        }
        File file2 = new File(filePath);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String originalFileName = file.getOriginalFilename();//获取原始图片的扩展名
            String newFileName = UUID.randomUUID() + originalFileName;
            String newFilePath = filePath + newFileName; //新文件的路径
            try {
                file.transferTo(new File(newFilePath));//将传来的文件写入新建的文件
            } catch (Exception e) {
                //处理异常
                log.error("[upload img error],error={}", e);
            }
        }
        return RequestResult.SUCCESS();
    }

    @ApiOperation("获取某类型的图片")
    @GetMapping("getImgs")
    public List getImgs(int type, String foreignId) {
        List imgs = imgService.getImgs(type, foreignId);
        return imgs;
    }
}
