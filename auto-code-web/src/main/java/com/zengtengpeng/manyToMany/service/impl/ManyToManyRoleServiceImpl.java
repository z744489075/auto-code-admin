package com.zengtengpeng.manyToMany.service.impl;

import com.zengtengpeng.manyToMany.dao.ManyToManyUserDao;
import com.zengtengpeng.manyToMany.bean.ManyToManyUser;
import com.zengtengpeng.manyToMany.bean.ManyToManyRole;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zengtengpeng.manyToMany.dao.ManyToManyRoleDao;
import com.zengtengpeng.manyToMany.service.ManyToManyRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;


/**
 *多对多角色 serverImpl
 */
@Service
@Transactional
public class ManyToManyRoleServiceImpl   implements ManyToManyRoleService {

	/**
	 * 多对多角色
	 */
	@Resource
	private ManyToManyUserDao manyToManyUserDao;



	/**
	 * 注入dao
	 */
	@Resource
	private ManyToManyRoleDao manyToManyRoleDao;
	/**
	 * 初始化
	 */
	@Override
	public ManyToManyRoleDao initDao(){
		return manyToManyRoleDao;
	}


	/**
	 * 多对多重写insert
	 */
	@Override
	public int insert(ManyToManyRole manyToManyRole){
		int insert = manyToManyRoleDao.insert(manyToManyRole);
		String id = manyToManyRole.getUserId();
		if(id !=null && !"".equals(id)){
			String[] split = id.split(",");
			manyToManyRoleDao.insertRelation(manyToManyRole.getId().toString(),split);
		}
		return insert;

	}


	/**
	 * 多对多重写Update
	 */
	@Override
	public int update(ManyToManyRole manyToManyRole){
				Integer update = manyToManyRoleDao.update(manyToManyRole);
		String id = manyToManyRole.getUserId();
		if(id !=null && !"".equals(id)){
			ManyToManyUser d=new ManyToManyUser();
			d.setRoleId(manyToManyRole.getId().toString());
			manyToManyUserDao.deleteManyToManyUserByManyToManyRole(d);
			String[] split = id.split(",");
			manyToManyRoleDao.insertRelation(manyToManyRole.getId().toString(),split);
		}
		return update;

	}


	/**
	 * 级联查询(带分页) 多对多角色--多对多用户
	 */
	@Override
	public ManyToManyRole selectManyToManyUserAndManyToManyRole(ManyToManyRole manyToManyRole){
		manyToManyRole = this.selectAllByPaging(manyToManyRole);
		if(manyToManyRole!=null && manyToManyRole.getRows()!=null){
			manyToManyRole.getRows().forEach(t->{
				ManyToManyRole data= (ManyToManyRole) t;
				ManyToManyUser manyToManyUser=new ManyToManyUser();
				manyToManyUser.setRoleId(data.getId().toString());
				List<ManyToManyUser> datas=manyToManyUserDao.selectManyToManyUserByManyToManyRole(manyToManyUser);
				data.setManyToManyUserList(datas);
			});
		}
		return manyToManyRole;

	}


	/**
	 * 级联条件查询多对多用户--多对多角色
	 */
	@Override
	public List<ManyToManyRole> selectManyToManyUserAndManyToManyRoleByCondition(ManyToManyRole manyToManyRole){
		List<ManyToManyRole> datas = this.selectByCondition(manyToManyRole);
		if(datas!=null){
			datas.forEach(t->{
				ManyToManyUser manyToManyUser=new ManyToManyUser();
				manyToManyUser.setRoleId(t.getId().toString());
				List<ManyToManyUser> lists=manyToManyUserDao.selectManyToManyUserByManyToManyRole(manyToManyUser);
				t.setManyToManyUserList(lists);
			});
		}
		return datas;

	}


	/**
	 * 级联删除(根据主表删除) 多对多角色--多对多用户
	 */
	@Override
	public Integer deleteManyToManyUserAndManyToManyRole(ManyToManyRole manyToManyRole){
		ManyToManyUser manyToManyUser=new ManyToManyUser();
		manyToManyRole = manyToManyRoleDao.selectByPrimaryKey(manyToManyRole);
		manyToManyUser.setRoleId(manyToManyRole.getId().toString());
		manyToManyUserDao.deleteManyToManyUserByManyToManyRole(manyToManyUser);
		return manyToManyRoleDao.deleteByPrimaryKey(manyToManyRole);

	}


	/**
	 * 根据主表id查询外表所有数据(带分页)
	 */
	@Override
	public ManyToManyRole selectManyToManyRoleByManyToManyUser(ManyToManyRole t){
		PageHelper.startPage(t.getPage(), t.getPageSize());
		List<ManyToManyRole> lists = manyToManyRoleDao.selectManyToManyRoleByManyToManyUser(t);
		PageInfo pageInfo = new PageInfo(lists);
		t.setRows(lists);
		t.setTotal((new Long(pageInfo.getTotal())).intValue());
		return t;

	}



}
