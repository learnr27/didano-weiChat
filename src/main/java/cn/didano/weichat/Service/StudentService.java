package cn.didano.weichat.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.didano.weichat.constant.DeletedType;
import cn.didano.weichat.constant.StatusType;
import cn.didano.weichat.dao.Hand_student4MailListHasParentsMapper;
import cn.didano.weichat.dao.Tb_schoolparentMapper;
import cn.didano.weichat.dao.Tb_studentMapper;
import cn.didano.weichat.dao.Tb_student_away_recordMapper;
import cn.didano.weichat.dao.Tb_student_detection4photoWallMapper;
import cn.didano.weichat.dao.Tb_student_parentMapper;
import cn.didano.weichat.exception.DBExceptionEnums;
import cn.didano.weichat.exception.ServiceException;
import cn.didano.weichat.model.Hand_parentAndStudentId;
import cn.didano.weichat.model.Hand_student4MailListHasParents;
import cn.didano.weichat.model.Hand_student4MailListHasParentsExample;
import cn.didano.weichat.model.Hand_studentAwayData;
import cn.didano.weichat.model.Tb_schoolparent4;
import cn.didano.weichat.model.Tb_student;
import cn.didano.weichat.model.Tb_studentExample;
import cn.didano.weichat.model.Tb_student_away_record;
import cn.didano.weichat.model.Tb_student_detection4photoWall;
import cn.didano.weichat.model.Tb_student_detection4photoWallExample;
import cn.didano.weichat.model.Tb_student_parent;
import cn.didano.weichat.model.Tb_student_parentExample;

@Service
public class StudentService {

	@Autowired
	private Tb_studentMapper studentMapper;
	
	@Autowired
	private Tb_student_detection4photoWallMapper photoWallMapper;
	@Autowired
	private Hand_student4MailListHasParentsMapper newstudentMapper;
	@Autowired
	private Tb_student_parentMapper studentparentMapper;
	@Autowired
	private Tb_schoolparentMapper schoolparentMapper;
	@Autowired
	private Tb_student_away_recordMapper awayMapper;


	/**
	 * 根据家长id查询小朋友
	 */
	public Tb_student selectStudentByParentId(Integer parentId){
		Tb_student_parentExample condition = new Tb_student_parentExample();
		Tb_student_parentExample.Criteria criteria = condition.createCriteria();
		// 对于已经deleted=1的不显示 禁用不显示
		criteria.andDeletedEqualTo(DeletedType.N0_DELETED.getValue());
		criteria.andParentIdEqualTo(parentId);
System.out.println(studentparentMapper.selectByExample(condition).size());
		Tb_student_parent parent = studentparentMapper.selectByExample(condition).get(0);
			
		return studentMapper.selectByPrimaryKey(parent.getStudentId());
	}
	/**
	 * 根据时间查找学生的接送报告
	 */
	public Tb_student_away_record selectStudentAwayRecordById(Hand_studentAwayData data){
		if(awayMapper.selectStudentAwayRecordById(data).size()!=0){
		return awayMapper.selectStudentAwayRecordById(data).get(0);
		}else{
			return null;
		}
		
	}
	/**
	 * 查找学生的晨检报告
	 */
	public Tb_student_detection4photoWall selectStudentDectection(Hand_studentAwayData data){
		return photoWallMapper.selectStudentDectection(data).get(0);
	}
	/**
	 * 
	 */
	/**
	 * 通过id查询
	 */
	public List<Tb_student_detection4photoWall> selectById(Integer student_id) {

		Tb_student_detection4photoWallExample condition = new Tb_student_detection4photoWallExample();
		Tb_student_detection4photoWallExample.Criteria criteria = condition.createCriteria();
		criteria.andStudentIdEqualTo(student_id);
		condition.setOrderByClause("created");
		criteria.andOrgImgUrlNotLike("");
		return photoWallMapper.selectByExample(condition);
	}
	
	/**
	 * 通过id查询student Info
	 */
	public Tb_student selectStudentById(Integer id) {
		Tb_studentExample condition=new Tb_studentExample();
		Tb_studentExample.Criteria criteria=condition.createCriteria();
		criteria.andIdEqualTo(id);
		criteria.andDeletedEqualTo(false);
		Tb_student selectByPrimaryKey = studentMapper.selectByPrimaryKey(id);
		return selectByPrimaryKey;
	}

	/**
	 * 查找所有学生
	 */
	public List<Hand_student4MailListHasParents> findAll() {
		Hand_student4MailListHasParentsExample condition = new Hand_student4MailListHasParentsExample();
		Hand_student4MailListHasParentsExample.Criteria criteria = condition.createCriteria();
		// 对于已经deleted=1的不显示 禁用不显示
		criteria.andDeletedEqualTo(DeletedType.N0_DELETED.getValue());
		criteria.andStatusEqualTo(StatusType.IS_USE.getIndex());
		return newstudentMapper.selectByExample(condition);
	}

	/**
	 * 插入学生
	 * 
	 * @param record
	 * @return 有值，id ,否则返回-1
	 */
	public int insertStudentSelective(Hand_student4MailListHasParents record) {
		if (record == null)
			throw new ServiceException(DBExceptionEnums.ERROR_DB_CONTENT_NULL);
		return newstudentMapper.insertSelective(record);
	}

	/**
	 * 插入学生父母关系表
	 * 
	 * @param record
	 * @return 有值，id ,否则返回-1
	 */
	public int insertStudentParentSelective(Tb_student_parent record) {
		if (record == null)
			throw new ServiceException(DBExceptionEnums.ERROR_DB_CONTENT_NULL);
		return studentparentMapper.insertSelective(record);
	}

	/**
	 * 插入父母
	 * 
	 * @param record
	 * @return 有值，id ,否则返回-1
	 */
	public int insertParentSelective(Tb_schoolparent4 record) {
		if (record == null)
			throw new ServiceException(DBExceptionEnums.ERROR_DB_CONTENT_NULL);
		return schoolparentMapper.insertSelective(record);
	}
	/**
	 * 根据家长电话号码查找他的所有孩子
	 */
	public List<Tb_student> getStudentListByParentPhone(String parentPhone){
		return studentMapper.getStudentListByParentPhone(parentPhone);
	}
	
	/**
	 * 根据父母id学生ID查找
	 */
	public Tb_student getStudentByParentId(Hand_parentAndStudentId parentAndStudent){
		return studentMapper.getStudentByParentId(parentAndStudent);
	}
	
	/**
	 * 编辑学生信息
	 */
	public int updateStudentSetting(Tb_student record){
		if (record == null)
			throw new ServiceException(DBExceptionEnums.ERROR_DB_CONTENT_NULL);
		return studentMapper.updateByPrimaryKeySelective(record);
	}
}
