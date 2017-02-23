package com.istc.Service.EntityService;

import com.istc.Entities.Entity.Meeting;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.MeetingDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lurui on 2017/2/5 0005.
 */
@Service("meetingService")
@Transactional(rollbackFor = Exception.class)
public class MeetingService {
    @Resource
    private transient MeetingDAO meetingDAO;

    /**
     * 增删改查
     */
    public void add(Meeting meeting){
        meetingDAO.save(meeting);
    }

    public void delete(Meeting meeting){
        meetingDAO.delete(meeting.getMeetingID());
    }

    public void delete(Integer deptID, Integer meetingTimes){
        delete(new Meeting(deptID, meetingTimes));
    }
    public void edit(Meeting meeting){
        meetingDAO.edit(meeting);
    }

    public Meeting get(Meeting meeting){
        return meetingDAO.get(meeting.getMeetingID());
    }

    public Meeting get(Integer departmentId, Integer meetingTimes){
        return meetingDAO.get(departmentId, meetingTimes);
    }

    public List<Meeting> getMeetingsByDepartmetId(Integer departmentId){
        return meetingDAO.getByDepartmentId(departmentId);
    }

    /**
     * 获取最近的一次例会
     * @return 最近一次例会
     */
    public Meeting getLatest(Integer departmentId){
        return meetingDAO.getLatest(departmentId);
    }
    /**
     * 获取最早的一次例会
     * @return 最早一次例会
     */
    public Meeting getEarliest(){
        return meetingDAO.getEarliest();
    }

    public Integer countDepartmentMeetingsById(Integer departmentId){
        return meetingDAO.departmentMeetingsTotalCount(departmentId);
    }
}