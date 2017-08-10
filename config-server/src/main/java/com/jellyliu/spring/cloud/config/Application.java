package com.jellyliu.spring.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置服务
 *
 * @Creater liuzhudong
 * @Date 17/7/17 下午4:31
 * @Version 1.0
 * @Copyright
 */

@SpringBootApplication
@EnableConfigServer
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
