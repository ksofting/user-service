/*
 * 文 件 名: UserRoleDataFallback.java 版 权: Copyright © 2015-2017, 湖南物联聚创信息科技有限公司 描 述: UserRoleDataFallback.java 版 本： 1.0 创
 * 建 人: lidesheng 创建时间: 2017年10月27日 下午2:15:59
 */
package com.zeus.userservice.commerce.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.zeus.common.core.exception.ServiceException;
import com.zeus.common.entity.person.AuthUserRoleDTO;

/**
 * @ClassName: UserRoleDataFallback
 * @Description: TODO(用户角色数据返回处理类)
 * @author lidesheng 2017年10月27日 下午2:15:59
 * 
 */
@Component
public class UserRoleDataFallback implements IUserRoleData{
    
    @Override
    public void insertByBatch(List<AuthUserRoleDTO> authUserRoleDTO){
        throw new ServiceException("2002013","bathInsert userRole info error");
    }
}
