/*
 * 文 件 名:  MenuDataFallback.java
 * 版    权:  Copyright © 2015-2017, 湖南物联聚创信息科技有限公司
 * 描    述:  MenuDataFallback.java 
 * 版    本：   1.0 
 * 创 建 人:  HuangChao
 * 创建时间: 2017年11月2日 下午3:43:59
*/
package com.zeus.userservice.commerce.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.zeus.common.core.exception.ServiceException;
import com.zeus.common.core.info.PageDataInfo;
import com.zeus.common.entity.PageQueryInfo;
import com.zeus.common.entity.user.HsAuthMenusDTO;
import com.zeus.common.mybatis.model.user.HsAuthMenus;
import com.zeus.userservice.common.constants.MessageConstants;

/** 
* @ClassName: MenuDataFallback
* @Description: 菜单服务降级服务
* @author HuangChao 2017年11月2日 下午3:43:59
* 
*/
@Component
public class MenuDataFallback implements IMenuData{
    
    
    @Override
    public PageDataInfo<HsAuthMenus> pageQuery(PageQueryInfo<HsAuthMenus> queryParam){
        return null;
    }
    
    @Override
    public int addHsAuthMenus(HsAuthMenus hsAuthMenus){
        throw new ServiceException(MessageConstants.MENU_ADD_ERROR,"insert menu info error");
    }
    
    @Override
    public int editHsAuthMenus(HsAuthMenus hsAuthMenus){
        throw new ServiceException(MessageConstants.MENU_UPDATE_ERROR,"edit menu info error");
    }
    
    @Override
    public int delHsAuthMenus(Long menuId){
        throw new ServiceException(MessageConstants.MENU_DEL_ERROR,"del menu info error");
    }
    
    @Override
    public List<HsAuthMenus> queryAll(String menuType){
        return null;
    }
    
    @Override
    public HsAuthMenusDTO queryInfoById(Long menuId){
        return null;
    }
    
    @Override
    public List<HsAuthMenus> queryAll(){
        return null;
    }
    
}
