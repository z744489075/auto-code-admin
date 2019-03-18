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
import com.zengtengpeng.sys.bean.SysOperationLog;
import com.zengtengpeng.sys.service.SysOperationLogService;
import org.springframework.stereotype.Controller;
import com.zengtengpeng.common.annotation.Auth;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 
 * @author zengtp
 *
 */
@Controller
@Api(description = "操作日志")
public class SysOperationLogController {
	
	@Resource
	private SysOperationLogService sysOperationLogService;


    /**
     * 根据主键查询
     * @param sysOperationLog
     * @return
     */
	@RequestMapping("sysOperationLog/selectByPrimaryKey")
	@ResponseBody
	@ApiOperation(value="根据主键查询", notes="根据主键查询" ,httpMethod="POST")
	public DataRes selectByPrimaryKey(SysOperationLog sysOperationLog, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(sysOperationLogService.selectByPrimaryKey(sysOperationLog));
    }

    /**
	* 根据条件查询
	*/
	@RequestMapping("sysOperationLog/querySysOperationLogByCondition")
	@ResponseBody
	@ApiOperation(value="根据条件查询", notes="根据条件查询" ,httpMethod="POST")
	public DataRes queryByCondition(SysOperationLog sysOperationLog, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(sysOperationLogService.selectByCondition(sysOperationLog));
    }

   /**
	* 分页查询
	* @param sysOperationLog 参数
	* @return
	*/
	@RequestMapping("sysOperationLog/selectAll")
	@ResponseBody
	@ApiOperation(value="分页查询", notes="分页查询" ,httpMethod="POST")
	public DataRes selectAll(SysOperationLog sysOperationLog,HttpServletRequest request, HttpServletResponse response){
		sysOperationLog.setOrderByString("order by id desc");
    	return DataRes.success(sysOperationLogService.selectAllByPaging(sysOperationLog));
    }

	/**
	* 导出数据
	* @param tests 参数
	* @return
	*/
	@RequestMapping("sysOperationLog/export")
	@ApiOperation(value="导出数据", notes="导出数据" ,httpMethod="POST")
	public void export(SysOperationLog sysOperationLog,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysOperationLog> list= sysOperationLogService.selectAll(sysOperationLog);
		Map<String, String> header = new LinkedHashMap<>();
        header.put("id", "操作日志");
        header.put("sysUserId", "用户id");
        header.put("sysAuthId", "权限id");
        header.put("authName", "权限名");
        header.put("authHref", "权限链接");
        header.put("ip", "IP地址");
		header.put("createTime_", "创建时间");
        header.put("requestParam", "请求参数");
        header.put("exceptions", "异常");
		ExcelUtils.exportExcel("操作日志",header,list,response,request);
    }

	/**
	* 跳转到列表页面
	* @return
	*/
	@RequestMapping("sysOperationLog/gotoList")
	@ApiIgnore
	public String gotoList(SysOperationLog sysOperationLog, HttpServletRequest request, HttpServletResponse response){
		return "sys/sys_operation_log_list";
	}

	/**
	* 跳转到详情页面
	* @return
	*/
	@RequestMapping("sysOperationLog/gotoDetail")
	@Auth("sysOperationLog/save")
	@ApiIgnore
	public String gotoDetail(SysOperationLog sysOperationLog, HttpServletRequest request, HttpServletResponse response){
		if(sysOperationLog.getId()!=null){
			request.setAttribute("sys_operation_log",sysOperationLogService.selectByPrimaryKey(sysOperationLog));
		}else {
			request.setAttribute("sys_operation_log",sysOperationLog);
		}
		return "sys/sys_operation_log_detail";
	}
}
