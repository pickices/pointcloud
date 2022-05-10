package com.lizuo.pointcloud.controller;

import com.lizuo.pointcloud.pojo.model.CloudPoint;
import com.lizuo.pointcloud.service.CloudService;
import com.lizuo.pointcloud.util.DownloadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class CloudController {

    @Autowired
    CloudService cloudService;

    @ResponseBody
    @GetMapping("/show")
    @CrossOrigin(origins = "*")
    public List<List<List<String>>> show(String category) throws IOException {
        String subdir = "classification/";
        return cloudService.getCloud(category,subdir);
    }

    @ResponseBody
    @GetMapping("/retrieval")
    @CrossOrigin(origins = "*")
    public List<List<List<String>>> retrieval(int pc) throws IOException {
        String cloudcategory = new String();
        String subdir = "retrieval/";
        switch (pc){
            case 1:cloudcategory = "airplane";
            case 2:cloudcategory = "chair";
            case 3:cloudcategory = "guitar";
            case 4:cloudcategory = "sofa";
            case 5:cloudcategory = "toilet";
        }
        return cloudService.getCloud(cloudcategory,subdir);
    }

    @ResponseBody
    @GetMapping("/show2")
    @CrossOrigin(origins = "*")
    public CloudPoint show2(String category,int page) throws IOException {
        String subdir = "classification/";
        return cloudService.getCloud2(category,page,subdir);
    }

    @ResponseBody
    @GetMapping("/retrieval2")
    @CrossOrigin(origins = "*")
    public CloudPoint retrieval2(String cloudcategory,int page) throws IOException {
        String subdir = "retrieval/";
        return cloudService.getCloud2(cloudcategory,page,subdir);
    }

    @ResponseBody
    @GetMapping("/classdownload")
    @CrossOrigin(origins = "*")
    public void download(String category, int page, int num,HttpServletResponse response) throws Throwable {
        String number = String.valueOf((page-1)*6+num);
        String dir = "/home/lizuo/data/pointconv_pytorch-master/system/classification/"+category+"/"+category+number+".txt";
        File file = new File(dir);
        if (!file.exists()) {
            return;
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        // 设置编码格式
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + URLEncoder.encode(file.getName(), "UTF-8"));
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        byte[] buff = new byte[1024];
        OutputStream os = response.getOutputStream();
        int i = 0;
        while ((i = bis.read(buff)) != -1) {
            os.write(buff, 0, i);
            os.flush();
        }
        bis.close();
        os.close();
    }
}
