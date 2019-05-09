package com.zengtengpeng.simple.controller;
import com.zengtengpeng.common.annotation.Auth;

import javax.annotation.Resource;
import com.zengtengpeng.common.utils.ExcelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zengtengpeng.common.bean.DataRes;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;


import com.zengtengpeng.simple.bean.SimpleCode;
import com.zengtengpeng.simple.service.SimpleCodeService;


/**
 *单表代码生成 controller
 */
@Controller
public class SimpleCodeController  {


	Logger logger = LoggerFactory.getLogger(SimpleCodeController.class);
	@Resource
	private SimpleCodeService simpleCodeService;
	/**
	 * 跳转到列表页面
	 */
	@RequestMapping("simpleCode/gotoList")
	public String gotoList(SimpleCode simpleCode,HttpServletRequest request,HttpServletResponse response){
		logger.info("测试中");
		return "simple/test_simple_code_list";
	}


	/**
	 * 跳转到详情页面
	 */
	@RequestMapping("simpleCode/gotoDetail")
	@Auth("simpleCode/save")
	public String gotoDetail(SimpleCode simpleCode,HttpServletRequest request,HttpServletResponse response){
		if(simpleCode.getId()!=null){
			 request.setAttribute("test_simple_code",simpleCodeService.selectByPrimaryKey(simpleCode));
		}else {
			request.setAttribute("test_simple_code",simpleCode);
		}
		return "simple/test_simple_code_detail";

	}


	/**
	 * 删除-单表代码生成
	 */
	@ResponseBody
	@RequestMapping("simpleCode/deleteByPrimaryKey")
	public DataRes deleteByPrimaryKey(SimpleCode simpleCode,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(simpleCodeService.deleteByPrimaryKey(simpleCode));
	}


	/**
	 *  保存 (主键为空则增加否则修改)-> 单表代码生成
	 */
	@ResponseBody
	@RequestMapping("simpleCode/save")
	public DataRes save(SimpleCode simpleCode,HttpServletRequest request,HttpServletResponse response){
		if(simpleCode.getId()==null){
			return DataRes.success(simpleCodeService.insert(simpleCode));
		}
		return DataRes.success(simpleCodeService.update(simpleCode));

	}


	/**
	 * 根据主键查询->单表代码生成
	 */
	@ResponseBody
	@RequestMapping("simpleCode/selectByPrimaryKey")
	public DataRes selectByPrimaryKey(SimpleCode simpleCode,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(simpleCodeService.selectByPrimaryKey(simpleCode));
	}


	/**
	 * 根据条件查询(所有的实体属性都是条件,如果为空则忽略该字段)->单表代码生成
	 */
	@ResponseBody
	@RequestMapping("simpleCode/selectByCondition")
	public DataRes selectByCondition(SimpleCode simpleCode,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(simpleCodeService.selectByCondition(simpleCode));
	}


	/**
	 * 分页查询 (所有的实体属性都是条件,如果为空则忽略该字段) (详见Page类.所以的实体都继承该类 默认 page=1 pageSize=10)->单表代码生成
	 */
	@ResponseBody
	@RequestMapping("simpleCode/selectAllByPaging")
	public DataRes selectAllByPaging(SimpleCode simpleCode,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(simpleCodeService.selectAllByPaging(simpleCode));
	}


	/**
	 * 导出报表->单表代码生成
	 */
	@RequestMapping("simpleCode/export")
	public void export(SimpleCode simpleCode,HttpServletRequest request,HttpServletResponse response){
		List<SimpleCode> list= simpleCodeService.selectAll(simpleCode);
		Map<String, String> header = new LinkedHashMap<>();
		header.put("id", "主键");
		header.put("name", "名称");
		header.put("age", "年龄");
		header.put("status_", "{\"name\":\"状态\",\"1\":\"启用\",\"0\":\"禁用\"}");
		header.put("birthday_", "生日");
		header.put("remarks", "备注");
		header.put("createTime_", "创建时间");
		ExcelUtils.exportExcel("单表代码生成",header,list,response,request);

	}


}
