package com.lizuo.pointcloud.service;

import com.lizuo.pointcloud.pojo.model.CloudPoint;
import org.springframework.cache.annotation.Cacheable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface CloudService {
    List<List<List<String>>> getCloud(String cloudcategory, String subdir) throws IOException;

    CloudPoint getCloud2(String cloudcategory, String subdir) throws IOException;
}
