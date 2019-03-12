package com.zengtengpeng.oneToOne.dao;

import com.zengtengpeng.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import com.zengtengpeng.oneToOne.bean.OneToOneClass;


/**
 *班级 dao
 */
@Mapper
public interface OneToOneClassDao  extends BaseDao<OneToOneClass>{




	/**
	 * 根据用户删除班级
	 */
	public Integer deleteOneToOneClassByOneToOneUser(OneToOneClass oneToOneClass);

}
