package com.zengtengpeng.manyToMany.controller;

import com.zengtengpeng.manyToMany.service.ManyToManyRoleService;
import com.zengtengpeng.manyToMany.bean.ManyToManyRole;
import java.util.ArrayList;
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
import com.zengtengpeng.manyToMany.bean.ManyToManyUser;
import com.zengtengpeng.manyToMany.service.ManyToManyUserService;


/**
 *多对多用户 controller
 */
@Controller
public class ManyToManyUserController  {

	/**
	 * 多对多角色
	 */
	@Resource
	private ManyToManyRoleService manyToManyRoleService;



	@Resource
	private ManyToManyUserService manyToManyUserService;
	/**
	 * 跳转到列表页面
	 */
	@RequestMapping("manyToManyUser/gotoList")
	public String gotoList(ManyToManyUser manyToManyUser,HttpServletRequest request,HttpServletResponse response){
		return "manyToMany/test_many_to_many_user_list";
	}


	/**
	 * 跳转到详情页面
	 */
	@RequestMapping("manyToManyUser/gotoDetail")
	@Auth("manyToManyUser/save")
	public String gotoDetail(ManyToManyUser manyToManyUser,HttpServletRequest request,HttpServletResponse response){
		List<ManyToManyRole> data = manyToManyRoleService.selectAll(null);
		request.setAttribute("goto_detail_test_many_to_many_role",data);

		if(manyToManyUser.getId()!=null){
			ManyToManyUser t = manyToManyUserService.selectManyToManyUserAndManyToManyRoleByCondition(manyToManyUser).get(0);
			request.setAttribute("test_many_to_many_user",t);
			List<ManyToManyRole> list = t.getManyToManyRoleList();
			List<String> sId=new ArrayList<>();
			if(list!=null){
				for (ManyToManyRole manyToManyRole : list) {
					sId.add(manyToManyRole.getId().toString());
				}
			}
			request.setAttribute("sId",sId);

		}else {
			request.setAttribute("test_many_to_many_user",manyToManyUser);
		}
		return "manyToMany/test_many_to_many_user_detail";

	}


	/**
	 * 删除-多对多用户
	 */
	@ResponseBody
	@Auth("manyToManyUser/deleteByPrimaryKey")
	@RequestMapping("manyToManyUser/deleteByPrimaryKey")
	public DataRes deleteByPrimaryKey(ManyToManyUser manyToManyUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyUserService.deleteByPrimaryKey(manyToManyUser));
	}


	/**
	 *  保存 (主键为空则增加 否则 修改)-> 多对多用户
	 */
	@ResponseBody
	@RequestMapping("manyToManyUser/save")
	public DataRes save(ManyToManyUser manyToManyUser,HttpServletRequest request,HttpServletResponse response){
		if(manyToManyUser.getId()==null){
			return DataRes.success(manyToManyUserService.insert(manyToManyUser));
		}
		return DataRes.success(manyToManyUserService.update(manyToManyUser));

	}


	/**
	 * 根据主键查询->多对多用户
	 */
	@ResponseBody
	@Auth("manyToManyUser/selectAllByPaging")
	@RequestMapping("manyToManyUser/selectByPrimaryKey")
	public DataRes selectByPrimaryKey(ManyToManyUser manyToManyUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyUserService.selectByPrimaryKey(manyToManyUser));
	}


	/**
	 * 根据条件查询(所有的实体属性都是条件,如果为空则忽略该字段)->多对多用户
	 */
	@ResponseBody
	@Auth("manyToManyUser/selectAllByPaging")
	@RequestMapping("manyToManyUser/selectByCondition")
	public DataRes selectByCondition(ManyToManyUser manyToManyUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyUserService.selectByCondition(manyToManyUser));
	}


	/**
	 * 分页查询 (所有的实体属性都是条件,如果为空则忽略该字段) (详见Page类.所以的实体都继承该类 默认 page=1 pageSize=10)->多对多用户
	 */
	@ResponseBody
	@Auth("manyToManyUser/selectAllByPaging")
	@RequestMapping("manyToManyUser/selectAllByPaging")
	public DataRes selectAllByPaging(ManyToManyUser manyToManyUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyUserService.selectAllByPaging(manyToManyUser));
	}


	/**
	 * 导出报表->多对多用户
	 */
	@RequestMapping("manyToManyUser/export")
	public void export(ManyToManyUser manyToManyUser,HttpServletRequest request,HttpServletResponse response){
		List<ManyToManyUser> list= manyToManyUserService.selectAll(manyToManyUser);
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
		ExcelUtils.exportExcel("多对多用户",header,list,response,request);

	}


	/**
	 * 级联查询(带分页) 多对多用户--多对多角色
	 */
	@Auth("manyToManyUser/selectAllByPaging")
	@RequestMapping("manyToManyUser/selectManyToManyUserAndManyToManyRole")
	@ResponseBody
	public DataRes selectManyToManyUserAndManyToManyRole(ManyToManyUser manyToManyUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyUserService.selectManyToManyUserAndManyToManyRole(manyToManyUser));
	}


	/**
	 * 级联条件查询 多对多用户--多对多角色
	 */
	@Auth("manyToManyUser/selectAllByPaging")
	@RequestMapping("manyToManyUser/selectManyToManyUserAndManyToManyRoleByCondition")
	@ResponseBody
	public DataRes selectManyToManyUserAndManyToManyRoleByCondition(ManyToManyUser manyToManyUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyUserService.selectManyToManyUserAndManyToManyRoleByCondition(manyToManyUser));
	}


	/**
	 * 级联删除(根据主键删除) 多对多用户--多对多角色
	 */
	@Auth("manyToManyUser/deleteByPrimaryKey")
	@RequestMapping("manyToManyUser/deleteManyToManyUserAndManyToManyRole")
	@ResponseBody
	public DataRes deleteManyToManyUserAndManyToManyRole(ManyToManyUser manyToManyUser,HttpServletRequest request,HttpServletResponse response){
		return DataRes.success(manyToManyUserService.deleteManyToManyUserAndManyToManyRole(manyToManyUser));
	}


	/**
	 * 根据外表id查询主表表所有数据(带分页)
	 */
	@Auth("manyToManyUser/selectAllByPaging")
	@RequestMapping("manyToManyUser/selectManyToManyUserByManyToManyRole")
	@ResponseBody
	public DataRes selectManyToManyUserByManyToManyRole(HttpServletRequest request,HttpServletResponse response,ManyToManyUser manyToManyUser){
		return DataRes.success(manyToManyUserService.selectManyToManyUserByManyToManyRole(manyToManyUser));
	}



}
