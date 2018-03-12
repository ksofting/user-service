package com.zeus.userservice.commerce.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zeus.common.constants.BaseConstants;
import com.zeus.common.core.exception.ServiceException;
import com.zeus.common.core.info.PageDataInfo;
import com.zeus.common.entity.PageQueryInfo;
import com.zeus.common.entity.Result;
import com.zeus.common.entity.ResultBean;
import com.zeus.common.entity.person.AuthUserDTO;
import com.zeus.common.mybatis.model.user.HsAuthUser;
import com.zeus.userservice.commerce.service.UserService;
import com.zeus.userservice.common.constants.MessageConstants;

/**
 * 
 * @ClassName: UserController
 * @Description: 用户管理服务控制器
 * @author lidesheng 2017年10月25日 下午3:07:07
 *
 */
@RestController
@RequestMapping("/user/")
@ResponseBody
public class UserController{
    
    @Resource
    private UserService userService;
    
    /**
     * 
     * @Title: queryPage
     * @Description: 查询用户信息列表
     * @param @param pageNum  当前页数
     * @param @param pageSize 每页大小
     * @return ResultBean<PageDataInfo<AuthUserDTO>> 用户列表集合
     * @throws 
     * @author lidesheng 2017年10月30日 下午1:13:07
     */
    @GetMapping(value= "querylist")
    public ResultBean<PageDataInfo<AuthUserDTO>> queryPage(
        @RequestParam(value= "pageNum",defaultValue= "0",required= false) int pageNum,
        @RequestParam("pageSize") int pageSize,AuthUserDTO seachParams){
        PageQueryInfo<AuthUserDTO> queryParam= new PageQueryInfo<>(pageNum,pageSize,seachParams);
        ResultBean<PageDataInfo<AuthUserDTO>> result= new ResultBean<>();
        result.setData(userService.queryPage(queryParam));
        return result;
    }
    
    /**
     * 
     * @Title: queryCombox
     * @Description: 查询带下拉框用户分页
     * @param @param seachParams
     * @param @return
     * @return ResultBean<PageDataInfo<AuthUserDTO>>
     * @throws 
     * @author lidesheng 2017年11月9日 上午10:58:21
     */
    @PostMapping(value= "querycombox")
    public ResultBean<PageDataInfo<HsAuthUser>> queryCombox(PageQueryInfo<AuthUserDTO> seachParams){
        PageDataInfo<HsAuthUser> pageDataInfo= userService.selectUserLikeSeach(seachParams);
        return new ResultBean<PageDataInfo<HsAuthUser>>(pageDataInfo);
    }
    
    /**
     * 
     * @Title: selectHsAuthUserByUserId
     * @Description: 查询用户明细
     * @param @param userId
     * @param @return
     * @return ResultBean<AuthUserDTO>
     * @throws 
     * @author lidesheng 2017年11月7日 下午2:12:26
     */
    @GetMapping(value= "{userId}/details")
    public ResultBean<AuthUserDTO> selectHsAuthUserByUserId(@PathVariable("userId") Long userId){
        AuthUserDTO userinfo= userService.selectDetailsById(userId);
        return new ResultBean<AuthUserDTO>(userinfo);
    }
    
    /**
     * 
     * @Title: selectHsAuthUserByPrimaryKey
     * @Description: 通过用户Id查询用户信息
     * @param @param userId 用户Id
     * @param @return
     * @return ResultBean<HsAuthUser>
     * @throws 
     * @author lidesheng 2017年11月3日 下午4:57:10
     */
    @GetMapping(value= "{userId}/info")
    public ResultBean<HsAuthUser> selectHsAuthUserByPrimaryKey(@PathVariable("userId") Long userId){
        HsAuthUser hsAuthUser= userService.selectHsAuthUserByPrimaryKey(userId);
        return new ResultBean<HsAuthUser>(hsAuthUser);
    }
    
    /**
     * 
     * @Title: addHsAuthUser
     * @Description: 新增用户信息
     * @param @param hsAuthUser 用户实体对象
     * @param @return
     * @return Result 统一封装结果集
     * @throws 
     * @author lidesheng 2017年10月30日 下午1:13:43
     */
    @PostMapping(value= "add")
    public Result addHsAuthUser(@RequestBody AuthUserDTO hsAuthUser){
        int result= userService.addHsAuthUser(hsAuthUser);
        if(result> BaseConstants.NUM.ZERO){
            return new Result();
        }
        throw new ServiceException(MessageConstants.USER_ADD_ERROR);
    }
    
    /**
     * 
     * @Title: updateHsAuthUser
     * @Description: 修改用户信息
     * @param @param hsAuthUser
     * @param @return
     * @return Result 统一封装结果集
     * @throws 
     * @author lidesheng 2017年10月30日 下午1:13:56
     */
    @PutMapping(value= "save")
    public Result updateHsAuthUser(@RequestBody AuthUserDTO hsAuthUser){
        int result= userService.updateHsAuthUser(hsAuthUser);
        if(result> BaseConstants.NUM.ZERO){
            return new Result();
        }
        throw new ServiceException(MessageConstants.USER_UPDATE_ERROR);
    }
    
    /**
     * 
     * @Title: updateUserPwd
     * @Description: 重置用户密码
     * @param @param hsAuthUser
     * @param @return
     * @return Result
     * @throws 
     * @author lidesheng 2017年11月1日 下午6:58:26
     */
    @PutMapping(value= "resetpwd")
    public Result updateUserPwd(@RequestBody AuthUserDTO hsAuthUser){
        int result= userService.updateUserPwd(hsAuthUser);
        if(result> BaseConstants.NUM.ZERO){
            return new Result();
        }
        throw new ServiceException(MessageConstants.USER_UPDATE_PWD);
    }
    
    /**
     * 
     * @Title: updateStateByUnlock
     * @Description: 解锁用户
     * @param @param hsAuthUser
     * @param @return
     * @return Result
     * @throws 
     * @author lidesheng 2017年11月6日 上午10:40:01
     */
    @PutMapping(value= "{userId}/unlock")
    public Result updateStateByUnlock(@PathVariable("userId") Long userId){
        int result= userService.updateStateByUnlock(userId);
        if(result> BaseConstants.NUM.ZERO){
            return new Result();
        }
        throw new ServiceException(MessageConstants.USER_UNLOCK_ERROR);
    }
    
    /**
     * 
     * @Title: deleteHsAuthUser
     * @Description: 删除用户信息（逻辑删除）
     * @param @param userId 用户ID
     * @param @return
     * @return Result
     * @throws 
     * @author lidesheng 2017年10月30日 下午1:14:17
     */
    @DeleteMapping(value= "{userId}/delete")
    public Result deleteHsAuthUser(@PathVariable("userId") Long userId){
        int result= userService.deleteHsAuthUser(userId);
        if(result> BaseConstants.NUM.ZERO){
            return new Result();
        }
        throw new ServiceException(MessageConstants.USER_DELETE_ERROR);
    }
}
