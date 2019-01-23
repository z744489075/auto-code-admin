package com.zengtengpeng.sys.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zengtengpeng.common.bean.Page;
import com.zengtengpeng.common.utils.DateUtils;
import java.util.Date;
import org.springframework.util.StringUtils;

/**
* 操作日志
* @author zengtp
*/
public class SysOperationLog extends Page {
    /**
     * 操作日志
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer sysUserId;

    /**
     * 权限id
     */
    private Integer sysAuthId;

    /**
     * 权限名
     */
    private String authName;

    /**
     * 权限链接
     */
    private String authHref;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 异常
     */
    private String exceptions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Integer getSysAuthId() {
        return sysAuthId;
    }

    public void setSysAuthId(Integer sysAuthId) {
        this.sysAuthId = sysAuthId;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName == null ? null : authName.trim();
    }

    public String getAuthHref() {
        return authHref;
    }

    public void setAuthHref(String authHref) {
        this.authHref = authHref == null ? null : authHref.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    @JsonIgnore
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam == null ? null : requestParam.trim();
    }

    public String getExceptions() {
        return exceptions;
    }

    public void setExceptions(String exceptions) {
        this.exceptions = exceptions == null ? null : exceptions.trim();
    }

    public String getCreateTime_() {
        return DateUtils.formatDateTime(createTime);
    }
}