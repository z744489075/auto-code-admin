package com.zengtengpeng.sys.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zengtengpeng.common.bean.Page;
import com.zengtengpeng.common.utils.DateUtils;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

/**
* 后台权限
* @author zengtp
*/
public class SysAuth extends Page {
    /**
     * 后台权限
     */
    private Integer id;

    /**
     * 父id
     */
    private Integer parentAuthId;

    /**
     * 所有父id
     */
    private String parentAuthIds;

    /**
     * 名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 链接
     */
    private String href;

    /**
     * 图标
     */
    private String icon;

    /**
     * {"name":"是否显示","0":"显示","1":"不显示"}
     */
    private Integer shows;

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

    private List<SysAuth> children;

    public List<SysAuth> getChildren() {
        return children;
    }

    public void setChildren(List<SysAuth> children) {
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentAuthId() {
        return parentAuthId;
    }

    public void setParentAuthId(Integer parentAuthId) {
        this.parentAuthId = parentAuthId;
    }

    public String getParentAuthIds() {
        return parentAuthIds;
    }

    public void setParentAuthIds(String parentAuthIds) {
        this.parentAuthIds = parentAuthIds == null ? null : parentAuthIds.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getShows() {
        return shows;
    }

    public void setShows(Integer shows) {
        this.shows = shows;
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

    public String getShows_() {
        if(StringUtils.isEmpty(shows)){
             return "";
        }else if(shows.equals(0)){
             return "显示";
        }else if(shows.equals(1)){
             return "不显示";
        }
        return "";
    }

    public String getCreateTime_() {
        return DateUtils.formatDateTime(createTime);
    }

    public String getUpdateTime_() {
        return DateUtils.formatDateTime(updateTime);
    }
}