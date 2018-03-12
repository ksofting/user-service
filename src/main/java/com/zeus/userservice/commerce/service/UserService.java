/*
 * 文 件 名:  UserService.java
 * 版    权:  Copyright © 2015-2017, 湖南物联聚创信息科技有限公司
 * 描    述:  UserService.java 
 * 版    本：   1.0 
 * 创 建 人:  lidesheng
 * 创建时间: 2017年10月31日 下午4:04:18
*/
package com.zeus.userservice.commerce.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;// 为空验证
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.framework.common.utils.Str2MD5;
import com.zeus.common.constants.BaseConstants;
import com.zeus.common.core.enums.impl.TrueFalseEnum;
import com.zeus.common.core.exception.ServiceException;
import com.zeus.common.core.id.IdUtil;
import com.zeus.common.core.info.PageDataInfo;
import com.zeus.common.entity.PageQueryInfo;
import com.zeus.common.entity.person.AuthUserDTO;
import com.zeus.common.entity.person.AuthUserRoleDTO;
import com.zeus.common.mybatis.model.user.HsAuthUser;
import com.zeus.common.service.service.BaseService;
import com.zeus.userservice.common.constants.CommonConstants;
import com.zeus.userservice.common.constants.MessageConstants;

/**
 * 
 * @ClassName: UserService
 * @Description: 用户管理业务逻辑处理类
 * @author lidesheng 2017年11月1日 下午4:20:21
 *
 */
@Service
public class UserService extends BaseService{
    
    @Autowired
    private IUserData userServiceImpl;
    
    /**
     * 
     * @Title: queryPage
     * @Description: 用户列表分页
     * @param @param seachParams (@AssemblyType) 表示 注解组合,联合查询需要用到
     * @param @return
     * @return PageDataInfo<AuthUserDTO>
     * @throws 
     * @author lidesheng 2017年11月9日 上午11:04:21
     */
    public PageDataInfo<AuthUserDTO> queryPage(PageQueryInfo<AuthUserDTO> seachParams){
        return userServiceImpl.queryPage(seachParams);
    }
    
    /**
     * 
     * @Title: selectUserLikeSeach
     * @Description: 带查询条件用户下拉框
     * @param @param queryParams
     * @param @return
     * @return PageDataInfo<HsAuthUser>
     * @throws 
     * @author lidesheng 2017年11月9日 上午11:03:57
     */
    public PageDataInfo<HsAuthUser> selectUserLikeSeach(@RequestBody PageQueryInfo queryParams){
        return userServiceImpl.selectUserLikeSeach(queryParams);
    }
    
    /**
     * 
     * @Title: selectHsAuthUserRolesByUserId
     * @Description: 用户所属角色
     * @param @param userId
     * @param @return
     * @return List<AuthUserDTO>
     * @throws 
     * @author lidesheng 2017年11月7日 下午1:22:05
     */
    public AuthUserDTO selectDetailsById(@PathVariable("userId") Long userId){
        return userServiceImpl.selectDetailsById(userId);
    }
    
    /**
     * 
     * @Title: addHsAuthUser
     * @Description: 新增用户信息：调用用户数据服务事物接口
     * @param @param hsAuthUser
     * @param @return Result 表示返回结果、否则抛出异常信息展示前端
     * @return Result 0000表示成功、否则抛出异常信息展示前端
     * @throws @author lidesheng 2017年10月24日 下午3:59:16
     */
    public int addHsAuthUser(AuthUserDTO hsAuthUser){
        hsAuthUser.setUserId(IdUtil.getId());
        getUserInfoValidate(hsAuthUser);//参数有效性验证
        if(! getContainsState(hsAuthUser.getStatus())){
            throw new ServiceException(MessageConstants.USER_STATE_ILLEGAL);
        }
        
        //初始化对象
        hsAuthUser.setCreatedBy(currentUserId());
        hsAuthUser.setCreatedTime(new Date());
        hsAuthUser.setExpireDate(new Date());
        hsAuthUser.setEmployeeId(hsAuthUser.getUserEmployeeCode());//保存员工ID
        hsAuthUser.setMessageInterval(BaseConstants.NUM.ZERO);
        hsAuthUser.setStatus(hsAuthUser.getStatus().toUpperCase());
        hsAuthUser.setRdStatus(TrueFalseEnum.TRUE.getValue());
        hsAuthUser.setRoles(getUserRoleList(hsAuthUser));
        hsAuthUser.setUserPwd(Str2MD5.MD532(hsAuthUser.getUserPwd()));
        return userServiceImpl.addAuthUserByTransact(hsAuthUser);
    }
    
