package com.lizuo.pointcloud.service.impl;

import com.lizuo.pointcloud.pojo.model.CloudPoint;
import com.lizuo.pointcloud.service.CloudService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CloudServiceImpl implements CloudService {
    @Override
    public List<List<List<String>>> getCloud(String cloudcategory, String subdir) throws IOException {
        String dir = "/home/lizuo/data/pointconv_pytorch-master/system/"+subdir+cloudcategory;
        String filedir = dir+".txt";
        String encoding="GBK";
        File file=new File(filedir);
        List<List<List<String>>> cloudpoint = new ArrayList<>();
        if(file.isFile() && file.exists()){
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
            BufferedReader bufferedReader = new BufferedReader(read);
            String line = null;
//            while ((line = bufferedReader.readLine()) != null) {
            int num = 40;
            if(subdir.equals("classification/")) num=20;
            for(int j=0;j<num;j++){
                List<List<String>> cloud = new ArrayList<>();
//                cloud.add(Arrays.asList(line.split(" ")));
                for(int i = 0;i<2048;i++){
                    line = bufferedReader.readLine();
                    List<String> point = Arrays.asList(line.split(" "));
                    cloud.add(point);
                }
                cloudpoint.add(cloud);
            }

            read.close();
        }
        return cloudpoint;
    }

    @Override
    @Cacheable(value = "cloudpoint")
    public CloudPoint getCloud2(String cloudcategory, String subdir) throws IOException {
        String dir = "/home/lizuo/data/pointconv_pytorch-master/system/"+subdir+cloudcategory;
        String filedir = dir+".txt";
        String encoding="GBK";
        File file=new File(filedir);
        List<List<List<String>>> cloudpoint = new ArrayList<>();
        if(file.isFile() && file.exists()){
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
            BufferedReader bufferedReader = new BufferedReader(read);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                List<List<String>> cloud = new ArrayList<>();
                cloud.add(Arrays.asList(line.split(" ")));
                for(int i = 0;i<2047;i++){
                    line = bufferedReader.readLine();
                    List<String> point = Arrays.asList(line.split(" "));
                    cloud.add(point);
                }
                cloudpoint.add(cloud);
            }

            read.close();
        }
        CloudPoint cp = new CloudPoint();
        cp.setCloudpoint(cloudpoint);
        return cp;
    }
}
