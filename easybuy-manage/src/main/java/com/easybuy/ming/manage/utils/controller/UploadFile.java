package com.easybuy.ming.manage.utils.controller;

import com.easybuy.ming.utils.JsonUtils;
import com.easybuy.ming.pojo.PictureResult;
import org.csource.common.NameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by ming on 2016/10/31.
 */
@Controller
@RequestMapping("/upload")
public class UploadFile {



    @RequestMapping("/userLogo.do")
    @ResponseBody
    public String  uploadUserLogo(@RequestParam("photo") MultipartFile uploadFile){
        String name = uploadFile.getName();
        //上传的文件名
        String originalFilename = uploadFile.getOriginalFilename();
        String extName=originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        PictureResult result=new PictureResult();
        String path="";
        try {
            //获取文件输入流
            byte[] bytes = uploadFile.getBytes();
            FastDFSClient dfsClient=new FastDFSClient("classpath:/properties/fdfs_client.conf");
            NameValuePair nvp [] = new NameValuePair[]{
                    new NameValuePair("item_id", "100010"),
                    new NameValuePair("width", "80"),
                    new NameValuePair("height", "90")
            };
             path = dfsClient.uploadFile(bytes, extName, nvp);
            result.setUrl(path);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("上传图片失败");
            result.setError("1");
        }
        return JsonUtils.objectsToJson(result);
    }

}
