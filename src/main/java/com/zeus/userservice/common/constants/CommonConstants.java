/*
 * 文 件 名:  CommonConstants.java
 * 版    权:  Copyright © 2015-2017, 湖南物联聚创信息科技有限公司
 * 描    述:  CommonConstants.java 
 * 版    本：   1.0 
 * 创 建 人:  lidesheng
 * 创建时间: 2017年10月31日 下午4:04:18
*/
package com.zeus.userservice.common.constants;

/** 
 * @ClassName: CommonConstants
 * @Description: 用户服务公用常量定义
 * @author lidesheng 2017年10月31日 下午4:04:18
 * 
 */
public interface CommonConstants{
    
    static final String USER_STATE_N= "N";//用户状态(N正常 )
    
    static final String USER_STATE_L= "L";//用户状态(L锁定) 
    
    static final String USER_STATE_D= "D";//用户状态(D删除-禁用) 
    
    static final String COM_ONE= "1"; //常量1
    
    static final String COM_TWO= "2"; //常量2
    
    static final String USER_ADD_SUBMIT= "add"; //新增方式
    
    static final String USER_EDIT_SUBMIT= "edit"; //修改方式
    
    static final String USER_DELETE_SUBMIT= "delete"; //删除方式
}
