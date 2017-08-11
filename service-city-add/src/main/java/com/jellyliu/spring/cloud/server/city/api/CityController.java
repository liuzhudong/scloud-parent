package com.jellyliu.spring.cloud.server.city.api;

import com.jellyliu.spring.cloud.server.city.entity.CityInfo;
import com.jellyliu.spring.cloud.server.city.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @Creater liuzhudong
 * @Date 17/8/11 下午8:13
 * @Version 1.0
 * @Copyright
 */
@RestController
@RequestMapping("api/city")
public class CityController {

  @Autowired
  private CityService cityService;

  @RequestMapping("new")
  public String add(CityInfo cityInfo) {
    cityService.add(cityInfo);
    return "OK";
  }

}
