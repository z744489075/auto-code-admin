package com.zengtengpeng.sys.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zengtengpeng.common.bean.Page;
import com.zengtengpeng.common.utils.DateUtils;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

/**
* 后台角色
* @author zengtp
*/
public class SysRole extends Page {
    /**
     * 后台角色
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

    private List<SysAuth> sysAuths;

    public List<SysAuth> getSysAuths() {
        return sysAuths;
    }

    public void setSysAuths(List<SysAuth> sysAuths) {
        this.sysAuths = sysAuths;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    @JsonIgnore
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    @JsonIgnore
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDels() {
        return dels;
    }

    public void setDels(Integer dels) {
        this.dels = dels;
    }

    public String getStatus_() {
        if(StringUtils.isEmpty(status)){
             return "";
        }else if(status.equals(0)){
             return "启用";
        }else if(status.equals(1)){
             return "禁用";
        }
        return "";
    }

    public String getCreateTime_() {
        return DateUtils.formatDateTime(createTime);
    }

    public String getUpdateTime_() {
        return DateUtils.formatDateTime(updateTime);
    }

    public String getDels_() {
        if(StringUtils.isEmpty(dels)){
             return "";
        }else if(dels.equals(0)){
             return "正常";
        }else if(dels.equals(1)){
             return "删除";
        }
        return "";
    }
}