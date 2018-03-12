/*
 * 文 件 名:  IUserData.java
 * 版    权:  Copyright © 2015-2017, 湖南物联聚创信息科技有限公司
 * 描    述:  IUserData.java 
 * 版    本：   1.0 
 * 创 建 人:  lidesheng
 * 创建时间: 2017年10月31日 下午4:04:18
*/
package com.zeus.userservice.commerce.service;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.zeus.common.service.IUserService;

/**
 * 
 * @ClassName: IUserData
 * @Description: 用户管理接口定义
 * @author lidesheng 2017年11月1日 下午4:22:47
 *
 */
@FeignClient(value= "user-data",fallback= UserDataFallback.class)
public interface IUserData extends IUserService{
    
}
