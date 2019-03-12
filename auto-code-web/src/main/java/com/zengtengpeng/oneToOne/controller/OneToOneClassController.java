package com.zengtengpeng.oneToOne.controller;

import com.zengtengpeng.oneToOne.service.OneToOneUserService;
import com.zengtengpeng.oneToOne.bean.OneToOneUser;
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
import com.zengtengpeng.oneToOne.bean.OneToOneClass;
import com.zengtengpeng.oneToOne.service.OneToOneClassService;


/**
 *一对一班级 controller
 */
@Controller
public class OneToOneClassController  {

	/**
	 * 一对一用户
	 */
	@Resource
	private OneToOneUserService oneToOneUserService;



	@Resource
	private OneToOneClassService oneToOneClassService;
	/**
	 * 跳转到列表页面
	 */
	@RequestMapping("oneToOneClass/gotoList")
	public String gotoList(OneToOneClass oneToOneClass,HttpServletRequest request,HttpServletResponse response){
		return "oneToOne/test_one_to_one_class_list";
	}


	/**
	 * 跳转到详情页面
	 */
	@RequestMapping("oneToOneClass/gotoDetail")
	@Auth("oneToOneClass/save")
	public String gotoDetail(OneToOneClass oneToOneClass,HttpServletRequest request,HttpServletResponse response){
		List<OneToOneUser> data = oneToOneUserService.selectAll(null);
		request.setAttribute("goto_detail_test_one_to_one_user",data);

		if(oneToOneClass.getId()!=null){
			 request.setAttribute("test_one_to_one_class",oneToOneClassService.selectByPrimaryKey(oneToOneClass));
		}else {
			request.setAttribute("test_one_to_one_class",oneToOneClass);
		}
		return "oneToOne/test_one_to_one_class_detail";

	}


	/**
	 * 删除-一对一班级
	 */
	@ResponseBody
	@Auth("oneToOneClass/deleteByPrimaryKey")
	@RequestMapping("oneToOneClass/deleteByPrimaryKey")
	public DataRes deleteByPrimaryKey(OneToOneClass oneToOneClass,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToOneClassService.deleteByPrimaryKey(oneToOneClass));
	}


	/**
	 *  保存 (主键为空则增加 否则 修改)-> 一对一班级
	 */
	@ResponseBody
	@RequestMapping("oneToOneClass/save")
	public DataRes save(OneToOneClass oneToOneClass,HttpServletRequest request,HttpServletResponse response){
		if(oneToOneClass.getId()==null){
			return DataRes.success(oneToOneClassService.insert(oneToOneClass));
		}
		return DataRes.success(oneToOneClassService.update(oneToOneClass));

	}


	/**
	 * 根据主键查询->一对一班级
	 */
	@ResponseBody
	@Auth("oneToOneClass/selectAllByPaging")
	@RequestMapping("oneToOneClass/selectByPrimaryKey")
	public DataRes selectByPrimaryKey(OneToOneClass oneToOneClass,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToOneClassService.selectByPrimaryKey(oneToOneClass));
	}


	/**
	 * 根据条件查询(所有的实体属性都是条件,如果为空则忽略该字段)->一对一班级
	 */
	@ResponseBody
	@Auth("oneToOneClass/selectAllByPaging")
	@RequestMapping("oneToOneClass/selectByCondition")
	public DataRes selectByCondition(OneToOneClass oneToOneClass,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToOneClassService.selectByCondition(oneToOneClass));
	}


	/**
	 * 分页查询 (所有的实体属���都是条件,如果为空则忽略该字段) (详见Page类.所以的实体都继承该类 默认 page=1 pageSize=10)->一对一班级
	 */
	@ResponseBody
	@Auth("oneToOneClass/selectAllByPaging")
	@RequestMapping("oneToOneClass/selectAllByPaging")
	public DataRes selectAllByPaging(OneToOneClass oneToOneClass,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToOneClassService.selectAllByPaging(oneToOneClass));
	}


	/**
	 * 导出报表->一对一班级
	 */
	@RequestMapping("oneToOneClass/export")
	public void export(OneToOneClass oneToOneClass,HttpServletRequest request,HttpServletResponse response){
		List<OneToOneClass> list= oneToOneClassService.selectAll(oneToOneClass);
		Map<String, String> header = new LinkedHashMap<>();
		header.put("id", "班级id");
		header.put("userId", "用户id");
		header.put("className", "班级名称");
		header.put("quantity", "班级人数");
		header.put("createTime_", "create_time");
		ExcelUtils.exportExcel("一对一班级",header,list,response,request);

	}


	/**
	 * 级联查询(带分页) 一对一用户--一对一班级
	 */
	@Auth("oneToOneClass/selectAllByPaging")
	@RequestMapping("oneToOneClass/selectOneToOneUserAndOneToOneClass")
	@ResponseBody
	public DataRes selectOneToOneUserAndOneToOneClass(OneToOneClass oneToOneClass,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToOneClassService.selectOneToOneUserAndOneToOneClass(oneToOneClass));
	}


	/**
	 * 级联条件查询 一对一用户--一对一班级
	 */
	@Auth("oneToOneClass/selectAllByPaging")
	@RequestMapping("oneToOneClass/selectOneToOneUserAndOneToOneClassByCondition")
	@ResponseBody
	public DataRes selectOneToOneUserAndOneToOneClassByCondition(OneToOneClass oneToOneClass,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToOneClassService.selectOneToOneUserAndOneToOneClassByCondition(oneToOneClass));
	}


	/**
	 * 级联删除(根据主键删除) 一对一用户--一对一班级
	 */
	@Auth("oneToOneClass/deleteByPrimaryKey")
	@RequestMapping("oneToOneClass/deleteOneToOneUserAndOneToOneClass")
	@ResponseBody
	public DataRes deleteOneToOneUserAndOneToOneClass(OneToOneClass oneToOneClass,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToOneClassService.deleteOneToOneUserAndOneToOneClass(oneToOneClass));
	}



}
