/*
 * 文 件 名: IUserRoelData.java 版 权: Copyright © 2015-2017, 湖南物联聚创信息科技有限公司 描 述: IUserRoelData.java 版 本： 1.0 创 建 人:
 * lidesheng 创建时间: 2017年10月27日 下午2:13:12
 */
package com.zeus.userservice.commerce.service;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.zeus.common.service.IUserRoleService;

/**
 * @ClassName: IUserRoelData
 * @Description: 用户角色关联接口实现类
 * @author lidesheng 2017年10月27日 下午2:13:12
 * 
 */
@FeignClient(value= "user-data",fallback= UserRoleDataFallback.class)
public interface IUserRoleData extends IUserRoleService{
    
}
