package com.zengtengpeng.test.controller;

import com.zengtengpeng.test.service.UserService;
import com.zengtengpeng.test.bean.User;
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
import com.zengtengpeng.test.bean.TestRole;
import com.zengtengpeng.test.service.TestRoleService;


/**
 *测试角色 controller
 */
@Controller
public class TestRoleController  {

	/**
	 * 用户
	 */
	@Resource
	private UserService userService;



	@Resource
	private TestRoleService testRoleService;
	/**
	 * 跳转到列表页面
	 */
	@RequestMapping("testRole/gotoList")
	public String gotoList(TestRole testRole,HttpServletRequest request,HttpServletResponse response){
		return "test/test_role_list";
	}


	/**
	 * 跳转到详情页面
	 */
	@RequestMapping("testRole/gotoDetail")
	@Auth("testRole/save")
	public String gotoDetail(TestRole testRole,HttpServletRequest request,HttpServletResponse response){
		List<User> data = userService.selectAll(null);
		request.setAttribute("goto_detail_test_user",data);

		if(testRole.getId()!=null){
			 request.setAttribute("test_role",testRoleService.selectByPrimaryKey(testRole));
		}else {
			request.setAttribute("test_role",testRole);
		}
		return "test/test_role_detail";

	}


	/**
	 * 删除-测试角色
	 */
	@ResponseBody
	@RequestMapping("testRole/deleteByPrimaryKey")
	public DataRes deleteByPrimaryKey(TestRole testRole,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(testRoleService.deleteByPrimaryKey(testRole));
	}


	/**
	 *  保存 (主键为空则增加 否则 修改)-> 测试角色
	 */
	@ResponseBody
	@RequestMapping("testRole/save")
	public DataRes save(TestRole testRole,HttpServletRequest request,HttpServletResponse response){
		if(testRole.getId()==null){
			return DataRes.success(testRoleService.insert(testRole));
		}
		return DataRes.success(testRoleService.update(testRole));

	}


	/**
	 * 根据主键查询->测试角色
	 */
	@ResponseBody
	@RequestMapping("testRole/selectByPrimaryKey")
	public DataRes selectByPrimaryKey(TestRole testRole,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(testRoleService.selectByPrimaryKey(testRole));
	}


	/**
	 * 根据条件查询(所有的实体属性都是条件,如果为空则忽略该字段)->测试角色
	 */
	@ResponseBody
	@RequestMapping("testRole/selectByCondition")
	public DataRes selectByCondition(TestRole testRole,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(testRoleService.selectByCondition(testRole));
	}


	/**
	 * 分页查询 (所有的实体属性都是条件,如果为空则忽略该字段) (详见Page类.所以的实体都继承该类 默认 page=1 pageSize=10)->测试角色
	 */
	@ResponseBody
	@RequestMapping("testRole/selectAllByPaging")
	public DataRes selectAllByPaging(TestRole testRole,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(testRoleService.selectAllByPaging(testRole));
	}


	/**
	 * 导出报表->测试角色
	 */
	@RequestMapping("testRole/export")
	public void export(TestRole testRole,HttpServletRequest request,HttpServletResponse response){
		List<TestRole> list= testRoleService.selectAll(testRole);
		Map<String, String> header = new LinkedHashMap<>();
		header.put("id", "测试角色");
		header.put("name", "角色名称");
		header.put("status_", "{\"name\":\"状态\",\"0\":\"启用\",\"1\":\"禁用\"}");
		header.put("createUserId", "创建者");
		header.put("createTime_", "创建时间");
		header.put("updateUserId", "更新者");
		header.put("updateTime_", "更新时间");
		header.put("dels_", "{\"name\":\"是否删除\",\"0\":\"正常\",\"1\":\"删除\"}");
		ExcelUtils.exportExcel("测试角色",header,list,response,request);

	}


	/**
	 * 级联查询(带分页) 用户--角色
	 */
	@ResponseBody
	@RequestMapping("testRole/selectUserAndTestRole")
	public DataRes selectUserAndTestRole(TestRole testRole,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(testRoleService.selectUserAndTestRole(testRole));
	}


	/**
	 * 级联条件查询 用户--角色
	 */
	@ResponseBody
	@RequestMapping("testRole/selectUserAndTestRoleByCondition")
	public DataRes selectUserAndTestRoleByCondition(TestRole testRole,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(testRoleService.selectUserAndTestRoleByCondition(testRole));
	}


	/**
	 * 级联删除(根据主键删除) 用户--角色
	 */
	@ResponseBody
	@RequestMapping("testRole/deleteUserAndTestRole")
	public DataRes deleteUserAndTestRole(TestRole testRole,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(testRoleService.deleteUserAndTestRole(testRole));
	}


	/**
	 * 根据主表id查询外表所有数据(带分页)
	 */
	@ResponseBody
	@RequestMapping("testRole/selectTestRoleByUser")
	public DataRes selectTestRoleByUser(HttpServletRequest request,HttpServletResponse response,TestRole testRole){
		return DataRes.success(testRoleService.selectTestRoleByUser(testRole));
	}



}
