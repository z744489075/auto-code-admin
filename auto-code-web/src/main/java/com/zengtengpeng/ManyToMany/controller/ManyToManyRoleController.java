package com.zengtengpeng.ManyToMany.controller;

import com.zengtengpeng.ManyToMany.service.ManyToManyUserService;
import com.zengtengpeng.ManyToMany.bean.ManyToManyUser;
import java.util.ArrayList;
import com.zengtengpeng.common.annotation.Auth;
import springfox.documentation.annotations.ApiIgnore;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.zengtengpeng.ManyToMany.bean.ManyToManyRole;
import com.zengtengpeng.ManyToMany.service.ManyToManyRoleService;


/**
 *多对多角色 controller
 */
@Api(description="多对多角色")
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
	@ApiIgnore
	public String gotoList(ManyToManyRole manyToManyRole,HttpServletRequest request,HttpServletResponse response){
		return "ManyToMany/test_many_to_many_role_list";
	}


	/**
	 * 跳转到详情页面
	 */
	@RequestMapping("manyToManyRole/gotoDetail")
	@Auth("manyToManyRole/save")
	@ApiIgnore
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
	@ApiOperation(value="根据主键删除", notes="参数只用到了主键id,其他参数忽略" ,httpMethod="POST")
	public DataRes deleteByPrimaryKey(ManyToManyRole manyToManyRole,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyRoleService.deleteByPrimaryKey(manyToManyRole));
	}


	/**
	 *  保存 (主键为空则增加否则修改)-> 多对多角色
	 */
	@ResponseBody
	@RequestMapping("manyToManyRole/save")
	@ApiOperation(value="保存", notes="主键为空则增加否则修改" ,httpMethod="POST")
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
	@ApiOperation(value="根据主键查询", notes="参数只用到了主键." ,httpMethod="POST")
	public DataRes selectByPrimaryKey(ManyToManyRole manyToManyRole,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyRoleService.selectByPrimaryKey(manyToManyRole));
	}


	/**
	 * 根据条件查询(所有的实体属性都是条件,如果为空则忽略该字段)->多对多角色
	 */
	@ResponseBody
	@Auth("manyToManyRole/selectAllByPaging")
	@RequestMapping("manyToManyRole/selectByCondition")
	@ApiOperation(value="根据条件查询", notes="参数为空则忽略." ,httpMethod="POST")
	public DataRes selectByCondition(ManyToManyRole manyToManyRole,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyRoleService.selectByCondition(manyToManyRole));
	}


	/**
	 * 分页查询 (所有的实体属性都是条件,如果为空则忽略该字段) (详见Page类.所以的实体都继承该类 默认 page=1 pageSize=10)->多对多角色
	 */
	@ResponseBody
	@Auth("manyToManyRole/selectAllByPaging")
	@RequestMapping("manyToManyRole/selectAllByPaging")
	@ApiOperation(value="分页查询", notes="默认page=1pageSize等于10详见Page类(所有bean都继承该类).参数为空则忽略" ,httpMethod="POST")
	public DataRes selectAllByPaging(ManyToManyRole manyToManyRole,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyRoleService.selectAllByPaging(manyToManyRole));
	}


	/**
	 * 导出报表->多对多角色
	 */
	@RequestMapping("manyToManyRole/export")
	@ApiOperation(value="导出excel", notes="导出全部数据.参数为空则忽略." ,httpMethod="POST")
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
	@ApiOperation(value="外表级联查询(带分页)", notes="构建外表 级联查询(带分页) 默认 page=1 pageSize等于10 详见 Page类(所有bean都继承该类)" ,httpMethod="POST")
	public DataRes selectManyToManyUserAndManyToManyRole(ManyToManyRole manyToManyRole,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyRoleService.selectManyToManyUserAndManyToManyRole(manyToManyRole));
	}


	/**
	 * 级联条件查询 多对多用户--多对多角色
	 */
	@Auth("manyToManyRole/selectAllByPaging")
	@RequestMapping("manyToManyRole/selectManyToManyUserAndManyToManyRoleByCondition")
	@ResponseBody
	@ApiOperation(value="外表级联条件查询", notes="外表级联条件查询" ,httpMethod="POST")
	public DataRes selectManyToManyUserAndManyToManyRoleByCondition(ManyToManyRole manyToManyRole,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyRoleService.selectManyToManyUserAndManyToManyRoleByCondition(manyToManyRole));
	}


	/**
	 * 级联删除(根据主键删除) 多对多用户--多对多角色
	 */
	@Auth("manyToManyRole/deleteByPrimaryKey")
	@RequestMapping("manyToManyRole/deleteManyToManyUserAndManyToManyRole")
	@ResponseBody
	@ApiOperation(value="外表级联删除(根据主键删除)", notes="外表级联删除(根据主键删除)" ,httpMethod="POST")
	public DataRes deleteManyToManyUserAndManyToManyRole(ManyToManyRole manyToManyRole,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyRoleService.deleteManyToManyUserAndManyToManyRole(manyToManyRole));
	}


	/**
	 * 根据主表id查询外表所有数据(带分页)
	 */
	@Auth("manyToManyRole/selectAllByPaging")
	@RequestMapping("manyToManyRole/selectManyToManyRoleByManyToManyUser")
	@ResponseBody
	@ApiOperation(value="根据主表id查询外表所有数据(带分页)", notes="根据主表id查询外表所有数据(带分页)" ,httpMethod="POST")
	public DataRes selectManyToManyRoleByManyToManyUser(HttpServletRequest request,HttpServletResponse response,ManyToManyRole manyToManyRole){
		return DataRes.success(manyToManyRoleService.selectManyToManyRoleByManyToManyUser(manyToManyRole));
	}



}
