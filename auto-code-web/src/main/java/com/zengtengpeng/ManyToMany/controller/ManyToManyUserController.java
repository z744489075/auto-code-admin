package com.zengtengpeng.ManyToMany.controller;

import com.zengtengpeng.ManyToMany.service.ManyToManyRoleService;
import com.zengtengpeng.ManyToMany.bean.ManyToManyRole;
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
import com.zengtengpeng.ManyToMany.bean.ManyToManyUser;
import com.zengtengpeng.ManyToMany.service.ManyToManyUserService;


/**
 *多对多用户 controller
 */
@Api(description="多对多用户")
@Controller
public class ManyToManyUserController  {

	/**
	 * 多对多角色
	 */
	@Resource
	private ManyToManyRoleService manyToManyRoleService;



	@Resource
	private ManyToManyUserService manyToManyUserService;
	/**
	 * 跳转到列表页面
	 */
	@RequestMapping("manyToManyUser/gotoList")
	@ApiIgnore
	public String gotoList(ManyToManyUser manyToManyUser,HttpServletRequest request,HttpServletResponse response){
		return "ManyToMany/test_many_to_many_user_list";
	}


	/**
	 * 跳转到详情页面
	 */
	@RequestMapping("manyToManyUser/gotoDetail")
	@Auth("manyToManyUser/save")
	@ApiIgnore
	public String gotoDetail(ManyToManyUser manyToManyUser,HttpServletRequest request,HttpServletResponse response){
		List<ManyToManyRole> data = manyToManyRoleService.selectAll(null);
		request.setAttribute("goto_detail_test_many_to_many_role",data);

		if(manyToManyUser.getId()!=null){
			ManyToManyUser t = manyToManyUserService.selectManyToManyUserAndManyToManyRoleByCondition(manyToManyUser).get(0);
			request.setAttribute("test_many_to_many_user",t);
			List<ManyToManyRole> list = t.getManyToManyRoleList();
			List<String> sId=new ArrayList<>();
			if(list!=null){
				for (ManyToManyRole manyToManyRole : list) {
					sId.add(manyToManyRole.getId().toString());
				}
			}
			request.setAttribute("sId",sId);

		}else {
			request.setAttribute("test_many_to_many_user",manyToManyUser);
		}
		return "ManyToMany/test_many_to_many_user_detail";

	}


