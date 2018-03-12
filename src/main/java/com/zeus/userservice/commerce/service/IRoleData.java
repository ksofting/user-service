/*
 * 文 件 名:  IRoleData.java
 * 版    权:  Copyright © 2015-2017, 湖南物联聚创信息科技有限公司
 * 描    述:  IRoleData.java 
 * 版    本：   1.0 
 * 创 建 人:  lifeng
 * 创建时间: 2017年11月3日 下午1:49:47
*/
package com.zeus.userservice.commerce.service;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.zeus.common.service.IRoleService;

/**
 * 
 * @ClassName: IRoleData
 * @Description: 角色接口实现类
 * @author lifeng 2017年11月3日 下午1:53:40
 *
 */
@FeignClient(value= "user-data",fallback= RoleDataFallback.class)
public interface IRoleData extends IRoleService{
    
}
