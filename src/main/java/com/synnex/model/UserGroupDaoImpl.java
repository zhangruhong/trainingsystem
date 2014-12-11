package com.synnex.model;

import com.synnex.dao.UserGroupDao;
import com.synnex.dao.impl.GenericDaoImpl;

/**
 * 继承通用的方法，如有必要可以覆盖相应的方法来达到特殊处理的功能
 * 
 * @author Davisz
 *
 */
public class UserGroupDaoImpl extends GenericDaoImpl<Usergroup, Integer> implements UserGroupDao {
}
