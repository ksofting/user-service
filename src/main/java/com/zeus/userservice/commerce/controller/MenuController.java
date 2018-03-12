/*
 * 文 件 名:  MenuController.java
 * 版    权:  Copyright © 2015-2017, 湖南物联聚创信息科技有限公司
 * 描    述:  MenuController.java 
 * 版    本：   1.0 
 * 创 建 人:  HuangChao
 * 创建时间: 2017年10月31日 下午5:49:08
*/
package com.zeus.userservice.commerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zeus.common.core.info.PageDataInfo;
import com.zeus.common.entity.PageQueryInfo;
import com.zeus.common.entity.Result;
import com.zeus.common.entity.ResultBean;
import com.zeus.common.entity.user.HsAuthMenusDTO;
import com.zeus.common.mybatis.model.user.HsAuthMenus;
import com.zeus.userservice.commerce.service.MenuService;

/**
 * @ClassName: MenuController
 * @Description: 菜单管理处理控制器
 * @author HuangChao 2017年10月31日 下午5:49:08
 * 
 */
@RestController
@RequestMapping("/menu/")
@ResponseBody
public class MenuController{
    
    
    @Autowired
    private MenuService menuService;
    
    /**
     * 
     * @Title: pageQuery
     * @Description:分页条件查询
     * @param @param queryParam
     * @param @return
     * @return ResultBean<PageDataInfo<HsAuthMenus>>
     * @throws @author HuangChao 2017年11月1日 上午10:34:47
     */
    @RequestMapping(path= "/pageQuery",method= RequestMethod.GET)
    public ResultBean<PageDataInfo<HsAuthMenus>> pageQuery(
        @RequestParam(value= "pageNum",defaultValue= "0",required= false) int pageNum,
        @RequestParam("pageSize") int pageSize,HsAuthMenus hsAuthMenus){
        PageQueryInfo<HsAuthMenus> queryParam= new PageQueryInfo<>(pageNum,pageSize,hsAuthMenus);
        ResultBean<PageDataInfo<HsAuthMenus>> result= new ResultBean<>();
        result.setData(menuService.pageQuery(queryParam));
        return result;
    }
    
    /**
     * 
     * @Title: addHsAuthMenus
     * @Description: 添加菜单信息
     * @param @param hsAuthMenus
     * @param @return
     * @return int
     * @throws @author HuangChao 2017年11月1日 上午10:42:34
     */
    @RequestMapping(path= "/add",method= RequestMethod.POST)
    public Result addHsAuthMenus(@RequestBody HsAuthMenus hsAuthMenus){
        menuService.addHsAuthMenus(hsAuthMenus);
        return new Result();
    }
    
    /**
     * 
     * @Title: editHsAuthMenus
     * @Description: 编辑菜单信息
     * @param @param hsAuthMenus
     * @param @return
     * @return int
     * @throws @author HuangChao 2017年11月1日 上午10:42:49
     */
    @RequestMapping(path= "/edit",method= RequestMethod.POST)
    public Result editHsAuthMenus(@RequestBody HsAuthMenus hsAuthMenus){
        menuService.editHsAuthMenus(hsAuthMenus);
        return new Result();
    }
    
    /**
     * 
     * @Title: delHsAuthMenus
     * @Description: 删除菜单信息
     * @param @param menuId 菜单ID
     * @param @return
     * @return int
     * @throws @author HuangChao 2017年11月1日 上午10:43:38
     */
    @RequestMapping(path= "/{menuId}/del",method= RequestMethod.DELETE)
    public Result delHsAuthMenus(@PathVariable("menuId") Long menuId){
        menuService.delHsAuthMenus(menuId);
        return new Result();
    }
    
    /**
     * 
     * @Title: queryAll
     * @Description: 查询菜单列表信息
     * @param @param menuType 菜单类型，BS、CS、APP
     * @param @return
     * @return List<HsAuthMenus>
     * @throws @author HuangChao 2017年11月1日 上午10:43:55
     */
    @RequestMapping(path= "/{menuType}/queryAll",method= RequestMethod.GET)
    public ResultBean<List<HsAuthMenus>> queryAll(@PathVariable("menuType") String menuType){
        ResultBean<List<HsAuthMenus>> result= new ResultBean<>();
        result.setData(menuService.queryAll(menuType));
        return result;
    }
    
    /**
     * 
     * @Title: queryAll
     * @Description: 查询菜单列表信息
     * @param @param menuType 菜单类型，BS、CS、APP
     * @param @return
     * @return List<HsAuthMenus>
     * @throws @author HuangChao 2017年11月1日 上午10:43:55
     */
    @RequestMapping(path= "/queryAll",method= RequestMethod.GET)
    public ResultBean<List<HsAuthMenus>> queryAll(){
        ResultBean<List<HsAuthMenus>> result= new ResultBean<>();
        result.setData(menuService.queryAll());
        return result;
    }
    
    /**
     * 
     * @Title: queryInfoById
     * @Description: 根据菜单ID查询菜单信息
     * @param @param menuId
     * @param @return
     * @return List<HsAuthMenus>
     * @throws @author HuangChao 2017年11月1日 上午10:45:47
     */
    @RequestMapping(path= "/{menuId}/info",method= RequestMethod.GET)
    public ResultBean<HsAuthMenusDTO> queryInfoById(@PathVariable("menuId") Long menuId){
        ResultBean<HsAuthMenusDTO> result= new ResultBean<>();
        result.setData(menuService.queryInfoById(menuId));
        return result;
    }
    
}
