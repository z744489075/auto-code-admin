package com.zengtengpeng.sys.controller;

import javax.annotation.Resource;

import com.zengtengpeng.common.annotation.Auth;
import com.zengtengpeng.common.utils.ExcelUtils;
import com.zengtengpeng.sys.bean.SysAuth;
import com.zengtengpeng.sys.bean.SysUser;
import com.zengtengpeng.sys.service.SysAuthService;
import com.zengtengpeng.utils.UserUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zengtengpeng.common.bean.DataRes;

import java.util.*;

import com.zengtengpeng.sys.bean.SysRole;
import com.zengtengpeng.sys.service.SysRoleService;
import org.springframework.stereotype.Controller;

/**
 * 
 * @author zengtp
 *
 */
@Controller
public class SysRoleController {
	
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysAuthService sysAuthService;

	/**
	 * 修改状态
	 * @param sysRole
	 * @return
	 */
	@RequestMapping("sysRole/updateStatus")
	@ResponseBody
	@Auth("sysRole/save")
	public DataRes updateStatus(SysRole sysRole, HttpServletRequest request, HttpServletResponse response){
		SysUser user = UserUtils.getUser(request.getSession());
		sysRole.setUpdateUserId(user.getId());
		return DataRes.success(sysRoleService.update(sysRole));
	}

    /**
	 * 保存 如果id存在则修改否则新增
	 * @param sysRole
	 * @return
	 */
	@RequestMapping("sysRole/save")
	@ResponseBody
	public DataRes save( @RequestParam(value = "auths[]",required=false) List<String> auths,SysRole sysRole, HttpServletRequest request, HttpServletResponse response){
		SysUser user = UserUtils.getUser(request.getSession());
		sysRole.setCreateUserId(user.getId());
		sysRole.setUpdateUserId(user.getId());
		if(sysRole.getId()==null){
			return DataRes.success(sysRoleService.insert(sysRole,auths));
		}
		return DataRes.success(sysRoleService.update(sysRole,auths));
	}

    /**
     * 根据主键查询
     * @param sysRole
     * @return
     */
	@RequestMapping("sysRole/selectByPrimaryKey")
	@ResponseBody
	public DataRes selectByPrimaryKey(SysRole sysRole, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(sysRoleService.selectByPrimaryKey(sysRole));
    }

    /**
	* 根据条件查询
	*/
	@RequestMapping("sysRole/querySysRoleByCondition")
	@ResponseBody
	public DataRes queryByCondition(SysRole sysRole, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(sysRoleService.queryByCondition(sysRole));
    }

   /**
	* 分页查询
	* @param sysRole 参数
	* @return
	*/
	@RequestMapping("sysRole/selectAll")
	@ResponseBody
	public DataRes selectAll(SysRole sysRole,HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(sysRoleService.selectAllByPaging(sysRole));
    }

	/**
	* 导出数据
	* @return
	*/
	@RequestMapping("sysRole/export")
	public void export(SysRole sysRole,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysRole> list= sysRoleService.selectAll(sysRole);
		Map<String, String> header = new LinkedHashMap<>();
        header.put("id", "后台角色");
        header.put("name", "角色名称");
		header.put("status_", "{\"name\":\"状态\",\"0\":\"启用\",\"1\":\"禁用\"}");
        header.put("createUserId", "创建者");
		header.put("createTime_", "创建时间");
        header.put("updateUserId", "更新者");
		header.put("updateTime_", "更新时间");
		header.put("dels_", "{\"name\":\"是否删除\",\"0\":\"正常\",\"1\":\"删除\"}");
		ExcelUtils.exportExcel("后台角色",header,list,response,request);
    }

	/**
	* 跳转到列表页面
	* @return
	*/
	@RequestMapping("sysRole/gotoList")
	public String gotoList(SysRole sysRole, HttpServletRequest request, HttpServletResponse response){
		return "sys/sys_role_list";
	}

	/**
	* 跳转到详情页面
	* @return
	*/
	@RequestMapping("sysRole/gotoDetail")
	@Auth("sysRole/save")
	public String gotoDetail(SysRole sysRole, HttpServletRequest request, HttpServletResponse response){
		if(sysRole.getId()!=null){
			request.setAttribute("sys_role",sysRoleService.selectByPrimaryKey(sysRole));
		}else {
			request.setAttribute("sys_role",sysRole);
		}
		return "sys/sys_role_detail";
	}

	@RequestMapping("sysRole/detail")
	@Auth("sysRole/save")
	@ResponseBody
	public DataRes detail(SysRole sysRole, HttpServletRequest request, HttpServletResponse response){
		SysAuth sysAuth=new SysAuth();
		sysAuth.setOrderByString(" order by sort asc");
		List<SysAuth> t = sysAuthService.selectAll(sysAuth);
		List<SysAuth> csa= sysAuthService.queryByRole(sysRole);
		Map map=new HashMap();
		map.put("data",t);
		List<Integer> ck=new ArrayList<>();
		csa.forEach(tt->{
			ck.add(tt.getId());
		});
		map.put("ck",ck);
		return DataRes.success(map);
	}


}
