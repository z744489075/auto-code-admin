package com.zengtengpeng.oneToOne.controller;

import com.zengtengpeng.oneToOne.service.OneToOneClassService;
import com.zengtengpeng.oneToOne.bean.OneToOneClass;
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
import com.zengtengpeng.oneToOne.bean.OneToOneUser;
import com.zengtengpeng.oneToOne.service.OneToOneUserService;


/**
 *一对一用户 controller
 */
@Controller
public class OneToOneUserController  {

	/**
	 * 一对一班级
	 */
	@Resource
	private OneToOneClassService oneToOneClassService;



	@Resource
	private OneToOneUserService oneToOneUserService;
	/**
	 * 跳转到列表页面
	 */
	@RequestMapping("oneToOneUser/gotoList")
	public String gotoList(OneToOneUser oneToOneUser,HttpServletRequest request,HttpServletResponse response){
		return "oneToOne/test_one_to_one_user_list";
	}


	/**
	 * 跳转到详情页面
	 */
	@RequestMapping("oneToOneUser/gotoDetail")
	@Auth("oneToOneUser/save")
	public String gotoDetail(OneToOneUser oneToOneUser,HttpServletRequest request,HttpServletResponse response){
		if(oneToOneUser.getId()!=null){
			 request.setAttribute("test_one_to_one_user",oneToOneUserService.selectByPrimaryKey(oneToOneUser));
		}else {
			request.setAttribute("test_one_to_one_user",oneToOneUser);
		}
		return "oneToOne/test_one_to_one_user_detail";

	}


	/**
	 * 删除-一对一用户
	 */
	@ResponseBody
	@Auth("oneToOneUser/deleteByPrimaryKey")
	@RequestMapping("oneToOneUser/deleteByPrimaryKey")
	public DataRes deleteByPrimaryKey(OneToOneUser oneToOneUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToOneUserService.deleteByPrimaryKey(oneToOneUser));
	}


	/**
	 *  保存 (主键为空则增加 否则 修改)-> 一对一用户
	 */
	@ResponseBody
	@RequestMapping("oneToOneUser/save")
	public DataRes save(OneToOneUser oneToOneUser,HttpServletRequest request,HttpServletResponse response){
		if(oneToOneUser.getId()==null){
			return DataRes.success(oneToOneUserService.insert(oneToOneUser));
		}
		return DataRes.success(oneToOneUserService.update(oneToOneUser));

	}


	/**
	 * 根据主键查询->一对一用户
	 */
	@ResponseBody
	@Auth("oneToOneUser/selectAllByPaging")
	@RequestMapping("oneToOneUser/selectByPrimaryKey")
	public DataRes selectByPrimaryKey(OneToOneUser oneToOneUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToOneUserService.selectByPrimaryKey(oneToOneUser));
	}


	/**
	 * 根据条件查询(所有的实体属性都是条件,如果为空则忽略该字段)->一对一用户
	 */
	@ResponseBody
	@Auth("oneToOneUser/selectAllByPaging")
	@RequestMapping("oneToOneUser/selectByCondition")
	public DataRes selectByCondition(OneToOneUser oneToOneUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToOneUserService.selectByCondition(oneToOneUser));
	}


	/**
	 * 分页查询 (所有的实体属性都是条件,如果为空则忽略该字���) (详见Page类.所以的实体都继承该类 默认 page=1 pageSize=10)->一对一用户
	 */
	@ResponseBody
	@Auth("oneToOneUser/selectAllByPaging")
	@RequestMapping("oneToOneUser/selectAllByPaging")
	public DataRes selectAllByPaging(OneToOneUser oneToOneUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToOneUserService.selectAllByPaging(oneToOneUser));
	}


	/**
	 * 导出报表->一对一用户
	 */
	@RequestMapping("oneToOneUser/export")
	public void export(OneToOneUser oneToOneUser,HttpServletRequest request,HttpServletResponse response){
		List<OneToOneUser> list= oneToOneUserService.selectAll(oneToOneUser);
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
		ExcelUtils.exportExcel("一对一用户",header,list,response,request);

	}


	/**
	 * 级联查询(带分页) 一对一用户--一对一班级
	 */
	@Auth("oneToOneUser/selectAllByPaging")
	@RequestMapping("oneToOneUser/selectOneToOneUserAndOneToOneClass")
	@ResponseBody
	public DataRes selectOneToOneUserAndOneToOneClass(OneToOneUser oneToOneUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToOneUserService.selectOneToOneUserAndOneToOneClass(oneToOneUser));
	}


	/**
	 * 级联条件查询 一对一用户--一对一班级
	 */
	@Auth("oneToOneUser/selectAllByPaging")
	@RequestMapping("oneToOneUser/selectOneToOneUserAndOneToOneClassByCondition")
	@ResponseBody
	public DataRes selectOneToOneUserAndOneToOneClassByCondition(OneToOneUser oneToOneUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToOneUserService.selectOneToOneUserAndOneToOneClassByCondition(oneToOneUser));
	}


	/**
	 * 级联删除(根据主键删除) 一对一用户--一对一班级
	 */
	@Auth("oneToOneUser/deleteByPrimaryKey")
	@RequestMapping("oneToOneUser/deleteOneToOneUserAndOneToOneClass")
	@ResponseBody
	public DataRes deleteOneToOneUserAndOneToOneClass(OneToOneUser oneToOneUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(oneToOneUserService.deleteOneToOneUserAndOneToOneClass(oneToOneUser));
	}



}
