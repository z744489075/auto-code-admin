package com.zengtengpeng.sys.controller;

import javax.annotation.Resource;

import com.zengtengpeng.common.utils.ExcelUtils;
import com.zengtengpeng.sys.bean.SysRole;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zengtengpeng.common.bean.DataRes;
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

	/**
	 * 删除
	 * @param sysUser
	 * @return
	 */
	@RequestMapping("/sysUser/deleteByPrimaryKey")
	@ResponseBody
	public DataRes deleteByPrimaryKey(SysUser sysUser, HttpServletRequest request, HttpServletResponse response){
		return DataRes.success(sysUserService.deleteByPrimaryKey(sysUser));
	}

	@RequestMapping("/sysUser/updateStatus")
	@ResponseBody
	@Auth("sysUser/save")
	public DataRes updateStatus(SysUser sysUser, HttpServletRequest request, HttpServletResponse response){
		return DataRes.success(sysUserService.update(sysUser));
	}
    /**
	 * 保存 如果id存在则修改否则新增
	 * @param sysUser
	 * @return
	 */
	@RequestMapping("/sysUser/save")
	@ResponseBody
	public DataRes save(SysUser sysUser, HttpServletRequest request, HttpServletResponse response){
	    if(!StringUtils.isEmpty(sysUser.getPassword())){
	        sysUser.setPassword(DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes()));
        }
		if(sysUser.getId()==null){
			return DataRes.success(sysUserService.insert(sysUser));
		}
		return DataRes.success(sysUserService.update(sysUser));
	}

    /**
     * 根据主键查询
     * @param sysUser
     * @return
     */
	@RequestMapping("/sysUser/selectByPrimaryKey")
	@ResponseBody
	public DataRes selectByPrimaryKey(SysUser sysUser, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(sysUserService.selectByPrimaryKey(sysUser));
    }

    /**
	* 根据条件查询
	*/
	@RequestMapping("/sysUser/querySysUserByCondition")
	@ResponseBody
	public DataRes queryByCondition(SysUser sysUser, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(sysUserService.queryByCondition(sysUser));
    }

   /**
	* 分页查询
	* @param sysUser 参数
	* @return
	*/
	@RequestMapping("/sysUser/selectAll")
	@ResponseBody
	public DataRes selectAll(SysUser sysUser,HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(sysUserService.selectAllByPaging(sysUser));
    }

	/**
	* 导出数据
	* @param tests 参数
	* @return
	*/
	@RequestMapping("/sysUser/export")
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
	@RequestMapping("/sysUser/gotoList")
	public String gotoList(SysUser sysUser, HttpServletRequest request, HttpServletResponse response){
		return "sys/sys_user_list";
	}

	/**
	* 跳转到详情页面
	* @return
	*/
	@RequestMapping("/sysUser/gotoDetail")
	@Auth("sysUser/save")
	public String gotoDetail(SysUser sysUser, HttpServletRequest request, HttpServletResponse response){
		if(sysUser.getId()!=null){
			request.setAttribute("sys_user",sysUserService.selectByPrimaryKey(sysUser));
		}else {
			request.setAttribute("sys_user",sysUser);
		}
		return "sys/sys_user_detail";
	}
}
