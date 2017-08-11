package com.jellyliu.spring.cloud.server.city.service;

import com.jellyliu.spring.cloud.server.city.dao.CityDao;
import com.jellyliu.spring.cloud.server.city.entity.CityInfo;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @Creater liuzhudong
 * @Date 17/8/11 下午8:12
 * @Version 1.0
 * @Copyright
 */
@Service
public class CityService {

  @Autowired
  private CityDao cityDao;

  public void add(CityInfo cityInfo) {
    cityInfo.setCreateTime(new Date());
    cityDao.add(cityInfo);
  }

}
