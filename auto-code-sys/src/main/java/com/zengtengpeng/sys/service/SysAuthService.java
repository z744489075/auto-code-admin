package com.zengtengpeng.sys.service;

import com.zengtengpeng.sys.bean.SysAuth;
import java.util.List;
public interface SysAuthService {

	/**
	 * 删除
	 * @param sysAuth
	 * @return
	 */
	public abstract int deleteByPrimaryKey(SysAuth sysAuth);

	/**
	 * 新增
	 * @param sysAuth
	 * @return
	 */
	public abstract int insert(SysAuth sysAuth);

	/**
	 * 根据主键查询
	 * @param sysAuth
	 * @return
	 */
	public abstract SysAuth selectByPrimaryKey(SysAuth sysAuth);

	/**
	 * 根据条件查询
	 */
	public abstract List<SysAuth> querySysAuthByCondition(SysAuth sysAuth);

	/**
	 * 分页查询
	 * @param sysAuth 参数
	 * @return
	 */
	public abstract SysAuth selectAll(SysAuth sysAuth);

	/**
	 * 根据主键更新
	 * @param record
	 * @return
	 */
	public abstract int updateByPrimaryKey(SysAuth sysAuth);

	/**
	 * 导出所有数据
	 * @param tests
	 * @return
	 */
	List<SysAuth> export(SysAuth sysAuth);

}