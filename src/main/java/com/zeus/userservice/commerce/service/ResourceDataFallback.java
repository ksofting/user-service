/*
 * 文 件 名:  ResourceDataFallback.java
 * 版    权:  Copyright © 2015-2017, 湖南物联聚创信息科技有限公司
 * 描    述:  ResourceDataFallback.java
 * 版    本：   1.0 
 * 创 建 人:  lifeng
 * 创建时间: 2017年11月3日 下午1:49:47
*/
package com.zeus.userservice.commerce.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.zeus.common.core.exception.ServiceException;
import com.zeus.common.core.info.PageDataInfo;
import com.zeus.common.entity.PageQueryInfo;
import com.zeus.common.entity.user.AuthResourceDTO;
import com.zeus.common.mybatis.model.user.HsAuthResource;
import com.zeus.userservice.common.constants.MessageConstants;

/**
 * 
 * @ClassName: ResourceDataFallback
 * @Description: 资源服务降级服务
 * @author lifeng 2017年10月27日 下午1:21:37
 *
 */
@Component
public class ResourceDataFallback implements IResourceData{
    
    /**
     * (非 Javadoc)
     * 
     * @Title: pageQuery
     * @Description: 资源查询失败降级服务
     * @throws @author lifeng 2017年11月2日 下午5:33:44
     */
    @Override
    public PageDataInfo<AuthResourceDTO> pageQuery(PageQueryInfo<AuthResourceDTO> queryParam){
        throw new ServiceException(MessageConstants.ROLE_FOUND_ERROR);
    }
    
    /**
     * (非 Javadoc)
     * 
     * @Title: addHsAuthResource
     * @Description: 资源添加失败降级服务
     * @throws @author lifeng 2017年11月2日 下午5:33:47
     */
    @Override
    public int addHsAuthResource(HsAuthResource hsAuthResource){
        throw new ServiceException(MessageConstants.ROLE_ADD_ERROR);
    }
    
    /**
     * (非 Javadoc)
     * 
     * @Title: updateAuthResource
     * @Description: 资源更新失败降级服务
     * @throws @author lifeng 2017年11月2日 下午5:33:50
     */
    @Override
    public int updateAuthResource(HsAuthResource hsAuthResource){
        throw new ServiceException(MessageConstants.ROLE_UPTATE_ERROR);
    }
    
    /**
     * (非 Javadoc)
     * 
     * @Title: queryAll
     * @Description: 查询所有错误降级服务
     * @throws @author lifeng 2017年11月2日 下午5:33:53
     */
    @Override
    public List<HsAuthResource> queryAll(){
        throw new ServiceException(MessageConstants.ROLE_FOUND_ERROR);
    }
    
    /**
     * (非 Javadoc)
     * 
     * @Title: findById
     * @Description: 通过id查询错误降级服务
     * @throws @author lifeng 2017年11月2日 下午5:33:56
     */
    @Override
    public HsAuthResource findById(Long parentResId){
        
        throw new ServiceException(MessageConstants.ROLE_FOUND_ERROR);
        
    }
    
    /**
     * (非 Javadoc)
     * 
     * @Title: findByName
     * @Description: 通过name查询错误降级服务
     * @throws @author lifeng 2017年11月2日 下午5:33:59
     */
    @Override
    public List<HsAuthResource> findByName(String resName){
        throw new ServiceException(MessageConstants.ROLE_FOUND_ERROR);
    }
    
    /**
     * (非 Javadoc)
     * 
     * @Title: findByParentResId
     * @Description: 通过parentId查询错误降级服务
     * @throws @author lifeng 2017年11月2日 下午5:34:03
     */
    @Override
    public List<HsAuthResource> findByParentResId(Long resId){
        throw new ServiceException(MessageConstants.ROLE_FOUND_ERROR);
    }
    
    /**
     * (非 Javadoc)
     * 
     * @Title: deleteHsAuthResource
     * @Description: 删除资源信息错误降级服务
     * @throws @author lifeng 2017年11月2日 下午5:34:07
     */
    @Override
    public int deleteHsAuthResource(Long resId){
        throw new ServiceException(MessageConstants.ROLE_DLETE_ERROR);
    }
    
}
