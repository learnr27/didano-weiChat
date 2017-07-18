package cn.didano.weichat.dao;

import java.util.Date;

import cn.didano.weichat.model.Hand_classArriveAndLeaveInfo;

public interface Hand_attendanceMapper {
	int getDayArriveSchoolStatistic(Integer school_id, Date date);
	int getDayLeaveSchoolStatistic(Integer school_id, Date date);
	Hand_classArriveAndLeaveInfo getClassArriveSchoolNumList(Integer school_id, Date date);
	Hand_classArriveAndLeaveInfo getClassLeaveSchoolNumList(Integer school_id, Date date);
}
