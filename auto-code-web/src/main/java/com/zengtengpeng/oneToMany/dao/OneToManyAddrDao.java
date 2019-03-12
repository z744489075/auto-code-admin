package com.zengtengpeng.oneToMany.dao;

import com.zengtengpeng.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import com.zengtengpeng.oneToMany.bean.OneToManyAddr;


/**
 *一对多收货地址 dao
 */
@Mapper
public interface OneToManyAddrDao  extends BaseDao<OneToManyAddr>{




	/**
	 * 根据一对多用户删除一对多收货地址
	 */
	public Integer deleteOneToManyAddrByOneToManyUser(OneToManyAddr oneToManyAddr);

}
