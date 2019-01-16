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
import com.zengtengpeng.test.bean.TestAutoCode2;
import com.zengtengpeng.test.service.TestAutoCode2Service;
import org.springframework.stereotype.Controller;

/**
 * 
 * @author zengtp
 *
 */
@Controller
public class TestAutoCode2Controller {
	
	@Resource
	private TestAutoCode2Service testAutoCode2Service;

	/**
	 * 删除
	 * @param testAutoCode2
	 * @return
	 */
	@RequestMapping("/testAutoCode2/deleteByPrimaryKey")
	@ResponseBody
	public DataRes deleteByPrimaryKey(TestAutoCode2 testAutoCode2, HttpServletRequest request, HttpServletResponse response){
		return DataRes.success(testAutoCode2Service.deleteByPrimaryKey(testAutoCode2));
	}

    /**
	 * 保存 如果id存在则修改否则新增
	 * @param testAutoCode2
	 * @return
	 */
	@RequestMapping("/testAutoCode2/save")
	@ResponseBody
	public DataRes save(TestAutoCode2 testAutoCode2, HttpServletRequest request, HttpServletResponse response){
		if(testAutoCode2.getCodeId()==null){
			return DataRes.success(testAutoCode2Service.insert(testAutoCode2));
		}
		return DataRes.success(testAutoCode2Service.updateByPrimaryKey(testAutoCode2));
	}

    /**
     * 根据主键查询
     * @param testAutoCode2
     * @return
     */
	@RequestMapping("/testAutoCode2/selectByPrimaryKey")
	@ResponseBody
	public DataRes selectByPrimaryKey(TestAutoCode2 testAutoCode2, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(testAutoCode2Service.selectByPrimaryKey(testAutoCode2));
    }

    /**
	* 根据条件查询
	*/
	@RequestMapping("/testAutoCode2/queryTestAutoCode2ByCondition")
	@ResponseBody
	public DataRes queryTestAutoCode2ByCondition(TestAutoCode2 testAutoCode2, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(testAutoCode2Service.queryTestAutoCode2ByCondition(testAutoCode2));
    }

   /**
	* 分页查询
	* @param testAutoCode2 参数
	* @return
	*/
	@RequestMapping("/testAutoCode2/selectAll")
	@ResponseBody
	public DataRes selectAll(TestAutoCode2 testAutoCode2,HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(testAutoCode2Service.selectAll(testAutoCode2));
    }

	/**
	* 导出数据
	* @param tests 参数
	* @return
	*/
	@RequestMapping("/testAutoCode2/export")
	public void export(TestAutoCode2 testAutoCode2,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<TestAutoCode2> list= testAutoCode2Service.export(testAutoCode2);
		Map<String, String> header = new LinkedHashMap<>();
        header.put("codeId", "测试代码生成");
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
	@RequestMapping("/testAutoCode2/gotoList")
	public String gotoList(TestAutoCode2 testAutoCode2, HttpServletRequest request, HttpServletResponse response){
		return "test/test_auto_code2_list";
	}

	/**
	* 跳转到详情页面
	* @return
	*/
	@RequestMapping("/testAutoCode2/gotoDetail")
	public String gotoDetail(TestAutoCode2 testAutoCode2, HttpServletRequest request, HttpServletResponse response){
		if(testAutoCode2.getCodeId()!=null){
			request.setAttribute("test_auto_code2",testAutoCode2Service.selectByPrimaryKey(testAutoCode2));
		}else {
			request.setAttribute("test_auto_code2",testAutoCode2);
		}
		return "test/test_auto_code2_detail";
	}
}
