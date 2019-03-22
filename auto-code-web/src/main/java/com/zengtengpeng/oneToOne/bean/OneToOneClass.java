package com.zengtengpeng.oneToOne.bean;

import com.zengtengpeng.oneToOne.bean.OneToOneUser;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zengtengpeng.common.bean.Page;
import com.zengtengpeng.common.utils.DateUtils;
import java.util.Date;
import java.math.BigDecimal;
import com.zengtengpeng.autoCode.utils.MyStringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 *一对一班级 bean
 */
@ApiModel(description="一对一班级")
public class OneToOneClass   extends Page  {

	/**
	 * 一对一用户
	 */
	@ApiModelProperty(hidden = true)
	private OneToOneUser oneToOneUser;



	/**
	 * 班级id
	 */
	@ApiModelProperty(value = "班级id")
	private Integer id;
	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id")
	private Integer userId;
	/**
	 * 班级名称
	 */
	@ApiModelProperty(value = "班级名称")
	private String className;
	/**
	 * 班级人数
	 */
	@ApiModelProperty(value = "班级人数")
	private Integer quantity;
	/**
	 * create_time
	 */
	@ApiModelProperty(value = "create_time")
	private Date createTime;
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


	public String getClassName(){
		return className;
	}


	public void setClassName(String className){
		this.className=className;
	}


	public Integer getQuantity(){
		return quantity;
	}


	public void setQuantity(Integer quantity){
		this.quantity=quantity;
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


	/**
	 * 一对一用户
	 */
	public OneToOneUser getOneToOneUser(){
		return oneToOneUser;
	}


	/**
	 * 一对一用户
	 */
	public void setOneToOneUser(OneToOneUser oneToOneUser){
		this.oneToOneUser = oneToOneUser;
	}



}
