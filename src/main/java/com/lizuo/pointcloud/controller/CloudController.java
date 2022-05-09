package com.lizuo.pointcloud.controller;

import com.lizuo.pointcloud.pojo.model.CloudPoint;
import com.lizuo.pointcloud.service.CloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
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
    public CloudPoint show2(String category) throws IOException {
        String subdir = "classification/";
        return cloudService.getCloud2(category,subdir);
    }

    @ResponseBody
    @GetMapping("/retrieval2")
    @CrossOrigin(origins = "*")
    public CloudPoint retrieval2(int pc) throws IOException {
        String cloudcategory = new String();
        String subdir = "retrieval/";
        switch (pc){
            case 1:cloudcategory = "airplane";
            case 2:cloudcategory = "chair";
            case 3:cloudcategory = "guitar";
            case 4:cloudcategory = "sofa";
            case 5:cloudcategory = "toilet";
        }
        return cloudService.getCloud2(cloudcategory,subdir);
    }
}