	/**
	 * 删除-多对多用户
	 */
	@ResponseBody
	@Auth("manyToManyUser/deleteByPrimaryKey")
	@RequestMapping("manyToManyUser/deleteByPrimaryKey")
	@ApiOperation(value="根据主键删除", notes="参数只用到了主键id,其他参数忽略" ,httpMethod="POST")
	public DataRes deleteByPrimaryKey(ManyToManyUser manyToManyUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyUserService.deleteByPrimaryKey(manyToManyUser));
	}


	/**
	 *  保存 (主键为空则增加否则修改)-> 多对多用户
	 */
	@ResponseBody
	@RequestMapping("manyToManyUser/save")
	@ApiOperation(value="保存", notes="主键为空则增加否则修改" ,httpMethod="POST")
	public DataRes save(ManyToManyUser manyToManyUser,HttpServletRequest request,HttpServletResponse response){
		if(manyToManyUser.getId()==null){
			return DataRes.success(manyToManyUserService.insert(manyToManyUser));
		}
		return DataRes.success(manyToManyUserService.update(manyToManyUser));

	}


	/**
	 * 根据主键查询->多对多用户
	 */
	@ResponseBody
	@Auth("manyToManyUser/selectAllByPaging")
	@RequestMapping("manyToManyUser/selectByPrimaryKey")
	@ApiOperation(value="根据主键查询", notes="参数只用到了主键." ,httpMethod="POST")
	public DataRes selectByPrimaryKey(ManyToManyUser manyToManyUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyUserService.selectByPrimaryKey(manyToManyUser));
	}


	/**
	 * 根据条件查询(所有的实体属性都是条件,如果为空则忽略该字段)->多对多用户
	 */
	@ResponseBody
	@Auth("manyToManyUser/selectAllByPaging")
	@RequestMapping("manyToManyUser/selectByCondition")
	@ApiOperation(value="根据条件查询", notes="参数为空则忽略." ,httpMethod="POST")
	public DataRes selectByCondition(ManyToManyUser manyToManyUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyUserService.selectByCondition(manyToManyUser));
	}


	/**
	 * 分页查询 (所有的实体属性都是条件,如果为空则忽略该字段) (详见Page类.所以的实体都继承该类 默认 page=1 pageSize=10)->多对多用户
	 */
	@ResponseBody
	@Auth("manyToManyUser/selectAllByPaging")
	@RequestMapping("manyToManyUser/selectAllByPaging")
	@ApiOperation(value="分页查询", notes="默认page=1pageSize等于10详见Page类(所有bean都继承该类).参数为空则忽略" ,httpMethod="POST")
	public DataRes selectAllByPaging(ManyToManyUser manyToManyUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyUserService.selectAllByPaging(manyToManyUser));
	}


	/**
	 * 导出报表->多对多用户
	 */
	@RequestMapping("manyToManyUser/export")
	@ApiOperation(value="导出excel", notes="导出全部数据.参数为空则忽略." ,httpMethod="POST")
	public void export(ManyToManyUser manyToManyUser,HttpServletRequest request,HttpServletResponse response){
		List<ManyToManyUser> list= manyToManyUserService.selectAll(manyToManyUser);
		Map<String, String> header = new LinkedHashMap<>();
		header.put("id", "id");
		header.put("name", "名称");
		header.put("age", "年龄");
		header.put("status_", "{\"name\":\"状态\",\"1\":\"启用\",\"0\":\"禁用\"}");
		header.put("birthday_", "生日");
		header.put("remarks", "备注");
		header.put("mun", "数字");
		header.put("createTime_", "创建时间");
		header.put("updateTime_", "更新时间");
		ExcelUtils.exportExcel("多对多用户",header,list,response,request);

	}


	/**
	 * 级联查询(带分页) 多对多用户--多对多角色
	 */
	@Auth("manyToManyUser/selectAllByPaging")
	@RequestMapping("manyToManyUser/selectManyToManyUserAndManyToManyRole")
	@ResponseBody
	@ApiOperation(value="主表级联查询(带分页)", notes="主表级联查询(带分页)  默认 page=1 pageSize等于10 详见 Page类(所有bean都继承该类)" ,httpMethod="POST")
	public DataRes selectManyToManyUserAndManyToManyRole(ManyToManyUser manyToManyUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyUserService.selectManyToManyUserAndManyToManyRole(manyToManyUser));
	}


	/**
	 * 级联条件查询 多对多用户--多对多角色
	 */
	@Auth("manyToManyUser/selectAllByPaging")
	@RequestMapping("manyToManyUser/selectManyToManyUserAndManyToManyRoleByCondition")
	@ResponseBody
	@ApiOperation(value="主表级联条件查询", notes="主表级联条件查询" ,httpMethod="POST")
	public DataRes selectManyToManyUserAndManyToManyRoleByCondition(ManyToManyUser manyToManyUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyUserService.selectManyToManyUserAndManyToManyRoleByCondition(manyToManyUser));
	}


	/**
	 * 级联删除(根据主键删除) 多对多用户--多对多角色
	 */
	@Auth("manyToManyUser/deleteByPrimaryKey")
	@RequestMapping("manyToManyUser/deleteManyToManyUserAndManyToManyRole")
	@ResponseBody
	@ApiOperation(value="主表级联删除(根据主键删除)", notes="主表级联删除(根据主键删除)" ,httpMethod="POST")
	public DataRes deleteManyToManyUserAndManyToManyRole(ManyToManyUser manyToManyUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyUserService.deleteManyToManyUserAndManyToManyRole(manyToManyUser));
	}


	/**
	 * 根据外表id查询主表表所有数据(带分页)
	 */
	@Auth("manyToManyUser/selectAllByPaging")
	@RequestMapping("manyToManyUser/selectManyToManyUserByManyToManyRole")
	@ResponseBody
	@ApiOperation(value="根据外表id查询主表表所有数据(带分页)", notes="根据外表id查询主表表所有数据(带分页)" ,httpMethod="POST")
	public DataRes selectManyToManyUserByManyToManyRole(HttpServletRequest request,HttpServletResponse response,ManyToManyUser manyToManyUser){
		return DataRes.success(manyToManyUserService.selectManyToManyUserByManyToManyRole(manyToManyUser));
	}



}
