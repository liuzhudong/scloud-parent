package com.jellyliu.spring.cloud.server.city.dao;

import com.jellyliu.spring.cloud.server.city.entity.CityInfo;

/**
 * @Creater liuzhudong
 * @Date 17/8/11 下午8:02
 * @Version 1.0
 * @Copyright
 */
public interface CityDao {

  /**
   * 新增城市
   */
  void add(CityInfo cityInfo);

}