    /**
     * 
     * @Title: getUserInfoValida
     * @Description: 验证用户信息有效性
     * @param @param hsAuthUser
     * @param 
     * @return void
     * @throws 
     * @author lidesheng 2017年11月1日 上午11:39:46
     */
    private void getUserInfoValidate(AuthUserDTO hsAuthUser){
        //参数有效性验证
        Assert.notNull(hsAuthUser.getUserId(),MessageConstants.USER_ID_MANAGE);
        Assert.notNull(hsAuthUser.getUserName(),MessageConstants.USER_NAME_MANAGE);
        Assert.notNull(hsAuthUser.getUserPwd(),MessageConstants.USER_PWD_MANAGE);
        Assert.notNull(hsAuthUser.getStatus(),MessageConstants.USER_STATE_MANAGE);
        Assert.notNull(hsAuthUser.getUserEmployeeCode(),MessageConstants.USER_STAFF_MANAGE);
        
        //参数业务性验证
        if(findUserInfoExist(hsAuthUser,CommonConstants.COM_ONE,CommonConstants.USER_EDIT_SUBMIT)){
            throw new ServiceException(MessageConstants.USER_NAME_EXIST);//用户名是否存在
        }
        else if(findUserInfoExist(hsAuthUser,
            CommonConstants.COM_TWO,
            CommonConstants.USER_EDIT_SUBMIT)){
            throw new ServiceException(MessageConstants.USER_STAFFID_EXIST); //员工是否存在
        }
    }
    
    /**
     * 
     * @Title: getUserRoleList
     * @Description: 查询用户角色信息
     * @param @param hsAuthUser
     * @param @return
     * @return List<AuthUserRoleDTO>
     * @throws 
     * @author lidesheng 2017年11月1日 下午3:24:50
     */
    private List<AuthUserRoleDTO> getUserRoleList(AuthUserDTO hsAuthUser){
        List<AuthUserRoleDTO> userRoleList= new ArrayList<AuthUserRoleDTO>();
        //角色数组转换集合
        if(hsAuthUser.getRoleId()!= null){
            for(String roleId : hsAuthUser.getRoleId()){
                AuthUserRoleDTO userRole= new AuthUserRoleDTO();
                userRole.setId(IdUtil.getId());
                userRole.setUserId(hsAuthUser.getUserId());
                userRole.setRoleId(Long.parseLong(roleId));
                userRoleList.add(userRole);
            }
        }
        return userRoleList;
    }
    
    /**
     * 
     * @Title: getContainsState
     * @Description: 查询是否存在用户状态
     * @param @param userState 前台输入用户状态
     * @param @return true 表示存在、false 表示不存在
     * @return boolean
     * @throws 
     * @author lidesheng 2017年11月1日 下午2:59:12
     */
    private boolean getContainsState(String userState){
        String[] stateArray= {
            CommonConstants.USER_STATE_N,CommonConstants.USER_STATE_L,CommonConstants.USER_STATE_D
        };
        return Arrays.asList(stateArray).contains(userState.toUpperCase());
    }
    
