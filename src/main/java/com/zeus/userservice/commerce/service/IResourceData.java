/*
 * 文 件 名:  IResourceData.java
 * 版    权:  Copyright © 2015-2017, 湖南物联聚创信息科技有限公司
 * 描    述:  IResourceData.java
 * 版    本：   1.0 
 * 创 建 人:  lifeng
 * 创建时间: 2017年11月3日 下午1:49:47
*/
package com.zeus.userservice.commerce.service;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.zeus.common.service.IResourceService;

/**
 * 
 * @ClassName: IResourceData
 * @Description: 资源接口实现类
 * @author lifeng 2017年11月3日 下午1:54:16
 *
 */
@FeignClient(value = "user-data", fallback = ResourceDataFallback.class)
public interface IResourceData extends IResourceService {

}
