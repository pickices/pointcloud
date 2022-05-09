package com.lizuo.pointcloud.pojo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CloudPoint implements Serializable {
    private List<List<List<String>>> cloudpoint = new ArrayList<>();

    public List<List<List<String>>> getCloudpoint() {
        return cloudpoint;
    }

    public void setCloudpoint(List<List<List<String>>> cloudpoint) {
        this.cloudpoint = cloudpoint;
    }
}