    /**
     * 
     * @Title: findUserInfoExist
     * @Description: TODO(查询用户名是否存在)
     * @param @param hsAuthUser
     * @param @param types: 1 表示用户名称、2表示员工ID
     * @param @return
     * @return boolean:true 表示存在、false 表示不存在
     * @throws @author lidesheng 2017年10月26日 上午9:19:07
     */
    private boolean findUserInfoExist(AuthUserDTO hsAuthUser,String types,String models){
        AuthUserDTO authUserModel= new AuthUserDTO();
        if(types.equals(CommonConstants.COM_ONE)){
            authUserModel.setUserName(hsAuthUser.getUserName());
        }
        else if(types.equals(CommonConstants.COM_TWO)){
            authUserModel.setEmployeeId(hsAuthUser.getUserEmployeeCode());
        }
        
        //访问模式
        if(models.equals(CommonConstants.USER_EDIT_SUBMIT)){
            authUserModel.setUserId(hsAuthUser.getUserId());
        }
        int countUsers= userServiceImpl.selectUserInfoExist(authUserModel);
        return countUsers== TrueFalseEnum.FALSE.getValue() ? true : false;
    }
    
    /**
     * 
     * @Title: updateHsAuthUser
     * @Description: 修改用户信息
     * @param @param hsAuthUser
     * @param @return
     * @return Result 0000表示成功、否则抛出异常信息展示前端
     * @throws @author lidesheng 2017年10月25日 下午1:51:36
     */
    public int updateHsAuthUser(AuthUserDTO hsAuthUser){
        hsAuthUser.setSubmitType(Integer.parseInt(CommonConstants.COM_ONE));
        getUserInfoValidate(hsAuthUser);//参数有效性验证
        if(! getContainsState(hsAuthUser.getStatus())){
            throw new ServiceException(MessageConstants.USER_STATE_ILLEGAL);
        }
        //返回
        hsAuthUser.setRoles(getUserRoleList(hsAuthUser));
        hsAuthUser.setUserPwd(Str2MD5.MD532(hsAuthUser.getUserPwd()));
        hsAuthUser.setModifiedTime(new Date());
        hsAuthUser.setModifiedBy(currentUserId());
        return userServiceImpl.updateHsAuthUserByTransact(hsAuthUser);
    }
    
    /**
     * 
     * @Title: updateUserPwd
     * @Description: 重置密码
     * @param @param hsAuthUser
     * @param @return
     * @return int
     * @throws 
     * @author lidesheng 2017年11月2日 上午9:18:04
     */
    public int updateUserPwd(AuthUserDTO hsAuthUser){
        hsAuthUser.setUserPwd(Str2MD5.MD532(hsAuthUser.getUserPwd()));
        Assert.notNull(hsAuthUser.getUserId(),MessageConstants.USER_ID_MANAGE);
        Assert.notNull(hsAuthUser.getUserPwd(),MessageConstants.USER_PWD_EMPTY);
        return userServiceImpl.updateUserPwd(hsAuthUser);
    }
    
    /**
     * 
     * @Title: deleteHsAuthUser
     * @Description: 逻辑删除用户
     * @param @param userId
     * @param @return
     * @return Result 0000表示成功、否则抛出异常信息展示前端
     * @throws @author lidesheng 2017年10月25日 下午2:59:18
     */
    public int deleteHsAuthUser(Long userId){
        Assert.notNull(userId,MessageConstants.USER_ID_MANAGE);
        return userServiceImpl.deleteHsAuthUser(userId);
    }
    
    /**
     * 
     * @Title: selectHsAuthUserByPrimaryKey
     * @Description: 通过ID查询用户信息
     * @param @param userId 用户Id
     * @param @return
     * @return HsAuthUser
     * @throws 
     * @author lidesheng 2017年11月3日 下午4:32:10
     */
    public HsAuthUser selectHsAuthUserByPrimaryKey(Long userId){
        Assert.notNull(userId,MessageConstants.USER_ID_MANAGE);
        return userServiceImpl.selectHsAuthUserByPrimaryKey(userId);
    }
    
    /**
     * 
     * @Title: updateStateByUnlock
     * @Description: 解锁用户
     * @param @param userId 用户ID
     * @param @return
     * @return int
     * @throws 
     * @author lidesheng 2017年11月6日 上午10:36:10
     */
    public int updateStateByUnlock(Long userId){
        Assert.notNull(userId,MessageConstants.USER_ID_MANAGE);
        return userServiceImpl.updateStateByUnlock(userId);
    }
    
}
