/*
 * 文 件 名:  RoleDataFallback.java
 * 版    权:  Copyright © 2015-2017, 湖南物联聚创信息科技有限公司
 * 描    述:  RoleDataFallback.java
 * 版    本：   1.0 
 * 创 建 人:  lifeng
 * 创建时间: 2017年11月3日 下午1:49:47
*/
package com.zeus.userservice.commerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zeus.common.core.exception.ServiceException;
import com.zeus.common.core.info.PageDataInfo;
import com.zeus.common.entity.PageQueryInfo;
import com.zeus.common.entity.user.AuthRoleDTO;
import com.zeus.common.entity.user.AuthRoleInfoDTO;
import com.zeus.common.entity.user.AuthRoleSearchDTO;
import com.zeus.common.mybatis.model.user.HsAuthRole;
import com.zeus.userservice.common.constants.MessageConstants;

/**
 * 
 * @ClassName: RoleDataFallback
 * @Description: 角色服务降级服务
 * @author lifeng 2017年11月2日 下午3:04:33
 *
 */
@Service
public class RoleDataFallback implements IRoleData{
    
    /**
     * (非 Javadoc)
     * 
     * @Title: pageQuery
     * @Description: 分页条件查询降级服务
     * @throws @author lifeng 2017年11月2日 下午2:11:40
     */
    @Override
    public PageDataInfo<AuthRoleInfoDTO> pageQuery(PageQueryInfo<AuthRoleSearchDTO> queryParam){
        throw new ServiceException(MessageConstants.ROLE_FOUND_ERROR);
    }
    
    /**
     * (非 Javadoc)
     * 
     * @Title: addHsAuthRole
     * @Description: 角色添加降级服务
     * @throws @author lifeng 2017年11月2日 下午2:11:40
     */
    @Override
    public int addHsAuthRole(AuthRoleDTO authRoleDTO){
        throw new ServiceException(MessageConstants.ROLE_ADD_ERROR);
    }
    
    /**
     * (非 Javadoc)
     * 
     * @Title: updateHsAuthRole
     * @Description: 角色更新降级服务
     * @throws @author lifeng 2017年11月2日 下午2:11:40
     */
    @Override
    public int updateHsAuthRole(AuthRoleDTO authRoleDTO){
        throw new ServiceException(MessageConstants.ROLE_UPTATE_ERROR);
    }
    
    /**
     * (非 Javadoc)
     * 
     * @Title: deleteHsAuthRole
     * @Description: 角色删除降级服务
     * @throws @author lifeng 2017年11月2日 下午2:11:40
     */
    @Override
    public int deleteHsAuthRole(Long roleId){
        throw new ServiceException(MessageConstants.ROLE_DLETE_ERROR);
    }
    
    /**
     * (非 Javadoc)
     * 
     * @Title: queryAll
     * @Description: 查询所有降级服务
     * @throws @author lifeng 2017年11月2日 下午2:11:40
     */
    @Override
    public List<HsAuthRole> queryAll(){
        throw new ServiceException(MessageConstants.ROLE_FOUND_ERROR);
    }
    
    /**
     * (非 Javadoc)
     * 
     * @Title: findByName
     * @Description: 通过名称查询角色降级服务
     * @throws @author lifeng 2017年11月2日 下午2:11:40
     */
    @Override
    public List<HsAuthRole> findByName(String roleName){
        throw new ServiceException(MessageConstants.ROLE_FOUND_ERROR);
        
    }
    
    /**
     * (非 Javadoc)
     * 
     * @Title: findByCode
     * @Description: 通过代码查询角色降级服务
     * @throws @author lifeng 2017年11月2日 下午2:11:40
     */
    @Override
    public List<HsAuthRole> findByCode(String roleCode){
        throw new ServiceException(MessageConstants.ROLE_FOUND_ERROR);
        
    }
    
    /**
     * (非 Javadoc)
     * 
     * @Title: getRoleBySiteType
     * @Description: 通过网点类型查询角色
     * @throws @author lifeng 2017年11月6日 下午1:53:53
     */
    @Override
    public List<HsAuthRole> getRoleBySiteType(Long siteType){
        throw new ServiceException(MessageConstants.ROLE_FOUND_ERROR);
    }
    
    /**
     * (非 Javadoc)
     * 
     * @Title: getRoleInfoById
     * @Description: 通过id获取角色关联的详细信息
     * @throws @author lifeng 2017年11月7日 下午2:11:53
     */
    @Override
    public AuthRoleInfoDTO getRoleInfoById(Long roleId){
        throw new ServiceException(MessageConstants.ROLE_FOUND_ERROR);
    }
    
}
