/*
 * 文 件 名:  RoleController.java
 * 版    权:  Copyright © 2015-2017, 湖南物联聚创信息科技有限公司
 * 描    述:  RoleController.java 
 * 版    本：   1.0 
 * 创 建 人:  lifeng
 * 创建时间: 2017年11月3日 下午1:49:47
*/
package com.zeus.userservice.commerce.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zeus.common.core.info.PageDataInfo;
import com.zeus.common.entity.PageQueryInfo;
import com.zeus.common.entity.Result;
import com.zeus.common.entity.ResultBean;
import com.zeus.common.entity.user.AuthRoleDTO;
import com.zeus.common.entity.user.AuthRoleInfoDTO;
import com.zeus.common.entity.user.AuthRoleSearchDTO;
import com.zeus.common.mybatis.model.user.HsAuthRole;
import com.zeus.userservice.commerce.service.RoleService;

/**
 * 
 * @ClassName: RoleController
 * @Description: 角色服务接口控制器
 * @author lifeng 2017年10月30日 上午9:42:48
 *
 */

@RestController
@RequestMapping("/role/")
public class RoleController{
    
    @Resource
    private RoleService roleService;
    
    /**
     * 
     * @Title: pageQuery
     * @Description: 分页条件查询
     * @param @param queryParam
     * @param @return
     * @return PageDataInfo<HsAuthRole>
     * @throws @author lifeng 2017年11月2日 下午3:47:04
     */
    @GetMapping("/query")
    @ResponseBody
    public ResultBean<PageDataInfo<AuthRoleInfoDTO>> pageQuery(
        @RequestParam(value= "pageNum",defaultValue= "0",required= false) int pageNum,
        @RequestParam("pageSize") int pageSize,AuthRoleSearchDTO queryInfo){
        PageQueryInfo<AuthRoleSearchDTO> queryParam= new PageQueryInfo<>(pageNum,pageSize);
        queryParam.setParam(queryInfo);
        PageDataInfo<AuthRoleInfoDTO> dataInfo= roleService.pageQuery(queryParam);
        return new ResultBean<PageDataInfo<AuthRoleInfoDTO>>(dataInfo);
        
    }
    
    /**
     * 
     * @Title: queryAll
     * @Description: 查询所有
     * @param @return
     * @return ResultBean<List<HsAuthRole>>
     * @throws @author lifeng 2017年11月3日 下午3:05:16
     */
    @GetMapping("/queryAll")
    @ResponseBody
    public ResultBean<List<HsAuthRole>> queryAll(){
        List<HsAuthRole> dataInfo= roleService.queryAll();
        return new ResultBean<List<HsAuthRole>>(dataInfo);
        
    }
    
    /**
     * 
     * @Title: getRoleInfoById
     * @Description: 通过id获取角色关联的详细信息
     * @param @return
     * @return ResultBean<AuthRoleInfoDTO>
     * @throws @author lifeng 2017年11月7日 下午2:01:33
     */
    @GetMapping("/{id}/getRoleInfo")
    @ResponseBody
    public ResultBean<AuthRoleInfoDTO> getRoleInfoById(@PathVariable("id") Long roleId){
        AuthRoleInfoDTO roleInfo= roleService.getRoleInfoById(roleId);
        return new ResultBean<AuthRoleInfoDTO>(roleInfo);
        
    }
    
    /**
     * 
     * @Title: addRole
     * @Description: 角色添加
     * @param @param authRoleDTO
     * @param @return
     * @return Result
     * @throws @author lifeng 2017年10月30日 上午10:42:59
     */
    @PostMapping("/add")
    @ResponseBody
    public Result addRole(@RequestBody AuthRoleDTO authRoleDTO){
        return roleService.addRole(authRoleDTO);
        
    }
    
    /**
     * 
     * @Title: updateRole
     * @Description: 角色更新
     * @param @param authRoleDTO
     * @param @return
     * @return Result
     * @throws @author Administrator 2017年10月30日 下午1:10:18
     */
    @PostMapping("/edit")
    @ResponseBody
    public Result updateRole(@RequestBody AuthRoleDTO authRoleDTO){
        return roleService.updateRole(authRoleDTO);
        
    }
    
    /**
     * 
     * @Title: deleteRole
     * @Description: 角色逻辑删除
     * @param @param roleId
     * @param @return
     * @return Result
     * @throws @author Administrator 2017年10月30日 下午1:10:37
     */
    @DeleteMapping("/{id}/delete")
    @ResponseBody
    public Result deleteRole(@PathVariable("id") Long roleId){
        return roleService.deleteRole(roleId);
        
    }
    
    /**
     * 
     * @Title: getRoleBySiteType
     * @Description: 通过网点类型获取角色
     * @param @param siteType
     * @param @return
     * @return ResultBean<List<HsAuthRole>>
     * @throws @author lifeng 2017年11月6日 下午1:49:31
     */
    @GetMapping("/{typeId}/getRoleBySiteType")
    @ResponseBody
    public ResultBean<List<HsAuthRole>> getRoleBySiteType(@PathVariable("typeId") Long siteType){
        List<HsAuthRole> roles= roleService.getRoleBySiteType(siteType);
        return new ResultBean<List<HsAuthRole>>(roles);
        
    }
    
}
