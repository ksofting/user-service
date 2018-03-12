/*
 * 文 件 名:  ResourceService.java
 * 版    权:  Copyright © 2015-2017, 湖南物联聚创信息科技有限公司
 * 描    述:  ResourceService.java
 * 版    本：   1.0 
 * 创 建 人:  lifeng
 * 创建时间: 2017年11月3日 下午1:49:47
*/
package com.zeus.userservice.commerce.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.common.utils.ObjectUtils;
import com.zeus.common.core.enums.impl.TrueFalseEnum;
import com.zeus.common.core.exception.ServiceException;
import com.zeus.common.core.id.IdUtil;
import com.zeus.common.core.info.PageDataInfo;
import com.zeus.common.core.utils.AssertUtils;
import com.zeus.common.entity.PageQueryInfo;
import com.zeus.common.entity.Result;
import com.zeus.common.entity.user.AuthResourceDTO;
import com.zeus.common.intf.enums.ResourceType;
import com.zeus.common.mybatis.model.user.HsAuthMenus;
import com.zeus.common.mybatis.model.user.HsAuthResource;
import com.zeus.common.service.service.BaseService;
import com.zeus.userservice.common.constants.MessageConstants;

/**
 * 
 * @ClassName: ResourceService
 * @Description: 资源信息服务服务层
 * @author lifeng 2017年10月27日 下午1:20:40
 *
 */
@Service
public class ResourceService extends BaseService{
    
    @Autowired
    private IResourceData resourceServiceImpl;
    
    @Autowired
    private IMenuData menuServiceImpl;
    
    /**
     * 
     * @Title: pageQuery
     * @Description: 分页条件查询
     * @param @param queryInfo
     * @param @return
     * @return PageDataInfo<AuthResourceDTO>
     * @throws @author Administrator 2017年10月27日 下午3:48:49
     */
    public PageDataInfo<AuthResourceDTO> pageQuery(PageQueryInfo<AuthResourceDTO> queryInfo){
        AssertUtils.notNull(queryInfo,MessageConstants.RESOURCE_PARAM_EMPTY);
        return resourceServiceImpl.pageQuery(queryInfo);
    }
    
    /**
     * @Title: queryAll
     * @Description: 查询所有
     * @param @return
     * @return List<HsAuthResource>
     * @throws @author lifeng 2017年11月3日 下午2:44:39
     */
    public List<HsAuthResource> queryAll(){
        return resourceServiceImpl.queryAll();
        
    }
    
    /**
     * 
     * @Title: addHsAuthResource
     * @Description: 资源信息添加
     * @param @param hsAuthResource
     * @param @return
     * @return Result
     * @throws @author Administrator 2017年10月30日 下午5:31:48
     */
    public Result addHsAuthResource(HsAuthResource hsAuthResource){
        // 1.参数有效性校验
        AssertUtils.notNull(hsAuthResource,MessageConstants.RESOURCE_PARAM_EMPTY);// 参数不能为空
        AssertUtils.notNull(hsAuthResource.getResType(),MessageConstants.RESOURCE_TYPE_EMPTY);// 资源类型不能为空
        AssertUtils.notNull(hsAuthResource.getResName(),MessageConstants.RESOURCE_NAME_EMPTY);// 资源名称不能为空
        AssertUtils.notNull(hsAuthResource.getResCode(),MessageConstants.RESOURCE_CODE_EMPTY);// 资源代码不能为空
        AssertUtils.notNull(hsAuthResource.getReqMethod(),MessageConstants.RESOURCE_METHOD_EMPTY);// 访问方式不能为空
        
        // 2.校验资源名称唯一性
        List<HsAuthResource> resources= resourceServiceImpl.findByName(hsAuthResource.getResName());
        if(CollectionUtils.isNotEmpty(resources)){
            throw new ServiceException(MessageConstants.RESOURCE_NAME_EXSIT);
        }
        
        // 3.查询所属菜单是否存在
        if(ObjectUtils.isNotNull(hsAuthResource.getParentResId())){
            HsAuthMenus menus= menuServiceImpl.queryInfoById(hsAuthResource.getParentResId());
            if(ObjectUtils.isNull(menus)){
                throw new ServiceException(MessageConstants.RESOURCE_MENU_ERROR);
            }
            // 4.查询父级层级,设置子级层级
            HsAuthResource parentResource= resourceServiceImpl.findById(hsAuthResource
                .getParentResId());
            if(ObjectUtils.isNotNull(parentResource)){
                if(ObjectUtils.isNotNull(parentResource.getResLevel())){
                    hsAuthResource.setResLevel(1);
                }
                else{
                    hsAuthResource.setResLevel(parentResource.getResLevel()+ 1);
                }
            }
        }
        
        // 5.设置补充参数
        hsAuthResource.setResId(IdUtil.getId());// 生成id
        hsAuthResource.setRdStatus(TrueFalseEnum.TRUE.getValue());// 设置数据状态 1：正常，0：删除
        hsAuthResource.setCreatedBy(currentUserId());// 设置创建人
        
        // 6.调用服务添加资源数据
        int result= resourceServiceImpl.addHsAuthResource(hsAuthResource);
        // 添加成功则返回result，否则抛出异常
        if(result> 0){
            return new Result();
        }
        throw new ServiceException(MessageConstants.RESOURCE_ADD_ERROR);
        
    }
    
