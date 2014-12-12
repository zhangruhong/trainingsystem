package com.synnex.dao.impl;

import org.springframework.stereotype.Repository;

import com.synnex.dao.CourseDao;
import com.synnex.model.Course;

@Repository
public class CourseDaoImpl extends GenericDaoImpl<Course, Integer> implements CourseDao {

}
