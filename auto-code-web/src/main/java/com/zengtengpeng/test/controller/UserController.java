package com.zengtengpeng.test.controller;








import com.zengtengpeng.test.service.TestRoleService;
import com.zengtengpeng.test.bean.TestRole;
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
import com.zengtengpeng.test.bean.User;
import com.zengtengpeng.test.service.UserService;


/**
 *用户 controller
 */
@Controller
public class UserController  {















	/**
	 * 角色
	 */
	@Resource
	private TestRoleService testRoleService;



	@Resource
	private UserService userService;
	/**
	 * 跳转到列表页面
	 */
	@RequestMapping("user/gotoList")
	public String gotoList(User user,HttpServletRequest request,HttpServletResponse response){
		return "test/test_user_list";
	}


	/**
	 * 跳转到详情页面
	 */
	@RequestMapping("user/gotoDetail")
	@Auth("user/save")
	public String gotoDetail(User user,HttpServletRequest request,HttpServletResponse response){
		if(user.getId()!=null){
			 request.setAttribute("test_user",userService.selectByPrimaryKey(user));
		}else {
			request.setAttribute("test_user",user);
		}
		return "test/test_user_detail";

	}


	/**
	 * 删除-用户
	 */
	@ResponseBody
	@Auth("user/deleteByPrimaryKey")
	@RequestMapping("user/deleteByPrimaryKey")
	public DataRes deleteByPrimaryKey(User user,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(userService.deleteByPrimaryKey(user));
	}


	/**
	 *  保存 (主键为空则增加 否则 修改)-> 用户
	 */
	@ResponseBody
	@RequestMapping("user/save")
	public DataRes save(User user,HttpServletRequest request,HttpServletResponse response){
		if(user.getId()==null){
			return DataRes.success(userService.insert(user));
		}
		return DataRes.success(userService.update(user));

	}


	/**
	 * 根据主键查询->用户
	 */
	@ResponseBody
	@Auth("user/selectAllByPaging")
	@RequestMapping("user/selectByPrimaryKey")
	public DataRes selectByPrimaryKey(User user,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(userService.selectByPrimaryKey(user));
	}


	/**
	 * 根据条件查询(所有的实体属性都是条件,如果为空则忽略该字段)->用户
	 */
	@ResponseBody
	@Auth("user/selectAllByPaging")
	@RequestMapping("user/selectByCondition")
	public DataRes selectByCondition(User user,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(userService.selectByCondition(user));
	}


	/**
	 * 分页查询 (所有的实体属性都是条件,如果为空则忽略该字段) (详见Page类.所以的实体都继承该类 默认 page=1 pageSize=10)->用户
	 */
	@ResponseBody
	@Auth("user/selectAllByPaging")
	@RequestMapping("user/selectAllByPaging")
	public DataRes selectAllByPaging(User user,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(userService.selectAllByPaging(user));
	}


	/**
	 * 导出报表->用户
	 */
	@RequestMapping("user/export")
	public void export(User user,HttpServletRequest request,HttpServletResponse response){
		List<User> list= userService.selectAll(user);
		Map<String, String> header = new LinkedHashMap<>();
		header.put("id", "id");
		header.put("name", "名称");
		header.put("age", "年龄");
		header.put("status_", "{\"name\":\"状态\",\"1\":\"启用\",\"0\":\"禁用\"}");
		header.put("birthday_", "生日");
		header.put("remarks", "备注");
		header.put("mun", "数字");
		header.put("createTime_", "创建时间");
		header.put("updateTime_", "更新时间");
		ExcelUtils.exportExcel("用户",header,list,response,request);

	}


	/**
	 * 级联查询(带分页) 用户--角色
	 */
	@Auth("user/selectAllByPaging")
	@RequestMapping("user/selectUserAndTestRole")
	@ResponseBody
	public DataRes selectUserAndTestRole(User user,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(userService.selectUserAndTestRole(user));
	}


	/**
	 * 级联条件查询 用户--角色
	 */
	@Auth("user/selectAllByPaging")
	@RequestMapping("user/selectUserAndTestRoleByCondition")
	@ResponseBody
	public DataRes selectUserAndTestRoleByCondition(User user,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(userService.selectUserAndTestRoleByCondition(user));
	}


	/**
	 * 级联删除(根据主键删除) 用户--角色
	 */
	@Auth("user/deleteByPrimaryKey")
	@RequestMapping("user/deleteUserAndTestRole")
	@ResponseBody
	public DataRes deleteUserAndTestRole(User user,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(userService.deleteUserAndTestRole(user));
	}


	/**
	 * 根据外表id查询主表表所有数据(带分页)
	 */
	@Auth("user/selectAllByPaging")
	@RequestMapping("user/selectUserByTestRole")
	@ResponseBody
	public DataRes selectUserByTestRole(HttpServletRequest request,HttpServletResponse response,User user){
		return DataRes.success(userService.selectUserByTestRole(user));
	}










}
