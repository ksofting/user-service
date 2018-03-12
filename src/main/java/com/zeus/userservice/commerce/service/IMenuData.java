/*
 * 文 件 名:  IMenuData.java
 * 版    权:  Copyright © 2015-2017, 湖南物联聚创信息科技有限公司
 * 描    述:  IMenuData.java 
 * 版    本：   1.0 
 * 创 建 人:  HuangChao
 * 创建时间: 2017年11月2日 下午3:42:54
*/
package com.zeus.userservice.commerce.service;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.zeus.common.service.IMenuService;

/** 
* @ClassName: IMenuData
* @Description: 菜单数据服务接口调用定义接口
* @author HuangChao 2017年11月2日 下午3:42:54
* 
*/
@FeignClient(value= "user-data",fallback= MenuDataFallback.class)
public interface IMenuData extends IMenuService{
    
}
