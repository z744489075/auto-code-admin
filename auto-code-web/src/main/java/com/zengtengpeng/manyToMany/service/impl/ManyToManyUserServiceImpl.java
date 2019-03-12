package com.zengtengpeng.manyToMany.service.impl;

import com.zengtengpeng.manyToMany.dao.ManyToManyRoleDao;
import com.zengtengpeng.manyToMany.bean.ManyToManyUser;
import com.zengtengpeng.manyToMany.bean.ManyToManyRole;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zengtengpeng.manyToMany.dao.ManyToManyUserDao;
import com.zengtengpeng.manyToMany.service.ManyToManyUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;


/**
 *用户 serverImpl
 */
@Service
@Transactional
public class ManyToManyUserServiceImpl   implements ManyToManyUserService {

	/**
	 * 角色
	 */
	@Resource
	private ManyToManyRoleDao manyToManyRoleDao;



	/**
	 * 注入dao
	 */
	@Resource
	private ManyToManyUserDao manyToManyUserDao;
	/**
	 * 初始化
	 */
	@Override
	public ManyToManyUserDao initDao(){
		return manyToManyUserDao;
	}


	/**
	 * 多对多重写insert
	 */
	@Override
	public int insert(ManyToManyUser manyToManyUser){
		int insert = manyToManyUserDao.insert(manyToManyUser);
		String id = manyToManyUser.getRoleId();
		if(id !=null && !"".equals(id)){
			String[] split = id.split(",");
			manyToManyUserDao.insertRelation(manyToManyUser.getId().toString(),split);
		}
		return insert;

	}


	/**
	 * 多对多重写Update
	 */
	@Override
	public int update(ManyToManyUser manyToManyUser){
		Integer update = manyToManyUserDao.update(manyToManyUser);
		String id = manyToManyUser.getRoleId();
		if(id !=null && !"".equals(id)){
			ManyToManyRole d=new ManyToManyRole();
			d.setUserId(manyToManyUser.getId().toString());
			manyToManyRoleDao.deleteManyToManyRoleByManyToManyUser(d);
			String[] split = id.split(",");
			manyToManyUserDao.insertRelation(manyToManyUser.getId().toString(),split);
		}
		return update;

	}


	/**
	 * 级联查询(带分页) 用户--角色
	 */
	@Override
	public ManyToManyUser selectManyToManyUserAndManyToManyRole(ManyToManyUser manyToManyUser){
		manyToManyUser = this.selectAllByPaging(manyToManyUser);
		if(manyToManyUser!=null && manyToManyUser.getRows()!=null){
			manyToManyUser.getRows().forEach(t->{
				ManyToManyUser data= (ManyToManyUser) t;
				ManyToManyRole manyToManyRole=new ManyToManyRole();
				manyToManyRole.setUserId(data.getId().toString());
				List<ManyToManyRole> datas=manyToManyRoleDao.selectManyToManyRoleByManyToManyUser(manyToManyRole);
				data.setManyToManyRoleList(datas);
			});
		}
		return manyToManyUser;

	}


	/**
	 * 级联条件查询 用户--角色
	 */
	@Override
	public List<ManyToManyUser> selectManyToManyUserAndManyToManyRoleByCondition(ManyToManyUser manyToManyUser){
		List<ManyToManyUser> datas = this.selectByCondition(manyToManyUser);
		if(datas!=null){
			datas.forEach(t->{
				ManyToManyRole manyToManyRole=new ManyToManyRole();
				manyToManyRole.setUserId(t.getId().toString());
				List<ManyToManyRole> lists=manyToManyRoleDao.selectManyToManyRoleByManyToManyUser(manyToManyRole);
				t.setManyToManyRoleList(lists);
			});
		}
		return datas;

	}


	/**
	 * 级联删除(根据主表删除) 用户--角色
	 */
	@Override
	public Integer deleteManyToManyUserAndManyToManyRole(ManyToManyUser manyToManyUser){
		ManyToManyRole manyToManyRole=new ManyToManyRole();
		manyToManyUser = manyToManyUserDao.selectByPrimaryKey(manyToManyUser);
		manyToManyRole.setUserId(manyToManyUser.getId().toString());
		manyToManyRoleDao.deleteManyToManyRoleByManyToManyUser(manyToManyRole);
		return manyToManyUserDao.deleteByPrimaryKey(manyToManyUser);

	}


	/**
	 * 根据外表id查询主表所有数据(带分页)
	 */
	@Override
	public ManyToManyUser selectManyToManyUserByManyToManyRole(ManyToManyUser t){
		PageHelper.startPage(t.getPage(), t.getPageSize());
		List<ManyToManyUser> lists = manyToManyUserDao.selectManyToManyUserByManyToManyRole(t);
		PageInfo pageInfo = new PageInfo(lists);
		t.setRows(lists);
		t.setTotal((new Long(pageInfo.getTotal())).intValue());
		return t;

	}



}
