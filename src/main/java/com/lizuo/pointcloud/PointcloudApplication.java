package com.lizuo.pointcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableCaching
@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
public class PointcloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(PointcloudApplication.class, args);
    }

}
