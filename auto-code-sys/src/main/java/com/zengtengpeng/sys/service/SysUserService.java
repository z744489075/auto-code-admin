package com.zengtengpeng.sys.service;

import com.zengtengpeng.sys.bean.SysUser;
import java.util.List;
public interface SysUserService {

	/**
	 * 删除
	 * @param sysUser
	 * @return
	 */
	public abstract int deleteByPrimaryKey(SysUser sysUser);

	/**
	 * 新增
	 * @param sysUser
	 * @return
	 */
	public abstract int insert(SysUser sysUser);

	/**
	 * 根据主键查询
	 * @param sysUser
	 * @return
	 */
	public abstract SysUser selectByPrimaryKey(SysUser sysUser);

	/**
	 * 根据条件查询
	 */
	public abstract List<SysUser> querySysUserByCondition(SysUser sysUser);

	/**
	 * 分页查询
	 * @param sysUser 参数
	 * @return
	 */
	public abstract SysUser selectAll(SysUser sysUser);

	/**
	 * 根据主键更新
	 * @param record
	 * @return
	 */
	public abstract int updateByPrimaryKey(SysUser sysUser);

	/**
	 * 导出所有数据
	 * @param tests
	 * @return
	 */
	List<SysUser> export(SysUser sysUser);

}