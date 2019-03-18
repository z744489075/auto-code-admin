package com.zengtengpeng.sys.controller;

import javax.annotation.Resource;

import com.zengtengpeng.common.annotation.Auth;
import com.zengtengpeng.common.utils.ExcelUtils;
import com.zengtengpeng.sys.bean.SysUser;
import com.zengtengpeng.utils.AuthTreeUtils;
import com.zengtengpeng.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zengtengpeng.common.bean.DataRes;

import java.util.Date;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import com.zengtengpeng.sys.bean.SysAuth;
import com.zengtengpeng.sys.service.SysAuthService;
import org.springframework.stereotype.Controller;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 
 * @author zengtp
 *
 */
@Controller
@Api(description = "用户权限")
public class SysAuthController {
	
	@Resource
	private SysAuthService sysAuthService;

	/**
	 * 删除
	 * @param sysAuth
	 * @return
	 */
	@RequestMapping("sysAuth/deleteByPrimaryKey")
	@ResponseBody
	@ApiOperation(value="删除权限", notes="删除权限" ,httpMethod="POST")
	public DataRes deleteByPrimaryKey(SysAuth sysAuth, HttpServletRequest request, HttpServletResponse response){
		return DataRes.success(sysAuthService.deleteByPrimaryKey(sysAuth));
	}

    /**
	 * 保存 如果id存在则修改否则新增
	 * @param sysAuth
	 * @return
	 */
	@RequestMapping("sysAuth/save")
	@ResponseBody
	@ApiOperation(value="保存", notes="保存" ,httpMethod="POST")
	public DataRes save(SysAuth sysAuth, HttpServletRequest request, HttpServletResponse response){
		sysAuth.setIcon("layui-icon "+sysAuth.getIcon());
		sysAuth.setParentAuthId(sysAuth.getParentAuthId()==null?0:sysAuth.getParentAuthId());
		SysUser user = UserUtils.getUser(request.getSession());
		sysAuth.setCreateUserId(user.getId());
		sysAuth.setUpdateUserId(user.getId());
		if(sysAuth.getId()==null){
			sysAuth.setCreateTime(new Date());
			return DataRes.success(sysAuthService.insert(sysAuth));
		}
		sysAuth.setUpdateTime(new Date());
		return DataRes.success(sysAuthService.update(sysAuth));
	}

    /**
     * 根据主键查询
     * @param sysAuth
     * @return
     */
	@RequestMapping("sysAuth/selectByPrimaryKey")
	@ResponseBody
	@ApiOperation(value="根据主键查询", notes="根据主键查询" ,httpMethod="POST")
	public DataRes selectByPrimaryKey(SysAuth sysAuth, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(sysAuthService.selectByPrimaryKey(sysAuth));
    }

    /**
	* 根据条件查询
	*/
	@RequestMapping("sysAuth/querySysAuthByCondition")
	@ResponseBody
	@ApiOperation(value="根据条件查询", notes="根据条件查询" ,httpMethod="POST")
	public DataRes queryByCondition(SysAuth sysAuth, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(sysAuthService.selectByCondition(sysAuth));
    }

   /**
	* 查询
	* @param sysAuth 参数
	* @return
	*/
	@RequestMapping("sysAuth/selectAll")
	@ResponseBody
	@ApiOperation(value="分页查询", notes="分页查询" ,httpMethod="POST")
	public DataRes selectAll(HttpServletRequest request, HttpServletResponse response){
		SysAuth sysAuth=new SysAuth();
		sysAuth.setOrderByString(" order by sort asc");
		return DataRes.success(sysAuthService.selectAll(sysAuth));
    }
   /**
	* 查询树
	* @param sysAuth 参数
	* @return
	*/
	@RequestMapping("sysAuth/tree")
	@ResponseBody
	@Auth("sysAuth/selectAll")
	@ApiOperation(value="查询树状结构", notes="查询树状结构" ,httpMethod="POST")
	public List<SysAuth> selectTree(HttpServletRequest request, HttpServletResponse response){
		SysAuth sysAuth=new SysAuth();
		sysAuth.setOrderByString(" order by sort asc");
		List<SysAuth> sysAuths = sysAuthService.selectAll(sysAuth);
		List<SysAuth> recurve = AuthTreeUtils.recurve(sysAuths);
		return recurve;
    }

	/**
	* 导出数据
	* @param tests 参数
	* @return
	*/
	@RequestMapping("sysAuth/export")
	@ApiOperation(value="导出数据", notes="导出数据" ,httpMethod="POST")
	public void export(SysAuth sysAuth,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysAuth> list= sysAuthService.selectAll(sysAuth);
		Map<String, String> header = new LinkedHashMap<>();
        header.put("id", "后台权限");
        header.put("parentAuthId", "父id");
        header.put("parentAuthIds", "所有父id");
        header.put("name", "名称");
        header.put("sort", "排序");
        header.put("href", "链接");
        header.put("icon", "图标");
		header.put("shows_", "{\"name\":\"是否显示\",\"0\":\"显示\",\"1\":\"不显示\"}");
        header.put("createUserId", "创建者");
		header.put("createTime_", "创建时间");
        header.put("updateUserId", "更新者");
		header.put("updateTime_", "更新时间");
		ExcelUtils.exportExcel("后台权限",header,list,response,request);
    }

	/**
	* 跳转到列表页面
	* @return
	*/
	@RequestMapping("sysAuth/gotoList")
	@ApiIgnore
	public String gotoList(SysAuth sysAuth, HttpServletRequest request, HttpServletResponse response){
//		return "sys/tree";
		return "sys/sys_auth_list";
	}


	/**
	* 跳转到详情页面
	* @return
	*/
	@RequestMapping("sysAuth/gotoDetail")
	@Auth("sysAuth/save")
	@ApiIgnore
	public String gotoDetail(SysAuth sysAuth, HttpServletRequest request, HttpServletResponse response){
		if(sysAuth.getId()!=null){
			request.setAttribute("sys_auth",sysAuthService.selectByPrimaryKey(sysAuth));
		}else {
			request.setAttribute("sys_auth",sysAuth);
		}
		return "sys/sys_auth_detail";
	}
}
