package com.zengtengpeng.test.controller;
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
import com.zengtengpeng.test.bean.TestCode;
import com.zengtengpeng.test.service.TestCodeService;


/**
 *测试生成代码 controller
 */
@Controller
public class TestCodeController  {


	@Resource
	private TestCodeService testCodeService ;

	/**
	 * 跳转到列表页面
	 */
	@RequestMapping("testCode/gotoList")
	public String gotoList(TestCode testCode,HttpServletRequest request,HttpServletResponse response){
				return "test/test_code_list";
	}


	/**
	 * 跳转到详情页面
	 */
	@RequestMapping("testCode/gotoDetail")
	@Auth("testCode/save")
	public String gotoDetail(TestCode testCode,HttpServletRequest request,HttpServletResponse response){
		if(testCode.getId()!=null){
			 request.setAttribute("test_code",testCodeService.selectByPrimaryKey(testCode));
		}else {
			request.setAttribute("test_code",testCode);
		}
		return "test/test_code_detail";

	}


	/**
	 * 删除-测试生成代码
	 */
	@ResponseBody
	@RequestMapping("testCode/deleteByPrimaryKey")
	public DataRes deleteByPrimaryKey(TestCode testCode,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(testCodeService.deleteByPrimaryKey(testCode));
	}


	/**
	 *  保存 (主键为空则增加 否则 修改)-> 测试生成代码
	 */
	@ResponseBody
	@RequestMapping("testCode/save")
	public DataRes save(TestCode testCode,HttpServletRequest request,HttpServletResponse response){
		if(testCode.getId()==null){
			return DataRes.success(testCodeService.insert(testCode));
		}
		return DataRes.success(testCodeService.update(testCode));

	}


	/**
	 * 根据主键查询->测试生成代码
	 */
	@ResponseBody
	@RequestMapping("testCode/selectByPrimaryKey")
	public DataRes selectByPrimaryKey(TestCode testCode,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(testCodeService.selectByPrimaryKey(testCode));
	}


	/**
	 * 根据条件查询(所有的实体属性都是条件,如果为空则忽略该字段)->测试生成代码
	 */
	@ResponseBody
	@RequestMapping("testCode/selectByCondition")
	public DataRes selectByCondition(TestCode testCode,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(testCodeService.selectByCondition(testCode));
	}


	/**
	 * 分页查询 (所有的实体属性都是条件,如果为空则忽略该字段) (详见Page类.所以的实体都继承该类 默认 page=1 pageSize=10)->测试生成代码
	 */
	@ResponseBody
	@RequestMapping("testCode/selectAllByPaging")
	public DataRes selectAllByPaging(TestCode testCode,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(testCodeService.selectAllByPaging(testCode));
	}


	/**
	 * 导出报表->测试生成代码
	 */
	@RequestMapping("testCode/export")
	public void export(TestCode testCode,HttpServletRequest request,HttpServletResponse response){
		List<TestCode> list= testCodeService.selectAll(testCode);
		Map<String, String> header = new LinkedHashMap<>();
		header.put("id", "测试生成代码");
		header.put("name", "名称");
		header.put("age", "年龄");
		header.put("status_", "{\"name\":\"状态\",\"1\":\"启用\",\"0\":\"禁用\"}");
		header.put("birthday_", "生日");
		header.put("remarks", "备注");
		header.put("createTime_", "创建时间");
		ExcelUtils.exportExcel("测试生成代码",header,list,response,request);

	}


}
