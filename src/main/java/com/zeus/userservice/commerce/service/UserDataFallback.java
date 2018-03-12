/*
 * 文 件 名: UserDataFallback.java
 * 版 权: Copyright © 2015-2017, 湖南物联聚创信息科技有限公司
 * 描 述: UserDataFallback.java
 * 版 本： 1.0
 * 创 建 人: lidesheng
 * 创建时间: 2017年10月31日 下午4:04:18
 */
package com.zeus.userservice.commerce.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.zeus.common.core.exception.ServiceException;
import com.zeus.common.core.info.PageDataInfo;
import com.zeus.common.entity.PageQueryInfo;
import com.zeus.common.entity.person.AuthUserDTO;
import com.zeus.common.mybatis.model.user.HsAuthUser;
import com.zeus.userservice.common.constants.MessageConstants;

@Component
public class UserDataFallback implements IUserData{
    
    /**
     * (非 Javadoc) 
     * @Title: selectHsAuthUserRolesByUserId
     * @Description: 用户所有角色
     * @throws 
     * @author lidesheng 2017年11月7日 上午11:37:16
     */
    @Override
    public AuthUserDTO selectDetailsById(@PathVariable("userId") Long userId){
        throw new ServiceException(MessageConstants.USER_ID_MANAGE);
    }
    
    /**
     * (非 Javadoc) 
     * @Title: selectUserLikeSeach
     * @Description: 用户信息列表
     * @throws 
     * @author lidesheng 2017年11月9日 上午10:53:59
     */
    @Override
    public PageDataInfo<HsAuthUser> selectUserLikeSeach(PageQueryInfo queryParams){
        throw new ServiceException(MessageConstants.USER_COMBOX_LIST);
    }
    
    /**
     * (非 Javadoc) 
     * @Title: queryPage
     * @Description: 查询用户信息列表异常
     * @throws 
     * @author lidesheng 2017年11月1日 上午11:36:48
     */
    @Override
    public PageDataInfo<AuthUserDTO> queryPage(@RequestBody PageQueryInfo<AuthUserDTO> queryParam){
        throw new ServiceException(MessageConstants.USER_UPDATE_BACK);
    }
    
    /**
     * (非 Javadoc) 
     * @Title: updateStateByUnlock
     * @Description: 解锁用户失败
     * @throws 
     * @author lidesheng 2017年11月6日 上午9:35:18
     */
    @Override
    public int updateStateByUnlock(@PathVariable("userId") Long userId){
        throw new ServiceException(MessageConstants.USER_UNLOCK_ERROR);
    }
    
    /**
     * (非 Javadoc) 
     * @Title: updateUserPwd
     * @Description: 修改用户失败异常
     * @throws 
     * @author lidesheng 2017年11月6日 上午8:23:39
     */
    @Override
    public int updateUserPwd(AuthUserDTO authUserDTO){
        throw new ServiceException(MessageConstants.USER_UPDATE_PWD);
    }
    
    /**
     * (非 Javadoc) 
     * @Title: addHsAuthUser
     * @Description: 新增用户单记录异常
     * @throws 
     * @author lidesheng 2017年11月1日 上午11:36:29
     */
    @Override
    public int addHsAuthUser(HsAuthUser hsAuthUser){
        throw new ServiceException(MessageConstants.USER_ADD_ERROR);
    }
    
    /**
     * (非 Javadoc) 
     * @Title: updateHsAuthUser
     * @Description: 单记录更新用户信息对象
     * @throws 
     * @author lidesheng 2017年11月1日 上午11:36:11
     */
    @Override
    public int updateHsAuthUser(HsAuthUser hsAuthUser){
        throw new ServiceException(MessageConstants.USER_UPDATE_ERROR);
    }
    
    /**
     * (非 Javadoc) 
     * @Title: deleteHsAuthUser
     * @Description: 删除用户信息异常
     * @throws 
     * @author lidesheng 2017年11月1日 上午11:35:55
     */
    @Override
    public int deleteHsAuthUser(Long userId){
        throw new ServiceException(MessageConstants.USER_DELETE_ERROR);
    }
    
    /**
     * (非 Javadoc) 
     * @Title: selectUserInfoExist
     * @Description: 判断用户信息是否存在异常
     * @throws 
     * @author lidesheng 2017年11月1日 上午11:35:31
     */
    @Override
    public int selectUserInfoExist(AuthUserDTO authUserDTO){
        throw new ServiceException(MessageConstants.USER_NAME_EXIST);
    }
    
    /**
     * (非 Javadoc) 
     * @Title: addAuthUserByTransact异常
     * @Description: 新增用户
     * @throws 
     * @author lidesheng 2017年11月1日 上午11:35:14
     */
    @Override
    public int addAuthUserByTransact(AuthUserDTO hsAuthUser){
        throw new ServiceException(MessageConstants.USER_ADD_TRANSACT_ERROR);
    }
    
    /** (非 Javadoc) 
     * @Title: updateHsAuthUserByTransact
     * @Description: 修改用户信息异常
     * @throws 
     * @author lidesheng 2017年11月1日 上午11:30:10
     */
    @Override
    public int updateHsAuthUserByTransact(AuthUserDTO hsAuthUser){
        throw new ServiceException(MessageConstants.USER_UPDATE_TRANSACT_ERROR);
    }
    
    /** (非 Javadoc) 
     * @Title: selectHsAuthUserByPrimaryKey
     * @Description: 通过ID查询用户信息异常
     * @throws 
     * @author lidesheng 2017年11月3日 下午3:59:16
     */
    @Override
    public HsAuthUser selectHsAuthUserByPrimaryKey(Long userId){
        throw new ServiceException(MessageConstants.USER_UPDATE_TRANSACT_ERROR);
    }
    
    /**
     * (非 Javadoc) 
     * @Title: queryCache
     * @Description: 统一缓存
     * @throws 
     * @author zhangxiaohui 2017年11月10日 上午10:50:27
     */
    @Override
    public PageDataInfo<HsAuthUser> queryCache(@RequestParam("pageNum") int pageNum,
        @RequestParam("pageSize") int pageSize){
        throw new ServiceException(MessageConstants.USER_SEACH_LIST_ERROR);
    }
    
    @Override
    public Map<String,HsAuthUser> selectUserListApi(List<Long> userId){
        // TODO Auto-generated method stub
        return null;
    }
}
