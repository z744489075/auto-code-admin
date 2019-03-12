package com.zengtengpeng.oneToMany.bean;

import com.zengtengpeng.oneToMany.bean.OneToManyUser;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zengtengpeng.common.bean.Page;
import com.zengtengpeng.common.utils.DateUtils;
import java.util.Date;
import java.math.BigDecimal;
import com.zengtengpeng.autoCode.utils.MyStringUtils;


/**
 *一对多收货地址 bean
 */
public class OneToManyAddr   extends Page  {

	/**
	 * 一对多用户
	 */
	private OneToManyUser oneToManyUser;



	/**
	 * 用户收货地址id
	 */
	private Integer id;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 姓名
	 */
	private String addrName;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 收货地址
	 */
	private String addr;
	/**
	 * {"name":"状态","1":"启用","2":"删除"}
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	public Integer getId(){
		return id;
	}


	public void setId(Integer id){
		this.id=id;
	}


	public Integer getUserId(){
		return userId;
	}


	public void setUserId(Integer userId){
		this.userId=userId;
	}


	public String getAddrName(){
		return addrName;
	}


	public void setAddrName(String addrName){
		this.addrName=addrName;
	}


	public String getPhone(){
		return phone;
	}


	public void setPhone(String phone){
		this.phone=phone;
	}


	public String getAddr(){
		return addr;
	}


	public void setAddr(String addr){
		this.addr=addr;
	}


	public String getStatus_(){
		if(MyStringUtils.isEmpty(status)){
			 return "";
		}else if(status.toString().equals("1")){
			return "启用";
		}else if(status.toString().equals("2")){
			return "删除";
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


	/**
	 * 一对多用户
	 */
	public OneToManyUser getOneToManyUser(){
		return oneToManyUser;
	}


	/**
	 * 一对多用户
	 */
	public void setOneToManyUser(OneToManyUser oneToManyUser){
		this.oneToManyUser = oneToManyUser;
	}



}
