package com.zengtengpeng.sys.controller;

import javax.annotation.Resource;

import com.zengtengpeng.common.enums.ResponseCode;
import com.zengtengpeng.common.utils.ExcelUtils;
import com.zengtengpeng.sys.bean.SysRole;
import com.zengtengpeng.sys.service.SysRoleService;
import com.zengtengpeng.utils.UserUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zengtengpeng.common.bean.DataRes;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import com.zengtengpeng.sys.bean.SysUser;
import com.zengtengpeng.sys.service.SysUserService;
import org.springframework.stereotype.Controller;
import com.zengtengpeng.common.annotation.Auth;

/**
 * 
 * @author zengtp
 *
 */
@Controller
public class SysUserController {
	
	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysRoleService sysRoleService;

	/**
	 * 删除
	 * @param sysUser
	 * @return
	 */
	@RequestMapping("sysUser/deleteByPrimaryKey")
	@ResponseBody
	public DataRes deleteByPrimaryKey(SysUser sysUser, HttpServletRequest request, HttpServletResponse response){
		return DataRes.success(sysUserService.deleteByPrimaryKey(sysUser));
	}

	/**
	 * 修改状态
	 * @param sysUser
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("sysUser/updateStatus")
	@ResponseBody
	@Auth("sysUser/save")
	public DataRes updateStatus(SysUser sysUser, HttpServletRequest request, HttpServletResponse response){
		SysUser user = UserUtils.getUser(request.getSession());
		sysUser.setUpdateUserId(user.getId());
		return DataRes.success(sysUserService.update(sysUser));
	}


	@RequestMapping("sysUser/gotoChangePassword")
	@Auth
	public String gotoChangePassword(SysUser sysUser,String newPassword, HttpServletRequest request, HttpServletResponse response){
		return "sys/change_password";
	}
	/**
	 * 修改密码
	 * @param sysUser
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("sysUser/changePassword")
	@ResponseBody
	@Auth
	public DataRes changePassword(SysUser sysUser,String newPassword, HttpServletRequest request, HttpServletResponse response){
		SysUser user = UserUtils.getUser(request.getSession());
		if(!user.getPassword().equals(DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes()))){
			return DataRes.error(ResponseCode.LOGIN_UNPASSWORD.code(),ResponseCode.LOGIN_UNPASSWORD.desc());
		}
		SysUser u = UserUtils.getUser(request.getSession());
		user.setUpdateUserId(u.getId());
		user.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
		return DataRes.success(sysUserService.updatePassword(user));
	}
    /**
	 * 保存 如果id存在则修改否则新增
	 * @param sysUser
	 * @return
	 */
	@RequestMapping("sysUser/save")
	@ResponseBody
	public DataRes save(@RequestParam(value = "roles[]",required=false) List<String> roles,SysUser sysUser, HttpServletRequest request, HttpServletResponse response){
	    if(!StringUtils.isEmpty(sysUser.getPassword())){
	        sysUser.setPassword(DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes()));
        }
		SysUser user = UserUtils.getUser(request.getSession());
		sysUser.setCreateUserId(user.getId());
		sysUser.setUpdateUserId(user.getId());
		if(sysUser.getId()==null){
			return DataRes.success(sysUserService.insert(sysUser,roles));
		}
		return DataRes.success(sysUserService.update(sysUser,roles));
	}

    /**
     * 根据主键查询
     * @param sysUser
     * @return
     */
	@RequestMapping("sysUser/selectByPrimaryKey")
	@ResponseBody
	public DataRes selectByPrimaryKey(SysUser sysUser, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(sysUserService.selectByPrimaryKey(sysUser));
    }

    /**
	* 根据条件查询
	*/
	@RequestMapping("sysUser/querySysUserByCondition")
	@ResponseBody
	public DataRes queryByCondition(SysUser sysUser, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(sysUserService.selectByCondition(sysUser));
    }

   /**
	* 分页查询
	* @param sysUser 参数
	* @return
	*/
	@RequestMapping("sysUser/selectAll")
	@ResponseBody
	public DataRes selectAll(SysUser sysUser,HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(sysUserService.selectAllByPaging(sysUser));
    }

	/**
	* 导出数据
	* @return
	*/
	@RequestMapping("sysUser/export")
	public void export(SysUser sysUser,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysUser> list= sysUserService.selectAll(sysUser);
		Map<String, String> header = new LinkedHashMap<>();
        header.put("id", "后台管理员");
        header.put("loginName", "登录名");
        header.put("no", "工号");
        header.put("name", "姓名");
        header.put("email", "邮箱");
        header.put("mobile", "手机");
		header.put("status_", "{\"name\":\"状态\",\"0\":\"启用\",\"1\":\"禁用\"}");
        header.put("createUserId", "创建者");
		header.put("createTime_", "创建时间");
        header.put("updateUserId", "更新者");
		header.put("updateTime_", "更新时间");
        header.put("remarks", "备注信息");
		ExcelUtils.exportExcel("后台管理员",header,list,response,request);
    }

	/**
	* 跳转到列表页面
	* @return
	*/
	@RequestMapping("sysUser/gotoList")
	public String gotoList(SysUser sysUser, HttpServletRequest request, HttpServletResponse response){
		return "sys/sys_user_list";
	}

	/**
	* 跳转到详情页面
	* @return
	*/
	@RequestMapping("sysUser/gotoDetail")
	@Auth("sysUser/save")
	public String gotoDetail(SysUser sysUser, HttpServletRequest request, HttpServletResponse response){
		SysRole t=new SysRole();
		t.setStatus(0);
		List<SysRole> sysRoles = sysRoleService.selectAll(t);
		List<SysRole>  usr=new ArrayList<>();
		request.setAttribute("sr",sysRoles);
		if(sysUser.getId()!=null){
			request.setAttribute("sys_user",sysUserService.selectByPrimaryKey(sysUser));
			//查询所有可用的权限
			usr= sysRoleService.queryByUser(sysUser);
		}else {
			request.setAttribute("sys_user",sysUser);
		}
		ArrayList<Integer> usrid=new ArrayList<>();
		usr.forEach(tt-> usrid.add(tt.getId()));
		request.setAttribute("usr",usrid);
		return "sys/sys_user_detail";
	}
}
