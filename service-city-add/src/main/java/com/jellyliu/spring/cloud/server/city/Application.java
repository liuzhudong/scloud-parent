package com.jellyliu.spring.cloud.server.city;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * 城市数据服务(写入操作)
 *
 * @Creater liuzhudong
 * @Date 17/8/11 下午7:15
 * @Version 1.0
 * @Copyright
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class Application {

  //  EnableHystrix 开启接口调用熔断机制
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
