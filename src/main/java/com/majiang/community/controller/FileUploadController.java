package com.majiang.community.controller;

import com.majiang.community.dto.FileDTO;
import com.majiang.community.provider.UCloudProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class FileUploadController {

    @Autowired
    private UCloudProvider uCloudProvider;

    @RequestMapping(value = "/file/upload")
    @ResponseBody
    public Object upload(HttpServletRequest request){

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;

        MultipartFile file = multipartRequest.getFile("editormd-image-file");

        InputStream inputStream = null;
        String upload = "";
        try {
            inputStream = file.getInputStream();
            String contentType = file.getContentType();
            String name = file.getOriginalFilename();
            upload = uCloudProvider.upload(inputStream, contentType, name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileDTO fileDTO = new FileDTO();
        fileDTO.setMessage("成功");
        fileDTO.setSuccess(1);
        fileDTO.setUrl(upload);
        return fileDTO;
    }

}
