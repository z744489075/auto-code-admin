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
import com.zengtengpeng.test.bean.TestAutoCode;
import com.zengtengpeng.test.service.TestAutoCodeService;
import org.springframework.stereotype.Controller;

/**
 * 
 * @author zengtp
 *
 */
@Controller
public class TestAutoCodeController {
	
	@Resource
	private TestAutoCodeService testAutoCodeService;

	/**
	 * 删除
	 * @param testAutoCode
	 * @return
	 */
	@RequestMapping("/testAutoCode/deleteByPrimaryKey")
	@ResponseBody
	public DataRes deleteByPrimaryKey(TestAutoCode testAutoCode, HttpServletRequest request, HttpServletResponse response){
		return DataRes.success(testAutoCodeService.deleteByPrimaryKey(testAutoCode));
	}

    /**
	 * 保存 如果id存在则修改否则新增
	 * @param testAutoCode
	 * @return
	 */
	@RequestMapping("/testAutoCode/save")
	@ResponseBody
	public DataRes save(TestAutoCode testAutoCode, HttpServletRequest request, HttpServletResponse response){
		if(testAutoCode.getId()==null){
			return DataRes.success(testAutoCodeService.insert(testAutoCode));
		}
		return DataRes.success(testAutoCodeService.updateByPrimaryKey(testAutoCode));
	}

    /**
     * 根据主键查询
     * @param testAutoCode
     * @return
     */
	@RequestMapping("/testAutoCode/selectByPrimaryKey")
	@ResponseBody
	public DataRes selectByPrimaryKey(TestAutoCode testAutoCode, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(testAutoCodeService.selectByPrimaryKey(testAutoCode));
    }

    /**
	* 根据条件查询
	*/
	@RequestMapping("/testAutoCode/queryTestAutoCodeByCondition")
	@ResponseBody
	public DataRes queryTestAutoCodeByCondition(TestAutoCode testAutoCode, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(testAutoCodeService.queryTestAutoCodeByCondition(testAutoCode));
    }

   /**
	* 分页查询
	* @param testAutoCode 参数
	* @return
	*/
	@RequestMapping("/testAutoCode/selectAll")
	@ResponseBody
	public DataRes selectAll(TestAutoCode testAutoCode,HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(testAutoCodeService.selectAll(testAutoCode));
    }

	/**
	* 导出数据
	* @param tests 参数
	* @return
	*/
	@RequestMapping("/testAutoCode/export")
	public void export(TestAutoCode testAutoCode,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<TestAutoCode> list= testAutoCodeService.export(testAutoCode);
		Map<String, String> header = new LinkedHashMap<>();
        header.put("id", "测试代码生成");
        header.put("name", "名称");
        header.put("age", "年龄");
		header.put("status_", "{\"name\":\"状态\",\"1\":\"启动\",\"0\":\"禁用\",\"2\":\"删除\"}");
		header.put("pushTime_", "发布时间");
		header.put("createTime_", "创建时间");
        header.put("descc", "描述");
		ExcelUtils.exportExcel("测试代码生成",header,list,response,request);
    }

	/**
	* 跳转到列表页面
	* @return
	*/
	@RequestMapping("/testAutoCode/gotoList")
	public String gotoList(TestAutoCode testAutoCode, HttpServletRequest request, HttpServletResponse response){
		return "test/test_auto_code_list";
	}

	/**
	* 跳转到详情页面
	* @return
	*/
	@RequestMapping("/testAutoCode/gotoDetail")
	public String gotoDetail(TestAutoCode testAutoCode, HttpServletRequest request, HttpServletResponse response){
		if(testAutoCode.getId()!=null){
			request.setAttribute("test_auto_code",testAutoCodeService.selectByPrimaryKey(testAutoCode));
		}else {
			request.setAttribute("test_auto_code",testAutoCode);
		}
		return "test/test_auto_code_detail";
	}
}
