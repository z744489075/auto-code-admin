package com.zengtengpeng.sys.controller;

import javax.annotation.Resource;

import com.zengtengpeng.common.utils.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zengtengpeng.common.bean.DataRes;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import com.zengtengpeng.sys.bean.SysLoginLog;
import com.zengtengpeng.sys.service.SysLoginLogService;
import org.springframework.stereotype.Controller;
import com.zengtengpeng.common.annotation.Auth;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 
 * @author zengtp
 *
 */
@Controller
@Api(description = "登录日志")
public class SysLoginLogController {
	
	@Resource
	private SysLoginLogService sysLoginLogService;




    /**
     * 根据主键查询
     * @param sysLoginLog
     * @return
     */
	@RequestMapping("sysLoginLog/selectByPrimaryKey")
	@ResponseBody
	@ApiOperation(value="根据主键查询", notes="根据主键查询" ,httpMethod="POST")
	public DataRes selectByPrimaryKey(SysLoginLog sysLoginLog, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(sysLoginLogService.selectByPrimaryKey(sysLoginLog));
    }

    /**
	* 根据条件查询
	*/
	@RequestMapping("sysLoginLog/querySysLoginLogByCondition")
	@ResponseBody
	@ApiOperation(value="根据条件查询", notes="根据条件查询" ,httpMethod="POST")
	public DataRes queryByCondition(SysLoginLog sysLoginLog, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(sysLoginLogService.selectByCondition(sysLoginLog));
    }

   /**
	* 分页查询
	* @param sysLoginLog 参数
	* @return
	*/
	@RequestMapping("sysLoginLog/selectAll")
	@ResponseBody
	@ApiOperation(value="分页查询", notes="分页查询" ,httpMethod="POST")
	public DataRes selectAll(SysLoginLog sysLoginLog,HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(sysLoginLogService.selectAllByPaging(sysLoginLog));
    }

	/**
	* 导出数据
	* @param tests 参数
	* @return
	*/
	@RequestMapping("sysLoginLog/export")
	@ApiOperation(value="导出数据", notes="导出数据" ,httpMethod="POST")
	public void export(SysLoginLog sysLoginLog,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysLoginLog> list= sysLoginLogService.selectAll(sysLoginLog);
		Map<String, String> header = new LinkedHashMap<>();
        header.put("id", "");
        header.put("sysUserId", "用户id");
		header.put("createTime_", "创建时间(也是登录时间)");
        header.put("ip", "ip");
        header.put("browser", "浏览器");
		ExcelUtils.exportExcel("",header,list,response,request);
    }

	/**
	* 跳转到列表页面
	* @return
	*/
	@RequestMapping("sysLoginLog/gotoList")
	@ApiIgnore
	public String gotoList(SysLoginLog sysLoginLog, HttpServletRequest request, HttpServletResponse response){
		return "sys/sys_login_log_list";
	}

}
