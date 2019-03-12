package com.zengtengpeng.oneToOne.service.impl;

import com.zengtengpeng.oneToOne.dao.OneToOneUserDao;
import com.zengtengpeng.oneToOne.bean.OneToOneUser;
import com.zengtengpeng.oneToOne.bean.OneToOneClass;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zengtengpeng.oneToOne.dao.OneToOneClassDao;
import com.zengtengpeng.oneToOne.service.OneToOneClassService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;


/**
 *班级 serverImpl
 */
@Service
@Transactional
public class OneToOneClassServiceImpl   implements OneToOneClassService {

	/**
	 * 班级
	 */
	@Resource
	private OneToOneUserDao oneToOneUserDao;



	/**
	 * 注入dao
	 */
	@Resource
	private OneToOneClassDao oneToOneClassDao;
	/**
	 * 初始化
	 */
	@Override
	public OneToOneClassDao initDao(){
		return oneToOneClassDao;
	}


	/**
	 * 级联查询(带分页) 用户--班级
	 */
	@Override
	public OneToOneClass selectOneToOneUserAndOneToOneClass(OneToOneClass oneToOneClass){
		oneToOneClass = this.selectAllByPaging(oneToOneClass);
		if(oneToOneClass!=null && oneToOneClass.getRows()!=null){
			oneToOneClass.getRows().forEach(t->{
				OneToOneClass data= (OneToOneClass) t;
				OneToOneUser oneToOneUser=new OneToOneUser();
				oneToOneUser.setId(data.getUserId());
				data.setOneToOneUser(oneToOneUserDao.selectByPrimaryKey(oneToOneUser));
			});
		}
		return oneToOneClass;

	}


	/**
	 * 级联条件查询用户--班级
	 */
	@Override
	public List<OneToOneClass> selectOneToOneUserAndOneToOneClassByCondition(OneToOneClass oneToOneClass){
		List<OneToOneClass> datas = this.selectByCondition(oneToOneClass);
		if(datas!=null){
			datas.forEach(t->{
				OneToOneUser oneToOneUser=new OneToOneUser();
				oneToOneUser.setId(t.getUserId());
				t.setOneToOneUser(oneToOneUserDao.selectByPrimaryKey(oneToOneUser));
			});
		}
		return datas;

	}


	/**
	 * 级联删除(根据主表删除) 用户--班级
	 */
	@Override
	public Integer deleteOneToOneUserAndOneToOneClass(OneToOneClass oneToOneClass){
		oneToOneClass = oneToOneClassDao.selectByPrimaryKey(oneToOneClass);
		if(oneToOneClass!=null){
			OneToOneUser oneToOneUser=new OneToOneUser();
			oneToOneUser.setId(oneToOneClass.getUserId());
			oneToOneUserDao.deleteByPrimaryKey(oneToOneUser);
		}
		return oneToOneClassDao.deleteByPrimaryKey(oneToOneClass);

	}



}
