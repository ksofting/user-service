/*
 * 文 件 名:  MenuService.java
 * 版    权:  Copyright © 2015-2017, 湖南物联聚创信息科技有限公司
 * 描    述:  MenuService.java 
 * 版    本：   1.0 
 * 创 建 人:  HuangChao
 * 创建时间: 2017年11月2日 下午4:05:05
*/
package com.zeus.userservice.commerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.zeus.common.core.id.IdUtil;
import com.zeus.common.core.info.PageDataInfo;
import com.zeus.common.entity.PageQueryInfo;
import com.zeus.common.entity.user.HsAuthMenusDTO;
import com.zeus.common.mybatis.model.user.HsAuthMenus;
import com.zeus.common.service.service.BaseService;
import com.zeus.userservice.common.constants.MessageConstants;

/** 
* @ClassName: MenuService
* @Description: 菜单业务处理类
* @author HuangChao 2017年11月2日 下午4:05:05
* 
*/
@Service
public class MenuService extends BaseService{
    
    
    @Autowired
    private IMenuData iMenuData;
    
    /**
     * 
    * @Title: pageQuery
    * @Description:分页条件查询
    * @param @param queryParam
    * @param @return
    * @return PageDataInfo<HsAuthMenus>
    * @throws 
    * @author HuangChao 2017年11月1日 上午10:34:47
     */
    public PageDataInfo<HsAuthMenus> pageQuery(PageQueryInfo<HsAuthMenus> queryParam){
        return iMenuData.pageQuery(queryParam);
    }
    
    /**
     * 
    * @Title: addHsAuthMenus
    * @Description: 添加菜单信息
    * @param @param hsAuthMenus
    * @param @return
    * @return int
    * @throws 
    * @author HuangChao 2017年11月1日 上午10:42:34
     */
    public int addHsAuthMenus(HsAuthMenus hsAuthMenus){
        
        // 1、参数有效性校验
        Assert.notNull(hsAuthMenus,MessageConstants.MENU_PARAMS_EMPTY);
        Assert.notNull(hsAuthMenus.getMenuCode(),MessageConstants.MENU_CODE_EMPTY);
        Assert.notNull(hsAuthMenus.getMenuType(),MessageConstants.MENU_TYPE_EMPTY);
        Assert.notNull(hsAuthMenus.getMenuName(),MessageConstants.MENU_NAME_EMPTY);
        Assert.notNull(hsAuthMenus.getMenuOrder(),MessageConstants.MENU_ORDER_EMPTY);
        
        //2、验证上级菜单是否选择
        if(null== hsAuthMenus.getParentMenuId()|| hsAuthMenus.getParentMenuId()< 0){
            hsAuthMenus.setParentMenuId(0l);//属于一级菜单
        }
        
        hsAuthMenus.setMenuId(IdUtil.getId());
        hsAuthMenus.setCreatedBy(currentUserId());
        return iMenuData.addHsAuthMenus(hsAuthMenus);
    }
    
    /**
     * 
    * @Title: editHsAuthMenus
    * @Description: 编辑菜单信息
    * @param @param hsAuthMenus
    * @param @return
    * @return int
    * @throws 
    * @author HuangChao 2017年11月1日 上午10:42:49
     */
    public int editHsAuthMenus(@RequestBody HsAuthMenus hsAuthMenus){
        
        // 1、参数有效性校验
        Assert.notNull(hsAuthMenus,MessageConstants.MENU_PARAMS_EMPTY);
        Assert.notNull(hsAuthMenus.getMenuCode(),MessageConstants.MENU_CODE_EMPTY);
        Assert.notNull(hsAuthMenus.getMenuType(),MessageConstants.MENU_TYPE_EMPTY);
        Assert.notNull(hsAuthMenus.getMenuName(),MessageConstants.MENU_NAME_EMPTY);
        Assert.notNull(hsAuthMenus.getMenuOrder(),MessageConstants.MENU_ORDER_EMPTY);
        
        //2、验证上级菜单是否选择
        if(null== hsAuthMenus.getParentMenuId()|| hsAuthMenus.getParentMenuId()< 0){
            hsAuthMenus.setParentMenuId(0l);//属于一级菜单
        }
        
        hsAuthMenus.setModifiedBy(currentUserId());
        return iMenuData.editHsAuthMenus(hsAuthMenus);
    }
    
    /**
     * 
    * @Title: delHsAuthMenus
    * @Description: 删除菜单信息
    * @param @param menuId 菜单ID
    * @param @return
    * @return int
    * @throws 
    * @author HuangChao 2017年11月1日 上午10:43:38
     */
    public int delHsAuthMenus(@PathVariable("menuId") Long menuId){
        return iMenuData.delHsAuthMenus(menuId);
    }
    
    /**
     * 
    * @Title: queryAll
    * @Description: 查询菜单列表信息
    * @param @param menuType 菜单类型，BS、CS、APP
    * @param @return
    * @return List<HsAuthMenus>
    * @throws 
    * @author HuangChao 2017年11月1日 上午10:43:55
     */
    public List<HsAuthMenus> queryAll(String menuType){
        return iMenuData.queryAll(menuType);
    }
    
    /**
     * 
    * @Title: queryAll
    * @Description: 查询菜单列表信息
    * @param @return
    * @return List<HsAuthMenus>
    * @throws 
    * @author HuangChao 2017年11月1日 上午10:43:55
     */
    public List<HsAuthMenus> queryAll(){
        return iMenuData.queryAll();
    }
    
    /**
     * 
    * @Title: queryInfoById
    * @Description: 根据菜单ID查询菜单信息
    * @param @param menuId
    * @param @return
    * @return List<HsAuthMenus>
    * @throws 
    * @author HuangChao 2017年11月1日 上午10:45:47
     */
    public HsAuthMenusDTO queryInfoById(@PathVariable("menuId") Long menuId){
        return iMenuData.queryInfoById(menuId);
    }
    
}
