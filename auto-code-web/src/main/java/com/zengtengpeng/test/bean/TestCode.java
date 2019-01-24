package com.zengtengpeng.test.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zengtengpeng.common.bean.Page;
import com.zengtengpeng.common.utils.DateUtils;
import java.util.Date;

import com.zengtengpeng.sys.bean.SysUser;
import org.springframework.util.StringUtils;

/**
* 测试生成代码
* @author zengtp
*/
public class TestCode extends Page {
    /**
     * 测试生成代码
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    private SysUser sysUser;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * {"name":"状态","1":"启用","0":"禁用"}
     */
    private Integer status;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 备注
     */
    private String remarks;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

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
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @JsonIgnore
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getStatus_() {
        if(StringUtils.isEmpty(status)){
             return "";
        }else if(status.equals(1)){
             return "启用";
        }else if(status.equals(0)){
             return "禁用";
        }
        return "";
    }

    public String getBirthday_() {
        return DateUtils.formatDate(birthday);
    }

    public String getCreateTime_() {
        return DateUtils.formatDateTime(createTime);
    }
}