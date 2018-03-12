/*
 * 文 件 名:  RoleService.java
 * 版    权:  Copyright © 2015-2017, 湖南物联聚创信息科技有限公司
 * 描    述:  RoleService.java
 * 版    本：   1.0 
 * 创 建 人:  lifeng
 * 创建时间: 2017年11月3日 下午1:49:47
*/
package com.zeus.userservice.commerce.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeus.common.core.enums.impl.TrueFalseEnum;
import com.zeus.common.core.exception.ServiceException;
import com.zeus.common.core.id.IdUtil;
import com.zeus.common.core.info.PageDataInfo;
import com.zeus.common.core.utils.AssertUtils;
import com.zeus.common.entity.PageQueryInfo;
import com.zeus.common.entity.Result;
import com.zeus.common.entity.user.AuthRoleDTO;
import com.zeus.common.entity.user.AuthRoleInfoDTO;
import com.zeus.common.entity.user.AuthRoleSearchDTO;
import com.zeus.common.mybatis.model.user.HsAuthRole;
import com.zeus.common.mybatis.model.user.HsAuthRoleResource;
import com.zeus.common.service.service.BaseService;
import com.zeus.userservice.common.constants.MessageConstants;

@Service
public class RoleService extends BaseService{
    
    @Autowired
    private IRoleData roleServiceImpl;
    
    /**
     * 
     * @Title: addRole
     * @Description: 角色添加
     * @param @param authRoleDTO
     * @param @return
     * @return Result
     * @throws @author lifeng 2017年11月2日 下午4:52:31
     */
    public Result addRole(AuthRoleDTO authRoleDTO){
        // 1.参数有效性验证
        AssertUtils.notNull(authRoleDTO,MessageConstants.ROLE_PARAM_EMPTY);
        AssertUtils.notNull(authRoleDTO.getRoleName(),MessageConstants.ROLE_NAME_EMPTY);
        AssertUtils.notNull(authRoleDTO.getRoleCode(),MessageConstants.ROLE_CODE_EMPTY);
        // 2.校验名称和代码是否唯一
        List<HsAuthRole> roles= roleServiceImpl.findByName(authRoleDTO.getRoleName());
        if(CollectionUtils.isNotEmpty(roles)){
            throw new ServiceException(MessageConstants.ROLE_NAME_EXSIT);
        }
        roles= roleServiceImpl.findByCode(authRoleDTO.getRoleCode());
        if(CollectionUtils.isNotEmpty(roles)){
            throw new ServiceException(MessageConstants.ROLE_CODE_EXSIT);
        }
        authRoleDTO.setRoleId(IdUtil.getId());// 生成id
        authRoleDTO.setRdStatus(TrueFalseEnum.TRUE.getValue());// 设置数据状态
        authRoleDTO.setCreatedBy(currentUserId());// 设置创建人
        // 设置关联对象id
        updateRelation(authRoleDTO);
        // 4.调用服务添加角色信息及关联信息
        int result= roleServiceImpl.addHsAuthRole(authRoleDTO);
        if(result> 0){
            return new Result();
        }
        throw new ServiceException(MessageConstants.ROLE_ADD_ERROR);
        
    }
    
    /**
     * 
     * @Title: updateRole
     * @Description: 角色更新
     * @param @param authRoleDTO
     * @param @return
     * @return Result
     * @throws @author lifeng 2017年11月2日 下午4:52:53
     */
    public Result updateRole(AuthRoleDTO authRoleDTO){
        // 1.校验参数
        AssertUtils.notNull(authRoleDTO,MessageConstants.ROLE_PARAM_EMPTY);
        AssertUtils.notNull(authRoleDTO.getRoleId(),MessageConstants.ROLE_ID_EMPTY);
        // 名称 代码 是否存在
        if(StringUtils.isNotEmpty(authRoleDTO.getRoleName())){
            List<HsAuthRole> roles= roleServiceImpl.findByName(authRoleDTO.getRoleName());
            if(CollectionUtils.isNotEmpty(roles)){
                if(roles.size()== 1){
                    if(! roles.get(0).getRoleId().equals(authRoleDTO.getRoleId())){
                        throw new ServiceException(MessageConstants.ROLE_NAME_EXSIT);
                    }
                }
                else{
                    throw new ServiceException(MessageConstants.ROLE_NAME_EXSIT);
                }
            }
        }
        if(StringUtils.isNotEmpty(authRoleDTO.getRoleCode())){
            List<HsAuthRole> roles= roleServiceImpl.findByCode(authRoleDTO.getRoleCode());
            if(CollectionUtils.isNotEmpty(roles)){
                if(roles.size()== 1){
                    if(! roles.get(0).getRoleId().equals(authRoleDTO.getRoleId())){
                        throw new ServiceException(MessageConstants.ROLE_CODE_EXSIT);
                    }
                }
                else{
                    throw new ServiceException(MessageConstants.ROLE_CODE_EXSIT);
                }
            }
        }
        // 2.补充参数
        authRoleDTO.setModifiedBy(currentUserId());
        authRoleDTO.setModifiedTime(new Date());
        // 设置关联对象id
        updateRelation(authRoleDTO);
        // 3.调用服务更新数据
        int result= roleServiceImpl.updateHsAuthRole(authRoleDTO);
        if(result> 0)
            return new Result();
        return null;
    }
    
