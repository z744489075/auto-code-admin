package com.zengtengpeng.oneToMany.controller;

import com.zengtengpeng.oneToMany.service.OneToManyUserService;
import com.zengtengpeng.oneToMany.bean.OneToManyUser;
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
import com.zengtengpeng.oneToMany.bean.OneToManyAddr;
import com.zengtengpeng.oneToMany.service.OneToManyAddrService;


/**
 *用户收货地址 controller
 */
@Controller
public class OneToManyAddrController  {

	/**
	 * 用户
	 */
	@Resource
	private OneToManyUserService oneToManyUserService;



	@Resource
	private OneToManyAddrService oneToManyAddrService;
	/**
	 * 跳转到列表页面
	 */
	@RequestMapping("oneToManyAddr/gotoList")
	public String gotoList(OneToManyAddr oneToManyAddr,HttpServletRequest request,HttpServletResponse response){
		return "oneToMany/test_one_to_many_addr_list";
	}


	/**
	 * 跳转到详情页面
	 */
	@RequestMapping("oneToManyAddr/gotoDetail")
	@Auth("oneToManyAddr/save")
	public String gotoDetail(OneToManyAddr oneToManyAddr,HttpServletRequest request,HttpServletResponse response){
		List<OneToManyUser> data = oneToManyUserService.selectAll(null);
		request.setAttribute("goto_detail_test_one_to_many_user",data);

		if(oneToManyAddr.getId()!=null){
			 request.setAttribute("test_one_to_many_addr",oneToManyAddrService.selectByPrimaryKey(oneToManyAddr));
		}else {
			request.setAttribute("test_one_to_many_addr",oneToManyAddr);
		}
		return "oneToMany/test_one_to_many_addr_detail";

	}


	/**
	 * 删除-用户收货地址
	 */
	@ResponseBody
	@Auth("oneToManyAddr/deleteByPrimaryKey")
	@RequestMapping("oneToManyAddr/deleteByPrimaryKey")
	public DataRes deleteByPrimaryKey(OneToManyAddr oneToManyAddr,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToManyAddrService.deleteByPrimaryKey(oneToManyAddr));
	}


	/**
	 *  保存 (主键为空则增加 否则 修改)-> 用户收货地址
	 */
	@ResponseBody
	@RequestMapping("oneToManyAddr/save")
	public DataRes save(OneToManyAddr oneToManyAddr,HttpServletRequest request,HttpServletResponse response){
		if(oneToManyAddr.getId()==null){
			return DataRes.success(oneToManyAddrService.insert(oneToManyAddr));
		}
		return DataRes.success(oneToManyAddrService.update(oneToManyAddr));

	}


	/**
	 * 根据主键查询->用户收货地址
	 */
	@ResponseBody
	@Auth("oneToManyAddr/selectAllByPaging")
	@RequestMapping("oneToManyAddr/selectByPrimaryKey")
	public DataRes selectByPrimaryKey(OneToManyAddr oneToManyAddr,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToManyAddrService.selectByPrimaryKey(oneToManyAddr));
	}


	/**
	 * 根据条件查询(所有的实体属性都是条件,如果为空则忽略该字段)->用户收货地址
	 */
	@ResponseBody
	@Auth("oneToManyAddr/selectAllByPaging")
	@RequestMapping("oneToManyAddr/selectByCondition")
	public DataRes selectByCondition(OneToManyAddr oneToManyAddr,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToManyAddrService.selectByCondition(oneToManyAddr));
	}


	/**
	 * 分页查询 (所有的实体属性都是条件,如果为空则忽略该字段) (详见Page类.所以的实体都继承该类 默认 page=1 pageSize=10)->用户收货地址
	 */
	@ResponseBody
	@Auth("oneToManyAddr/selectAllByPaging")
	@RequestMapping("oneToManyAddr/selectAllByPaging")
	public DataRes selectAllByPaging(OneToManyAddr oneToManyAddr,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToManyAddrService.selectAllByPaging(oneToManyAddr));
	}


	/**
	 * 导出报表->用户收货地址
	 */
	@RequestMapping("oneToManyAddr/export")
	public void export(OneToManyAddr oneToManyAddr,HttpServletRequest request,HttpServletResponse response){
		List<OneToManyAddr> list= oneToManyAddrService.selectAll(oneToManyAddr);
		Map<String, String> header = new LinkedHashMap<>();
		header.put("id", "用户收货地址id");
		header.put("userId", "用户id");
		header.put("addrName", "姓名");
		header.put("phone", "手机号码");
		header.put("addr", "收货地址");
		header.put("status_", "{\"name\":\"状态\",\"1\":\"启用\",\"2\":\"删除\"}");
		header.put("createTime_", "创建时间");
		header.put("updateTime_", "更新时间");
		ExcelUtils.exportExcel("用户收货地址",header,list,response,request);

	}


	/**
	 * 级联查询(带分页) 用户--收货地址
	 */
	@Auth("oneToManyAddr/selectAllByPaging")
	@RequestMapping("oneToManyAddr/selectOneToManyUserAndOneToManyAddr")
	@ResponseBody
	public DataRes selectOneToManyUserAndOneToManyAddr(OneToManyAddr oneToManyAddr,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToManyAddrService.selectOneToManyUserAndOneToManyAddr(oneToManyAddr));
	}


	/**
	 * 级联条件查询 用户--收货地址
	 */
	@Auth("oneToManyAddr/selectAllByPaging")
	@RequestMapping("oneToManyAddr/selectOneToManyUserAndOneToManyAddrByCondition")
	@ResponseBody
	public DataRes selectOneToManyUserAndOneToManyAddrByCondition(OneToManyAddr oneToManyAddr,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToManyAddrService.selectOneToManyUserAndOneToManyAddrByCondition(oneToManyAddr));
	}


	/**
	 * 级联删除(根据主键删除) 用户--收货地址
	 */
	@Auth("oneToManyAddr/deleteByPrimaryKey")
	@RequestMapping("oneToManyAddr/deleteOneToManyUserAndOneToManyAddr")
	@ResponseBody
	public DataRes deleteOneToManyUserAndOneToManyAddr(OneToManyAddr oneToManyAddr,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToManyAddrService.deleteOneToManyUserAndOneToManyAddr(oneToManyAddr));
	}



}