    /**
     * 
     * @Title: updateHsAuthResource
     * @Description: 资源信息更新服务
     * @param @param hsAuthResource
     * @param @return
     * @return Result 表示成功，失败抛出异常
     * @throws @author lifeng 2017年10月26日 下午1:48:26
     */
    public Result updateHsAuthResource(HsAuthResource hsAuthResource){
        // 1.校验参数有效性
        AssertUtils.notNull(hsAuthResource,MessageConstants.RESOURCE_PARAM_EMPTY);
        AssertUtils.notNull(hsAuthResource.getResId(),MessageConstants.RESOURCE_RESID_EMPTY);
        
        // 2.校验用户名唯一性
        if(StringUtils.isNotBlank(hsAuthResource.getResName())){
            List<HsAuthResource> resources= resourceServiceImpl.findByName(hsAuthResource
                .getResName());
            if(CollectionUtils.isNotEmpty(resources)){
                if(resources.size()== 1){
                    // 如果查询的的对象ID跟传入的Id不一样，说明是2条不同数据
                    if(! hsAuthResource.getResId().equals(resources.get(0).getResId())){
                        throw new ServiceException(MessageConstants.RESOURCE_NAME_EXSIT);
                    }
                }
                else if(resources.size()> 1){
                    throw new ServiceException(MessageConstants.RESOURCE_NAME_EXSIT);
                }
            }
        }
        
        // 3.调用菜单管理服务判断所属菜单是否存在
        if(ObjectUtils.isNotNull(hsAuthResource.getParentResId())){
            HsAuthMenus menus= menuServiceImpl.queryInfoById(hsAuthResource.getParentResId());
            if(ObjectUtils.isNull(menus)){
                throw new ServiceException(MessageConstants.RESOURCE_MENU_ERROR);
            }
        }
        // 4.设置补充参数
        hsAuthResource.setModifiedBy(currentUserId());// 设置修改人
        
        // 5.调用服务更新资源信息数据
        int result= resourceServiceImpl.updateAuthResource(hsAuthResource);
        if(result> 0){
            return new Result();
        }
        throw new ServiceException(MessageConstants.RESOURCE_UPDATE_ERROR);
        
    }
    
    /**
     * 
     * @Title: deleteHsAuthResource
     * @Description: 逻辑删除资源信息, 删除数据需判断是否有关联子级，有则不能删除
     * @param @param resId
     * @param @return
     * @return Result
     * @throws @author Administrator 2017年10月26日 下午5:24:51
     */
    public Result deleteHsAuthResource(Long resId){
        // 1.校验参数
        AssertUtils.notNull(resId,MessageConstants.RESOURCE_PARAM_EMPTY);
        // 2.查询该资源是否有下层子关联，
        List<HsAuthResource> resources= resourceServiceImpl.findByParentResId(resId);
        if(CollectionUtils.isNotEmpty(resources)){
            throw new ServiceException(MessageConstants.RESOURCE_CHILD_EXSIT);
        }
        // 3.调用服务删除资源数据，改变数据状态,清除关联表记录
        int result= resourceServiceImpl.deleteHsAuthResource(resId);
        if(result> 0){
            return new Result();
        }
        throw new ServiceException(MessageConstants.RESOURCE_DELETE_ERROR);
    }
    
    /**
     * @Title: getReqMethod
     * @Description: 获取资源类型
     * @param @return
     * @return List<String>
     * @throws @author lifeng 2017年11月7日 下午1:49:52
     */
    public List<String> getResType(){
        List<String> types= new ArrayList<String>();
        for(ResourceType type : ResourceType.values()){
            types.add(type.getCode());
        }
        return types;
    }
    
}
