package com.zengtengpeng.sys.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zengtengpeng.common.bean.Page;
import com.zengtengpeng.common.utils.DateUtils;
import java.util.Date;
import org.springframework.util.StringUtils;

/**
* 
* @author zengtp
*/
public class SysLoginLog extends Page {
    private Integer id;

    /**
     * 用户id
     */
    private Integer sysUserId;

    /**
     * 创建时间(也是登录时间)
     */
    private Date createTime;

    /**
     * ip
     */
    private String ip;

    /**
     * 浏览器
     */
    private String browser;

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

    @JsonIgnore
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser == null ? null : browser.trim();
    }

    public String getCreateTime_() {
        return DateUtils.formatDateTime(createTime);
    }
}