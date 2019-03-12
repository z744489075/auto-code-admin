package com.zengtengpeng.oneToMany.controller;

import com.zengtengpeng.oneToMany.service.OneToManyAddrService;
import com.zengtengpeng.oneToMany.bean.OneToManyAddr;
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
import com.zengtengpeng.oneToMany.bean.OneToManyUser;
import com.zengtengpeng.oneToMany.service.OneToManyUserService;


/**
 *用户 controller
 */
@Controller
public class OneToManyUserController  {

	/**
	 * 收货地址
	 */
	@Resource
	private OneToManyAddrService oneToManyAddrService;



	@Resource
	private OneToManyUserService oneToManyUserService;
	/**
	 * 跳转到列表页面
	 */
	@RequestMapping("oneToManyUser/gotoList")
	public String gotoList(OneToManyUser oneToManyUser,HttpServletRequest request,HttpServletResponse response){
		return "oneToMany/test_one_to_many_user_list";
	}


	/**
	 * 跳转到详情页面
	 */
	@RequestMapping("oneToManyUser/gotoDetail")
	@Auth("oneToManyUser/save")
	public String gotoDetail(OneToManyUser oneToManyUser,HttpServletRequest request,HttpServletResponse response){
		if(oneToManyUser.getId()!=null){
			 request.setAttribute("test_one_to_many_user",oneToManyUserService.selectByPrimaryKey(oneToManyUser));
		}else {
			request.setAttribute("test_one_to_many_user",oneToManyUser);
		}
		return "oneToMany/test_one_to_many_user_detail";

	}


	/**
	 * 删除-用户
	 */
	@ResponseBody
	@Auth("oneToManyUser/deleteByPrimaryKey")
	@RequestMapping("oneToManyUser/deleteByPrimaryKey")
	public DataRes deleteByPrimaryKey(OneToManyUser oneToManyUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToManyUserService.deleteByPrimaryKey(oneToManyUser));
	}


	/**
	 *  保存 (主键为空则增加 否则 修改)-> 用户
	 */
	@ResponseBody
	@RequestMapping("oneToManyUser/save")
	public DataRes save(OneToManyUser oneToManyUser,HttpServletRequest request,HttpServletResponse response){
		if(oneToManyUser.getId()==null){
			return DataRes.success(oneToManyUserService.insert(oneToManyUser));
		}
		return DataRes.success(oneToManyUserService.update(oneToManyUser));

	}


	/**
	 * 根据主键查询->用户
	 */
	@ResponseBody
	@Auth("oneToManyUser/selectAllByPaging")
	@RequestMapping("oneToManyUser/selectByPrimaryKey")
	public DataRes selectByPrimaryKey(OneToManyUser oneToManyUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToManyUserService.selectByPrimaryKey(oneToManyUser));
	}


	/**
	 * 根据条件查询(所有的实体属性都是条件,如果为空则忽略该字段)->用户
	 */
	@ResponseBody
	@Auth("oneToManyUser/selectAllByPaging")
	@RequestMapping("oneToManyUser/selectByCondition")
	public DataRes selectByCondition(OneToManyUser oneToManyUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToManyUserService.selectByCondition(oneToManyUser));
	}


	/**
	 * 分页查询 (所有的实体属性都是条件,如果为空则忽略该���段) (详见Page类.所以的实体都继承该类 默认 page=1 pageSize=10)->用户
	 */
	@ResponseBody
	@Auth("oneToManyUser/selectAllByPaging")
	@RequestMapping("oneToManyUser/selectAllByPaging")
	public DataRes selectAllByPaging(OneToManyUser oneToManyUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToManyUserService.selectAllByPaging(oneToManyUser));
	}


	/**
	 * 导出报表->用户
	 */
	@RequestMapping("oneToManyUser/export")
	public void export(OneToManyUser oneToManyUser,HttpServletRequest request,HttpServletResponse response){
		List<OneToManyUser> list= oneToManyUserService.selectAll(oneToManyUser);
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
		ExcelUtils.exportExcel("用户",header,list,response,request);

	}


	/**
	 * 级联查询(带分页) 用户--收货地址
	 */
	@Auth("oneToManyUser/selectAllByPaging")
	@RequestMapping("oneToManyUser/selectOneToManyUserAndOneToManyAddr")
	@ResponseBody
	public DataRes selectOneToManyUserAndOneToManyAddr(OneToManyUser oneToManyUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToManyUserService.selectOneToManyUserAndOneToManyAddr(oneToManyUser));
	}


	/**
	 * 级联条件查询 用户--收货地址
	 */
	@Auth("oneToManyUser/selectAllByPaging")
	@RequestMapping("oneToManyUser/selectOneToManyUserAndOneToManyAddrByCondition")
	@ResponseBody
	public DataRes selectOneToManyUserAndOneToManyAddrByCondition(OneToManyUser oneToManyUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToManyUserService.selectOneToManyUserAndOneToManyAddrByCondition(oneToManyUser));
	}


	/**
	 * 级联删除(根据主键删除) 用户--收货地址
	 */
	@Auth("oneToManyUser/deleteByPrimaryKey")
	@RequestMapping("oneToManyUser/deleteOneToManyUserAndOneToManyAddr")
	@ResponseBody
	public DataRes deleteOneToManyUserAndOneToManyAddr(OneToManyUser oneToManyUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToManyUserService.deleteOneToManyUserAndOneToManyAddr(oneToManyUser));
	}



}
