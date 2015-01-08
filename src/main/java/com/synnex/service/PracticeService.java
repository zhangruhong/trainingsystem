package com.synnex.service;

import java.util.List;

import com.synnex.model.PageResult;
import com.synnex.model.Practice;

/**
 * 
 * @author hiramh
 *
 */
public interface PracticeService {
	/**
	 * 新建一个Practice对象
	 * 
	 * @param practice
	 */
	public void addPractice(Practice practice);

	/**
	 * 批量新建Practice对象（应用场景-讲师上传作业后自动为用户添加作业并发邮件通知）
	 * 
	 * @param courseId
	 */
	public void addPractices(Integer courseId);

	/**
	 * 删除/冻结一个用户（应用场景-培训学员离职，或者不再参加培训等）
	 * 
	 * @param practice
	 */
	public void deletePractice(Practice practice);

	/**
	 * 批量删除/冻结/转存一批用户（应用场景-一批学员毕业）
	 * 
	 * @param practices
	 */
	public void deletePractices(List<Practice> practices);

	/**
	 * 更新一个学员的信息(讲师维护)
	 * 
	 * @param practice
	 */
	public void updatePractice(Practice practice);

	/**
	 * 批量更新学员信息（应用场景-新建学员时勾选多个然后一起分配到某一部门）
	 * 
	 * @param practices
	 */
	public void updatePractices(List<Practice> practices);

	/**
	 * 根据编号查学员
	 * 
	 * @param id
	 *            （此编号为系统内部编号，对所有人透明）
	 * @return
	 */
	public Practice getPractice(Integer id);

	/**
	 * 根据courseId和traineeId找practice
	 * 
	 * @param courseId
	 * @param traineeId
	 */
	public Practice findPracticeByCourseAndUser(int courseId, int traineeId);

	/**
	 * 根据courseId找practice
	 * 
	 * @param courseId
	 */
	public List<Practice> findPracticeByCourse(int courseId);

	/**
	 * 根据loginname找practice
	 * 
	 * @param loginname
	 * @return
	 */
	public List<Practice> findPracticeByLoginname(String loginname);

	/**
	 * 根据trainee id找practice
	 * 
	 * @param userId
	 * @return
	 */
	public List<Practice> findPracticeByUser(int traineeId);

	/**
	 * 根据practice给trainer发送作业完成的邮件
	 * 
	 * @param id
	 */
	public void sendPracticeMailToTrainer(int practiceId);

	/**
	 * 根据trainee id找practice，带分页
	 * 
	 * @param page
	 * @param pagesize
	 * @param traineeId
	 * @param courseId TODO
	 * @return
	 */
	public PageResult<Practice> listPracticePageByTrainee(Integer page, int pagesize, Integer traineeId, Integer courseId);

	/**
	 * 根据course id找practice，带分页
	 * 
	 * @param page
	 * @param pagesize
	 * @param id
	 * @return
	 */
	public PageResult<Practice> listPracticePageByCourse(Integer page, int pagesize, int courseId);
}
