package com.zengtengpeng.sys.dao;

import com.zengtengpeng.common.dao.BaseDao;
import com.zengtengpeng.sys.bean.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserDao extends BaseDao<SysUser> {
    /**
     * 批量新增
     * @param userId
     * @param roles
     */
    void insertBatch(@Param("userId") Integer userId,@Param("roles") List<String> roles);

    void deleteRoles(SysUser sysUser);

    /**
     * 修改密码
     * @param user
     * @return
     */
    int updatePassword(SysUser user);
}