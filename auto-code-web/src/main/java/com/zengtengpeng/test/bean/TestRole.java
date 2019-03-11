package com.zengtengpeng.test.bean;

import com.zengtengpeng.test.bean.User;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zengtengpeng.common.bean.Page;
import com.zengtengpeng.common.utils.DateUtils;
import java.util.Date;
import java.math.BigDecimal;
import com.zengtengpeng.autoCode.utils.MyStringUtils;


/**
 *测试角色 bean
 */
public class TestRole   extends Page  {

	/**
	 * 用户
	 */
	private List<User> userList;
	/**
	 * 用户id
	 */
	private String userId;



	/**
	 * 测试角色
	 */
	private Integer id;
	/**
	 * 角色名称
	 */
	private String name;
	/**
	 * {"name":"状态","0":"启用","1":"禁用"}
	 */
	private Integer status;
	/**
	 * 创建者
	 */
	private Integer createUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新者
	 */
	private Integer updateUserId;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * {"name":"是否删除","0":"正常","1":"删除"}
	 */
	private Integer dels;
	public Integer getId(){
		return id;
	}


	public void setId(Integer id){
		this.id=id;
	}


	public String getName(){
		return name;
	}


	public void setName(String name){
		this.name=name;
	}


	public String getStatus_(){
		if(MyStringUtils.isEmpty(status)){
			 return "";
		}else if(status.toString().equals("0")){
			return "启用";
		}else if(status.toString().equals("1")){
			return "禁用";
		}
		return "";

	}


	@JsonIgnore
	public Integer getStatus(){
		return status;
	}


	public void setStatus(Integer status){
		this.status=status;
	}


	public Integer getCreateUserId(){
		return createUserId;
	}


	public void setCreateUserId(Integer createUserId){
		this.createUserId=createUserId;
	}


	public String getCreateTime_(){
		return DateUtils.formatDateTime(createTime);
	}


	@JsonIgnore
	public Date getCreateTime(){
		return createTime;
	}


	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}


	public Integer getUpdateUserId(){
		return updateUserId;
	}


	public void setUpdateUserId(Integer updateUserId){
		this.updateUserId=updateUserId;
	}


	public String getUpdateTime_(){
		return DateUtils.formatDateTime(updateTime);
	}


	@JsonIgnore
	public Date getUpdateTime(){
		return updateTime;
	}


	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}


	public String getDels_(){
		if(MyStringUtils.isEmpty(status)){
			 return "";
		}else if(dels.toString().equals("0")){
			return "正常";
		}else if(dels.toString().equals("1")){
			return "删除";
		}
		return "";

	}


	@JsonIgnore
	public Integer getDels(){
		return dels;
	}


	public void setDels(Integer dels){
		this.dels=dels;
	}


	/**
	 * 用户
	 */
	public List<User> getUserList(){
		return userList;
	}


	/**
	 * 用户
	 */
	public void setUserList(List<User> userList){
		this.userList = userList;
	}


	public void setUserId(String userId){
		this.userId=userId;
	}


	public String getUserId(){
		return userId;
	}



}
