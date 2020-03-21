package com.zengtengpeng.ManyToMany.controller;

import com.zengtengpeng.ManyToMany.service.ManyToManyUserService;
import com.zengtengpeng.ManyToMany.bean.ManyToManyUser;
import java.util.ArrayList;
import com.zengtengpeng.common.annotation.Auth;
import javax.annotation.Resource;
import com.zengtengpeng.common.utils.ExcelUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zengtengpeng.common.bean.DataRes;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import com.zengtengpeng.ManyToMany.bean.ManyToManyRole;
import com.zengtengpeng.ManyToMany.service.ManyToManyRoleService;


/**
 *多对多角色 controller
 */
@Controller
public class ManyToManyRoleController  {

	/**
	 * 多对多用户
	 */
	@Resource
	private ManyToManyUserService manyToManyUserService;



	@Resource
	private ManyToManyRoleService manyToManyRoleService;
	/**
	 * 跳转到列表页面
	 */
	@RequestMapping("manyToManyRole/gotoList")
	public String gotoList(ManyToManyRole manyToManyRole,HttpServletRequest request,HttpServletResponse response){
		return "ManyToMany/test_many_to_many_role_list";
	}


	/**
	 * 跳转到详情页面
	 */
	@RequestMapping("manyToManyRole/gotoDetail")
	@Auth("manyToManyRole/save")
	public String gotoDetail(ManyToManyRole manyToManyRole,HttpServletRequest request,HttpServletResponse response){
		List<ManyToManyUser> data = manyToManyUserService.selectAll(null);
		request.setAttribute("goto_detail_test_many_to_many_user",data);

		if(manyToManyRole.getId()!=null){
			ManyToManyRole t = manyToManyRoleService.selectManyToManyUserAndManyToManyRoleByCondition(manyToManyRole).get(0);
			request.setAttribute("test_many_to_many_role",t);
			List<ManyToManyUser> list = t.getManyToManyUserList();
			List<String> sId=new ArrayList<>();
			if(list!=null){
				for (ManyToManyUser manyToManyUser : list) {
					sId.add(manyToManyUser.getId().toString());
				}
			}
			request.setAttribute("sId",sId);

		}else {
			request.setAttribute("test_many_to_many_role",manyToManyRole);
		}
		return "ManyToMany/test_many_to_many_role_detail";

	}


	/**
	 * 删除-多对多角色
	 */
	@ResponseBody
	@Auth("manyToManyRole/deleteByPrimaryKey")
	@RequestMapping("manyToManyRole/deleteByPrimaryKey")
	public DataRes deleteByPrimaryKey(ManyToManyRole manyToManyRole,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyRoleService.deleteByPrimaryKey(manyToManyRole));
	}


	/**
	 *  保存 (主键为空则增加否则修改)-> 多对多角色
	 */
	@ResponseBody
	@RequestMapping("manyToManyRole/save")
	public DataRes save(ManyToManyRole manyToManyRole,HttpServletRequest request,HttpServletResponse response){
		if(manyToManyRole.getId()==null){
			return DataRes.success(manyToManyRoleService.insert(manyToManyRole));
		}
		return DataRes.success(manyToManyRoleService.update(manyToManyRole));

	}


	/**
	 * 根据主键查询->多对多角色
	 */
	@ResponseBody
	@Auth("manyToManyRole/selectAllByPaging")
	@RequestMapping("manyToManyRole/selectByPrimaryKey")
	public DataRes selectByPrimaryKey(ManyToManyRole manyToManyRole,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyRoleService.selectByPrimaryKey(manyToManyRole));
	}


	/**
	 * 根据条件查询(所有的实体属性都是条件,如果为空则忽略该字段)->多对多角色
	 */
	@ResponseBody
	@Auth("manyToManyRole/selectAllByPaging")
	@RequestMapping("manyToManyRole/selectByCondition")
	public DataRes selectByCondition(ManyToManyRole manyToManyRole,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyRoleService.selectByCondition(manyToManyRole));
	}


	/**
	 * 分页查询 (所有的实体属性都是条件,如果为空则忽略该字段) (详见Page类.所以的实体都继承该类 默认 page=1 pageSize=10)->多对多角色
	 */
	@ResponseBody
	@Auth("manyToManyRole/selectAllByPaging")
	@RequestMapping("manyToManyRole/selectAllByPaging")
	public DataRes selectAllByPaging(ManyToManyRole manyToManyRole,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyRoleService.selectAllByPaging(manyToManyRole));
	}


	/**
	 * 导出报表->多对多角色
	 */
	@RequestMapping("manyToManyRole/export")
	public void export(ManyToManyRole manyToManyRole,HttpServletRequest request,HttpServletResponse response){
		List<ManyToManyRole> list= manyToManyRoleService.selectAll(manyToManyRole);
		Map<String, String> header = new LinkedHashMap<>();
		header.put("id", "角色");
		header.put("name", "角色名称");
		header.put("status_", "{\"name\":\"状态\",\"0\":\"启用\",\"1\":\"禁用\"}");
		header.put("createUserId", "创建者");
		header.put("createTime_", "创建时间");
		header.put("updateUserId", "更新者");
		header.put("updateTime_", "更新时间");
		header.put("dels_", "{\"name\":\"是否删除\",\"0\":\"正常\",\"1\":\"删除\"}");
		ExcelUtils.exportExcel("多对多角色",header,list,response,request);

	}


	/**
	 * 级联查询(带分页) 多对多用户--多对多角色
	 */
	@Auth("manyToManyRole/selectAllByPaging")
	@RequestMapping("manyToManyRole/selectManyToManyUserAndManyToManyRole")
	@ResponseBody
	public DataRes selectManyToManyUserAndManyToManyRole(ManyToManyRole manyToManyRole,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyRoleService.selectManyToManyUserAndManyToManyRole(manyToManyRole));
	}


	/**
	 * 级联条件查询 多对多用户--多对多角色
	 */
	@Auth("manyToManyRole/selectAllByPaging")
	@RequestMapping("manyToManyRole/selectManyToManyUserAndManyToManyRoleByCondition")
	@ResponseBody
	public DataRes selectManyToManyUserAndManyToManyRoleByCondition(ManyToManyRole manyToManyRole,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyRoleService.selectManyToManyUserAndManyToManyRoleByCondition(manyToManyRole));
	}


	/**
	 * 级联删除(根据主键删除) 多对多用户--多对多角色
	 */
	@Auth("manyToManyRole/deleteByPrimaryKey")
	@RequestMapping("manyToManyRole/deleteManyToManyUserAndManyToManyRole")
	@ResponseBody
	public DataRes deleteManyToManyUserAndManyToManyRole(ManyToManyRole manyToManyRole,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyRoleService.deleteManyToManyUserAndManyToManyRole(manyToManyRole));
	}


	/**
	 * 根据主表id查询外表所有数据(带分页)
	 */
	@Auth("manyToManyRole/selectAllByPaging")
	@RequestMapping("manyToManyRole/selectManyToManyRoleByManyToManyUser")
	@ResponseBody
	public DataRes selectManyToManyRoleByManyToManyUser(HttpServletRequest request,HttpServletResponse response,ManyToManyRole manyToManyRole){
		return DataRes.success(manyToManyRoleService.selectManyToManyRoleByManyToManyUser(manyToManyRole));
	}



}
