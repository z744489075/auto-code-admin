package com.zengtengpeng.test.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zengtengpeng.common.bean.Page;
import com.zengtengpeng.common.utils.DateUtils;
import java.util.Date;
import org.springframework.util.StringUtils;

/**
* 测试代码生成
* @author zengtp
*/
public class TestAutoCode1 extends Page {
    /**
     * 测试代码生成
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * {"name":"状态","1":"启动","0":"禁用","2":"删除"}
     */
    private Integer status;

    /**
     * 发布时间
     */
    private Date pushTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 描述
     */
    private String descc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @JsonIgnore
    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    @JsonIgnore
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescc() {
        return descc;
    }

    public void setDescc(String descc) {
        this.descc = descc == null ? null : descc.trim();
    }

    public String getStatus_() {
        if(StringUtils.isEmpty(status)){
             return "";
        }else if(status.equals(1)){
             return "启动";
        }else if(status.equals(0)){
             return "禁用";
        }else if(status.equals(2)){
             return "删除";
        }
        return "";
    }

    public String getPushTime_() {
        return DateUtils.formatDate(pushTime);
    }

    public String getCreateTime_() {
        return DateUtils.formatDateTime(createTime);
    }
}