package com.zengtengpeng.sys.service;

import com.zengtengpeng.sys.bean.SysRole;
import java.util.List;
public interface SysRoleService {

	/**
	 * 删除
	 * @param sysRole
	 * @return
	 */
	public abstract int deleteByPrimaryKey(SysRole sysRole);

	/**
	 * 新增
	 * @param sysRole
	 * @return
	 */
	public abstract int insert(SysRole sysRole);

	/**
	 * 根据主键查询
	 * @param sysRole
	 * @return
	 */
	public abstract SysRole selectByPrimaryKey(SysRole sysRole);

	/**
	 * 根据条件查询
	 */
	public abstract List<SysRole> querySysRoleByCondition(SysRole sysRole);

	/**
	 * 分页查询
	 * @param sysRole 参数
	 * @return
	 */
	public abstract SysRole selectAll(SysRole sysRole);

	/**
	 * 根据主键更新
	 * @param record
	 * @return
	 */
	public abstract int updateByPrimaryKey(SysRole sysRole);

	/**
	 * 导出所有数据
	 * @param tests
	 * @return
	 */
	List<SysRole> export(SysRole sysRole);

}