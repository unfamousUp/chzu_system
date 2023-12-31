package com.chzu.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chzu.dto.AddEventsInfoDTO;
import com.chzu.dto.UpdateEventsInfoDTO;
import com.chzu.entity.Events;
import com.chzu.utils.R;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
@TableName("events")
public interface EventsMapper extends BaseMapper<Events> {

    // List<Events> getEventsWithSteps(@Param("eventsId") Integer eventsId);

    /**
     * 根据网信办用户id获取事件信息
     *
     * @param userId
     * @return
     */
    List<Events> getEventsByAssignedUserId(@Param("userId") Integer userId);

    /**
     * 根据机构用户id查询事件信息
     *
     * @param userId
     * @return
     */
    List<Events> getEventsByProcessUserId(@Param("userId") Integer userId);

    /**
     * 动态查询待办事件信息
     * @param eventStatus
     * @param userId
     * @param orgName
     * @param illegalContent
     * @param processStatus
     * @return
     */
    List<Events> getWaitToEventsInfoByDynamicQueryForAdminUser(
            @Param("eventStatus") String eventStatus,
            @Param("userId") Integer userId,
            @Param("orgName") String orgName,
            @Param("illegalContent") String illegalContent,
            @Param("processStatus") String processStatus
    );

    List<Events> getWaitToEventsInfoByDynamicQueryForInstitutionUser(
            @Param("eventStatusInstitution") String eventStatusInstitution,
            @Param("userId") Integer userId,
            @Param("orgName") String orgName,
            @Param("illegalContent") String illegalContent,
            @Param("processStatus") String processStatus
    );

    /**
     * 根据网信办用户id获取网信办用户待办事件信息
     *
     * @param userId
     * @param eventStatus
     * @return
     */
    List<Events> getToDoEventsForAdminUser(@Param("userId") Integer userId, @Param("eventStatus") String eventStatus);

    /**
     * 根据机构用户id获取机构用户待办事件信息
     *
     * @param userId
     * @param eventStatusInstitution
     * @return
     */
    List<Events> getToDoEventsForInstitutionUser(@Param("userId") Integer userId, @Param("eventStatusInstitution") String eventStatusInstitution);

    /**
     * 根据网信办用户id获取网信办用户在办事件信息
     *
     * @param userId
     * @param eventStatus
     * @return
     */
    List<Events> getAtToDoEventsForAdminUser(@Param("userId") Integer userId, @Param("eventStatus") String eventStatus, @Param("processStatus") String processStatus,@Param("processStatus2") String processStatus2);

    List<Events> getAtToDoEventsForInstitutionUser(@Param("userId") Integer userId, @Param("eventStatusInstitution") String eventStatusInstitution);


    List<Events> getDoneToEventsForInstitutionUser(@Param("userId") Integer userId, @Param("eventStatusInstitution") String eventStatusInstitution, @Param("processStatus") String processStatus);

    /**
     * 根据网信办用户id获取网信办用户办结事件信息
     *
     * @param userId
     * @param eventStatus
     * @param processStatus
     * @return
     */
    List<Events> getDoneToEventsForAdminUser(@Param("userId") Integer userId, @Param("eventStatus") String eventStatus, @Param("processStatus") String processStatus);

    /**
     * 为网信办更新待办事件信息
     *
     * @param updateEventsInfoDTO
     * @return
     */
    Integer updateEventsInfoForAdminUser(UpdateEventsInfoDTO updateEventsInfoDTO);

    /**
     * 为机构用户更新待办事件信息
     *
     * @param updateEventsInfoDTO
     * @return
     */
    Integer updateEventsInfoForInstitutionUser(UpdateEventsInfoDTO updateEventsInfoDTO);

    /**
     * 重置待办事件信息
     *
     * @return
     */
    Integer resetToDoEventsInfo(UpdateEventsInfoDTO updateEventsInfoDTO);

    /**
     * 撤销事件信息
     *
     * @return
     */
    Integer rollBackEventsInfo(UpdateEventsInfoDTO updateEventsInfoDTO);

    /**
     * 添加事件信息
     *
     * @return
     */
    Integer addEventsInfo(Events events);

    /**
     * 添加事件信息
     *
     * @return
     */
    Integer addEventsInfo(AddEventsInfoDTO addEventsInfoDTO);

    Integer updateTest(@Param("createTime")Date createTime);

    Date getCreateTimeDate();

}
