package com.jellyliu.spring.cloud.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @Creater liuzhudong
 * @Date 17/7/17 下午7:19
 * @Version 1.0
 * @Copyright
 */
@RestController
@SpringBootApplication
public class Application {

  @Value("${user.name}")
  String name = "World";

  @RequestMapping("/")
  public String home() {
    return "Hello " + name;
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
