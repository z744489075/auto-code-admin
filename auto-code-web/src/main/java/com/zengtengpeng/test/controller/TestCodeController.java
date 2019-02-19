package com.zengtengpeng.test.controller;

import javax.annotation.Resource;

import com.zengtengpeng.common.utils.ExcelUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zengtengpeng.common.bean.DataRes;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import com.zengtengpeng.test.bean.TestCode;
import com.zengtengpeng.test.service.TestCodeService;
import org.springframework.stereotype.Controller;
import com.zengtengpeng.common.annotation.Auth;
/**
 * 
 * @author zengtp
 *
 */
@Controller
public class TestCodeController {
	
	@Resource
	private TestCodeService testCodeService;

	/**
	 * 删除
	 * @param testCode
	 * @return
	 */
	@RequestMapping("testCode/deleteByPrimaryKey")
	@ResponseBody
	public DataRes deleteByPrimaryKey(TestCode testCode, HttpServletRequest request, HttpServletResponse response){
		return DataRes.success(testCodeService.deleteByPrimaryKey(testCode));
	}

    /**
	 * 保存 如果id存在则修改否则新增
	 * @param testCode
	 * @return
	 */
	@RequestMapping("testCode/save")
	@ResponseBody
	public DataRes save(TestCode testCode, HttpServletRequest request, HttpServletResponse response){
		if(testCode.getId()==null){
			return DataRes.success(testCodeService.insert(testCode));
		}
		return DataRes.success(testCodeService.update(testCode));
	}

    /**
     * 根据主键查询
     * @param testCode
     * @return
     */
	@RequestMapping("testCode/selectByPrimaryKey")
	@ResponseBody
	public DataRes selectByPrimaryKey(TestCode testCode, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(testCodeService.selectByPrimaryKey(testCode));
    }

    /**
	* 根据条件查询
	*/
	@RequestMapping("testCode/queryTestCodeByCondition")
	@ResponseBody
	public DataRes queryByCondition(TestCode testCode, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(testCodeService.queryByCondition(testCode));
    }

   /**
	* 分页查询
	* @param testCode 参数
	* @return
	*/
	@RequestMapping("testCode/selectAll")
	@ResponseBody
	public DataRes selectAll(TestCode testCode,HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(testCodeService.selectAllByPaging(testCode));
    }

	/**
	* 导出数据
	* @param tests 参数
	* @return
	*/
	@RequestMapping("testCode/export")
	public void export(TestCode testCode,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<TestCode> list= testCodeService.selectAll(testCode);
		Map<String, String> header = new LinkedHashMap<>();
        header.put("id", "测试生成代码");
        header.put("name", "名称");
        header.put("age", "年龄");
		header.put("status_", "{\"name\":\"状态\",\"1\":\"启用\",\"0\":\"禁用\"}");
		header.put("birthday_", "生日");
		header.put("createTime_", "创建时间");
        header.put("remarks", "备注");
		ExcelUtils.exportExcel("测试生成代码",header,list,response,request);
    }

	/**
	* 跳转到列表页面
	* @return
	*/
	@RequestMapping("testCode/gotoList")
	public String gotoList(TestCode testCode, HttpServletRequest request, HttpServletResponse response){
		return "test/test_code_list";
	}

	/**
	* 跳转到详情页面
	* @return
	*/
	@RequestMapping("testCode/gotoDetail")
	@Auth("testCode/save")
	public String gotoDetail(TestCode testCode, HttpServletRequest request, HttpServletResponse response){
		if(testCode.getId()!=null){
			request.setAttribute("test_code",testCodeService.selectByPrimaryKey(testCode));
		}else {
			request.setAttribute("test_code",testCode);
		}
		return "test/test_code_detail";
	}
}