    /**
     * 
     * @Title: deleteRole
     * @Description: 角色删除
     * @param @param roleId
     * @param @return
     * @return Result
     * @throws @author lifeng 2017年11月2日 下午4:52:56
     */
    public Result deleteRole(Long roleId){
        AssertUtils.notNull(roleId,MessageConstants.MENU_ID_EMPTY);
        int result= roleServiceImpl.deleteHsAuthRole(roleId);
        if(result> 0)
            return new Result();
        return null;
    }
    
    /**
     * 
     * @Title: pageQuery
     * @Description: 分页条件查询
     * @param @param queryParam
     * @param @return
     * @return PageDataInfo<HsAuthRole>
     * @throws @author lifeng 2017年11月2日 下午4:52:59
     */
    public PageDataInfo<AuthRoleInfoDTO> pageQuery(PageQueryInfo<AuthRoleSearchDTO> queryParam){
        AssertUtils.notNull(queryParam,MessageConstants.ROLE_PARAM_EMPTY);
        return roleServiceImpl.pageQuery(queryParam);
        
    }
    
    /**
     * @Title: updateRelation
     * @Description: 生成关联对象集合
     * @param @param authRoleDTO
     * @return void
     * @throws @author lifeng 2017年11月3日 上午11:18:00
     */
    private void updateRelation(AuthRoleDTO authRoleDTO){
        //TODO网点类型关联信息
        /*
        List<HsAuthRoleSiteType> roleSiteTypes= authRoleDTO.getRoleSiteTypes();
        if(CollectionUtils.isNotEmpty(roleSiteTypes)){
            for(HsAuthRoleSiteType hsAuthRoleSiteType : roleSiteTypes){
                hsAuthRoleSiteType.setId(IdUtil.getId());
                hsAuthRoleSiteType.setRoleId(authRoleDTO.getRoleId());
            }
        }*/
        List<HsAuthRoleResource> roleResources= null;
        Map<Long,Integer> map= new LinkedHashMap<>();
        //访问权限标识为1，操作权限标识为2，访问和操作权限标识为3
        if(CollectionUtils.isNotEmpty(authRoleDTO.getView())){
            for(Long resId : authRoleDTO.getView()){
                map.put(resId,1);
            }
        }
        if(CollectionUtils.isNotEmpty(authRoleDTO.getManagement())){
            for(Long resId : authRoleDTO.getManagement()){
                map.put(resId,map.containsKey(resId) ? 3 : 2);
            }
        }
        if(MapUtils.isNotEmpty(map)){
            roleResources= new ArrayList<>();
            Set<Long> keySet= map.keySet();
            for(Long resId : keySet){
                HsAuthRoleResource roleResource= new HsAuthRoleResource(IdUtil.getId(),authRoleDTO
                    .getRoleId(),resId);
                if(map.get(resId)== 1){
                    roleResource.setAccessPower(TrueFalseEnum.TRUE.getValue());
                    roleResource.setGrantPower(TrueFalseEnum.FALSE.getValue());
                }
                else if(map.get(resId)== 2){
                    roleResource.setAccessPower(TrueFalseEnum.FALSE.getValue());
                    roleResource.setGrantPower(TrueFalseEnum.TRUE.getValue());
                }
                else if(map.get(resId)== 3){
                    roleResource.setAccessPower(TrueFalseEnum.TRUE.getValue());
                    roleResource.setGrantPower(TrueFalseEnum.TRUE.getValue());
                }
                roleResources.add(roleResource);
            }
        }
        authRoleDTO.setRoleResources(roleResources);
    }
    
    /**
     * @Title: queryAll
     * @Description: 查询所有角色
     * @param @return
     * @return List<HsAuthRole>
     * @throws @author lifeng 2017年11月3日 下午3:07:40
     */
    public List<HsAuthRole> queryAll(){
        return roleServiceImpl.queryAll();
    }
    
    /**
     * @Title: getRoleBySiteType
     * @Description: 通过网点类型获取角色
     * @param @param siteType
     * @param @return
     * @return ResultBean<List<HsAuthRole>>
     * @throws @author lifeng 2017年11月6日 下午1:50:27
     */
    public List<HsAuthRole> getRoleBySiteType(Long siteType){
        AssertUtils.notNull(siteType,MessageConstants.ROLE_PARAM_EMPTY);
        return roleServiceImpl.getRoleBySiteType(siteType);
    }
    
    /**
     * @Title: getRoleInfoById
     * @Description: 通过id获取角色关联的详细信息
     * @param @param roleId
     * @param @return
     * @return AuthRoleInfoDTO
     * @throws @author lifeng 2017年11月7日 下午2:07:36
     */
    public AuthRoleInfoDTO getRoleInfoById(Long roleId){
        AssertUtils.notNull(roleId,MessageConstants.ROLE_PARAM_EMPTY);
        return roleServiceImpl.getRoleInfoById(roleId);
    }
    
}
