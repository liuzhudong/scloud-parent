package com.jellyliu.spring.cloud.server.city.entity;

import java.util.Date;

/**
 * @Creater liuzhudong
 * @Date 17/8/11 下午8:04
 * @Version 1.0
 * @Copyright
 */
public class CityInfo {

  private long id;
  private String name;
  private long provinceId;
  private int state = 1;
  private Date createTime;
  private Date updateTime;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getProvinceId() {
    return provinceId;
  }

  public void setProvinceId(long provinceId) {
    this.provinceId = provinceId;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }
}
