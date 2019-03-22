package com.zengtengpeng.simple.controller;
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
import com.zengtengpeng.simple.bean.SimpleCode;
import com.zengtengpeng.simple.service.SimpleCodeService;


/**
 *单表代码生成 controller
 */
@Api(description="单表代码生成")
@Controller
public class SimpleCodeController  {


	@Resource
	private SimpleCodeService simpleCodeService;
	/**
	 * 跳转到列表页面
	 */
	@RequestMapping("simpleCode/gotoList")
	@ApiIgnore
	public String gotoList(SimpleCode simpleCode,HttpServletRequest request,HttpServletResponse response){
		return "simple/test_simple_code_list";
	}


	/**
	 * 跳转到详情页面
	 */
	@RequestMapping("simpleCode/gotoDetail")
	@Auth("simpleCode/save")
	@ApiIgnore
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
	@ApiOperation(value="根据主键删除", notes="参数只用到了主键id,其他参数忽略" ,httpMethod="POST")
	public DataRes deleteByPrimaryKey(SimpleCode simpleCode,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(simpleCodeService.deleteByPrimaryKey(simpleCode));
	}


	/**
	 *  保存 (主键为空则增加否则修改)-> 单表代码生成
	 */
	@ResponseBody
	@RequestMapping("simpleCode/save")
	@ApiOperation(value="保存", notes="主键为空则增加否则修改" ,httpMethod="POST")
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
	@ApiOperation(value="根据主键查询", notes="参数只用到了主键." ,httpMethod="POST")
	public DataRes selectByPrimaryKey(SimpleCode simpleCode,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(simpleCodeService.selectByPrimaryKey(simpleCode));
	}


	/**
	 * 根据条件查询(所有的实体属性都是条件,如果为空则忽略该字段)->单表代码生成
	 */
	@ResponseBody
	@RequestMapping("simpleCode/selectByCondition")
	@ApiOperation(value="根据条件查询", notes="参数为空则忽略." ,httpMethod="POST")
	public DataRes selectByCondition(SimpleCode simpleCode,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(simpleCodeService.selectByCondition(simpleCode));
	}


	/**
	 * 分页查询 (所有的实体属性都是条件,如果为空则忽略该字段) (详见Page类.所以的实体都继承该类 默认 page=1 pageSize=10)->单表代码生成
	 */
	@ResponseBody
	@RequestMapping("simpleCode/selectAllByPaging")
	@ApiOperation(value="分页查询", notes="默认page=1pageSize等于10详见Page类(所有bean都继承该类).参数为空则忽略" ,httpMethod="POST")
	public DataRes selectAllByPaging(SimpleCode simpleCode,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(simpleCodeService.selectAllByPaging(simpleCode));
	}


	/**
	 * 导出报表->单表代码生成
	 */
	@RequestMapping("simpleCode/export")
	@ApiOperation(value="导出excel", notes="导出全部数据.参数为空则忽略." ,httpMethod="POST")
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
