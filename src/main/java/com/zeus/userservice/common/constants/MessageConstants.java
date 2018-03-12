/*
 * 文 件 名: MessageConstants.java
 * 版 权: Copyright © 2015-2017, 湖南物联聚创信息科技有限公司
 * 描 述: MessageConstants.java
 * 版 本： 1.0
 * 创 建 人: lidesheng
 * 创建时间: 2017年10月31日 下午3:47:33
 */
package com.zeus.userservice.common.constants;

/**
 * @ClassName: MessageConstants
 * @Description: 用户服务消息常量
 * @author lidesheng 2017年10月31日 下午3:47:33
 * 
 */
public interface MessageConstants{
    
    /* =======================用户信息常量定义 开始============================== */
    /**
     * 修改用户异常
     */
    String USER_UPDATE_BACK= "2002001";
    
    /**
     * 新增用户失败
     */
    String USER_ADD_ERROR= "2002002";
    
    /**
     * 用户ID不为空
     */
    String USER_ID_MANAGE= "2002003";
    
    /**
     * 用户名称不为空
     */
    String USER_NAME_MANAGE= "2002004";
    
    /**
     * 用户密码不为空
     */
    String USER_PWD_MANAGE= "2002005";
    
    /**
     * 用户状态不为空
     */
    String USER_STATE_MANAGE= "2002006";
    
    /*
     * 用户状态必须为(N正常 L锁定 D删除-禁用)
     */
    String USER_STATE_ERROR= "2002007";
    
    /**
     * 修改用户名已存在
     */
    String USER_ACCOUNT_EXIST= "2002008";
    
    /**
     * 修改用户失败
     */
    String USER_UPDATE_ERROR= "2002009";
    
    /**
     * 删除用户异常
     */
    String USER_DELETE_ERROR= "2002010";
    
    /**
     * 用户名已存在
     */
    String USER_NAME_EXIST= "2002011";
    
    /**
     * 员工ID不为空
     */
    String USER_STAFF_MANAGE= "2002012";
    
    /**
     * 批量新增用户角色失败
     */
    String USER_ADD_ALL_MANAGE= "2002013";
    
    /**
     * 新增用户失败事物处理
     */
    String USER_ADD_TRANSACT_MANAGE= "2002014";
    
    /**
     * 新增用户异常事物处理
     */
    String USER_ADD_TRANSACT_ERROR= "2002015";
    
    /**
     * 修改用户异常事物处理
     */
    String USER_UPDATE_TRANSACT_ERROR= "2002016";
    
    /**
     * 用户状态非法
     */
    String USER_STATE_ILLEGAL= "2002017";
    
    /**
     * 查询用户列表失败
     */
    String USER_SEACH_LIST_ERROR= "2002018";
    
    /**
     * 用户密码不为空
     */
    String USER_PWD_EMPTY= "2002019";
    
    /**
     * 修改用户密码失败
     */
    String USER_UPDATE_PWD= "2002020";
    
    /**
     * 查询用户明细失败
     */
    String USER_SEACH_ERROR= "2002021";
    
    /**
     * 解锁用户信息失败
     */
    String USER_UNLOCK_ERROR= "2002022";
    
    /**
     * 员工已存在
     */
    String USER_STAFFID_EXIST= "2002023";
    
    /**
     * 查询用户分页下拉框失败
     */
    String USER_COMBOX_LIST= "2002024";
    /* =======================用户信息常量定义 结束============================== */
    
    /* =======================资源信息常量定义 开始============================== */
    /**
     * 资源添加失败
     */
    String RESOURCE_ADD_ERROR= "20002201";
    
    /**
     * 参数为空
     */
    String RESOURCE_PARAM_EMPTY= "2002202";
    
    /**
     * 资源类型为空
     */
    String RESOURCE_TYPE_EMPTY= "2002203";
    
    /**
     * 资源名称为空
     */
    String RESOURCE_NAME_EMPTY= "2002204";
    
    /**
     * 资源编码为空
     */
    String RESOURCE_CODE_EMPTY= "2002205";
    
    /**
     * 访问方式为空
     */
    String RESOURCE_METHOD_EMPTY= "2002206";
    
    /**
     * 资源更新失败
     */
    String RESOURCE_UPDATE_ERROR= "2002207";
    
    /**
     * 资源id为空
     */
    String RESOURCE_RESID_EMPTY= "2002208";
    
    /**
     * 资源名称已存在
     */
    String RESOURCE_NAME_EXSIT= "2002209";
    
    /**
     * 资源存在关联子集
     */
    String RESOURCE_CHILD_EXSIT= "2002210";
    
    /**
     * 资源删除失败
     */
    String RESOURCE_DELETE_ERROR= "2002211";
    
    /**
     * 所属菜单不存在
     */
    String RESOURCE_MENU_ERROR= "2002212";
    
    /* =======================资源信息常量定义 结束============================== */
    
    /* =======================角色信息常量定义 开始============================== */
    /**
     * 角色新增失败
     */
    String ROLE_ADD_ERROR= "2002301";
    
    /**
     * 参数为空
     */
    String ROLE_PARAM_EMPTY= "2002302";
    
    /**
     * 角色名称为空
     */
    String ROLE_NAME_EMPTY= "2002303";
    
    /**
     * 角色编码为空
     */
    String ROLE_CODE_EMPTY= "2002304";
    
    /**
     * 角色更新失败
     */
    String ROLE_UPTATE_ERROR= "2002305";
    
    /**
     * 角色id为空
     */
    String ROLE_ROLEID_EMPTY= "2002306";
    
    /**
     * 角色 名称已存在
     */
    String ROLE_NAME_EXSIT= "2002307";
    
    /**
     * 角色代码已存在
     */
    String ROLE_CODE_EXSIT= "2002308";
    
    /**
     * 角色查询失败
     */
    String ROLE_FOUND_ERROR= "2002309";
    
    /**
     * 角色删除失败
     */
    String ROLE_DLETE_ERROR= "2002310";
    
    /**
     * 角色id为空
     */
    String ROLE_ID_EMPTY= "2002311";
    
    /* =======================角色信息常量定义 结束============================== */
    
    /* =======================菜单信息常量定义 开始============================== */
    
    /***
     * 菜单新增失败
     */
    String MENU_ADD_ERROR= "2002401";// 菜单新增失败
    
    /**
     * 菜单更新失败
     */
    String MENU_UPDATE_ERROR= "2002402";// 菜单更新失败
    
    /**
     * 菜单ID为空
     */
    String MENU_ID_EMPTY= "2002403";// 菜单ID为空
    
    /**
     * 菜单删除失败
     */
    String MENU_DEL_ERROR= "2002404";// 菜单删除失败
    
    /**
     * 菜单已存在
     */
    String MENU_IS_EXSIT= "2002405";// 菜单已存在
    
    /**
     * 参数为空
     */
    String MENU_PARAMS_EMPTY= "2002406";
    
    /** 菜单编码为空 **/
    String MENU_CODE_EMPTY= "2002407";
    
    /** 菜单类型为空 **/
    String MENU_TYPE_EMPTY= "2002408";
    
    /** 菜单顺序为空 **/
    String MENU_ORDER_EMPTY= "2002409";
    
    /** 菜单名称为空 **/
    String MENU_NAME_EMPTY= "2002410";
    
    /* =======================菜单信息常量定义 结束============================== */
    
}
