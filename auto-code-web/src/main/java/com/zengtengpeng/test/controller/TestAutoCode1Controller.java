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
import com.zengtengpeng.test.bean.TestAutoCode1;
import com.zengtengpeng.test.service.TestAutoCode1Service;
import org.springframework.stereotype.Controller;

/**
 * 
 * @author zengtp
 *
 */
@Controller
public class TestAutoCode1Controller {
	
	@Resource
	private TestAutoCode1Service testAutoCode1Service;

	/**
	 * 删除
	 * @param testAutoCode1
	 * @return
	 */
	@RequestMapping("/testAutoCode1/deleteByPrimaryKey")
	@ResponseBody
	public DataRes deleteByPrimaryKey(TestAutoCode1 testAutoCode1, HttpServletRequest request, HttpServletResponse response){
		return DataRes.success(testAutoCode1Service.deleteByPrimaryKey(testAutoCode1));
	}

    /**
	 * 保存 如果id存在则修改否则新增
	 * @param testAutoCode1
	 * @return
	 */
	@RequestMapping("/testAutoCode1/save")
	@ResponseBody
	public DataRes save(TestAutoCode1 testAutoCode1, HttpServletRequest request, HttpServletResponse response){
		if(testAutoCode1.getId()==null){
			return DataRes.success(testAutoCode1Service.insert(testAutoCode1));
		}
		return DataRes.success(testAutoCode1Service.updateByPrimaryKey(testAutoCode1));
	}

    /**
     * 根据主键查询
     * @param testAutoCode1
     * @return
     */
	@RequestMapping("/testAutoCode1/selectByPrimaryKey")
	@ResponseBody
	public DataRes selectByPrimaryKey(TestAutoCode1 testAutoCode1, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(testAutoCode1Service.selectByPrimaryKey(testAutoCode1));
    }

    /**
	* 根据条件查询
	*/
	@RequestMapping("/testAutoCode1/queryTestAutoCode1ByCondition")
	@ResponseBody
	public DataRes queryTestAutoCode1ByCondition(TestAutoCode1 testAutoCode1, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(testAutoCode1Service.queryTestAutoCode1ByCondition(testAutoCode1));
    }

   /**
	* 分页查询
	* @param testAutoCode1 参数
	* @return
	*/
	@RequestMapping("/testAutoCode1/selectAll")
	@ResponseBody
	public DataRes selectAll(TestAutoCode1 testAutoCode1,HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(testAutoCode1Service.selectAll(testAutoCode1));
    }

	/**
	* 导出数据
	* @param tests 参数
	* @return
	*/
	@RequestMapping("/testAutoCode1/export")
	public void export(TestAutoCode1 testAutoCode1,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<TestAutoCode1> list= testAutoCode1Service.export(testAutoCode1);
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
	@RequestMapping("/testAutoCode1/gotoList")
	public String gotoList(TestAutoCode1 testAutoCode1, HttpServletRequest request, HttpServletResponse response){
		return "test/test_auto_code1_list";
	}

	/**
	* 跳转到详情页面
	* @return
	*/
	@RequestMapping("/testAutoCode1/gotoDetail")
	public String gotoDetail(TestAutoCode1 testAutoCode1, HttpServletRequest request, HttpServletResponse response){
		if(testAutoCode1.getId()!=null){
			request.setAttribute("test_auto_code1",testAutoCode1Service.selectByPrimaryKey(testAutoCode1));
		}else {
			request.setAttribute("test_auto_code1",testAutoCode1);
		}
		return "test/test_auto_code1_detail";
	}
}
