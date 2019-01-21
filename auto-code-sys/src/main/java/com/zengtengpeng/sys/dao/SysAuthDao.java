package com.zengtengpeng.sys.dao;

import com.zengtengpeng.common.dao.BaseDao;
import com.zengtengpeng.sys.bean.SysAuth;
import com.zengtengpeng.sys.bean.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysAuthDao extends BaseDao<SysAuth> {
    /**
     * 批量新增
     * @param auths 权限集合
     * @param roleId 角色id
     */
    void insertBatch(@Param("auths") List<String> auths, @Param("roleId") Integer roleId);

    /**
     * 查询角色下的权限
     * @param sysRole
     * @return
     */
    List<SysAuth> queryByRole(SysRole sysRole);

    /**
     * 查询用户权限
     * @param userId
     * @return
     */
    List<SysAuth> queryByUser(Integer userId);
}