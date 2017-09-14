package cn.didano.weichat.Service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.didano.weichat.dao.Hand_benchmarkMapper;
import cn.didano.weichat.dao.Hand_student_detectionMapper;
import cn.didano.weichat.dao.Tb_studentRecordMapper;
import cn.didano.weichat.dao.Tb_student_selectMapper;
import cn.didano.weichat.model.Tb_benchmark;
import cn.didano.weichat.model.Tb_studentRecord;
import cn.didano.weichat.model.Tb_studentRecordExample;
import cn.didano.weichat.model.Tb_student_detection;

/*import cn.didano.base.dao.Hand_benchmarkMapper;
import cn.didano.base.dao.Hand_student_detectionMapper;
import cn.didano.base.dao.Tb_benchmarkMapper;
import cn.didano.base.dao.Tb_studentRecordMapper;
import cn.didano.base.dao.Tb_student_detectionMapper;
import cn.didano.base.dao.Tb_student_selectMapper;
import cn.didano.base.dao.Vd_auth_time_controlMapper;
import cn.didano.base.exception.DBExceptionEnums;
import cn.didano.base.exception.ServiceException;
import cn.didano.base.model.Tb_benchmark;
import cn.didano.base.model.Tb_studentRecord;
import cn.didano.base.model.Tb_studentRecordExample;
import cn.didano.base.model.Tb_student_detection;
import cn.didano.base.model.Vd_auth_time_control;
import cn.didano.base.model.Vd_auth_time_controlExample;
import cn.didano.base.util.TimeUtil;*/


/**
 * 频道的时间控制服务
 * @author stephen
 */
@Service
public class AuthTimeControlService {
	@Autowired
	private Tb_studentRecordMapper studentRecordMapper;
	
	@Autowired
	private Tb_student_selectMapper selectMapper;
	
	@Autowired
	private Hand_benchmarkMapper hand_benchmarkMapper;
	
	@Autowired
	private Hand_student_detectionMapper handMapper;

	/**
	 * 查询学生的成长记录
	 * 调用
	 * @param id
	 * @return
	 */
	public List<Tb_student_detection> selectBystudent(Integer id){
		return selectMapper.selectByPrimaryKey_student(id);
	}
	/**
	 * 查询学生
	 * 调用
	 * @param id
	 * @return
	 */
	public List<Tb_studentRecord> selectstudent(int id){
		Tb_studentRecordExample detection=new Tb_studentRecordExample();
		Tb_studentRecordExample.Criteria cri=detection.createCriteria();
		cri.andIdEqualTo(id);
		return studentRecordMapper.selectByExample(detection);
	}
	/**
	 * 查询同龄人的平均身高
	 * 调用selectByPrimaryKey
	 * @param id
	 * @return
	 */
	public List<Tb_student_detection> select_student_detection(@Param("birthday") Date birthday,  @Param("birthday1")Date birthday1){
		return handMapper.select_student_detection(birthday,birthday1);
	}
	/**
	 * 查询国家儿童身高体重的标准
	 * 调用selectByPrimaryKey
	 * @param id
	 * @return
	 */
	public List<Tb_benchmark> selectByHeightAddWeight(Tb_benchmark tb){
		return hand_benchmarkMapper.selectByPrimaryKey(tb);
	}
}
