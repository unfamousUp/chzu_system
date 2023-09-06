package com.chzu.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chzu.dto.AddEventsInfoDTO;
import com.chzu.dto.UpdateEventsInfoDTO;
import com.chzu.entity.Events;
import com.chzu.utils.R;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
@TableName("events")
public interface EventsMapper extends BaseMapper<Events> {

    // List<Events> getEventsWithSteps(@Param("eventsId") Integer eventsId);

    /**
     * 根据网信办用户id获取事件信息
     * @param userId
     * @return
     */
    List<Events> getEventsByAssignedUserId(@Param("userId")Integer userId);

    /**
     * 根据机构用户id查询事件信息
     * @param userId
     * @return
     */
    List<Events> getEventsByProcessUserId(@Param("userId")Integer userId);

    /**
     * 根据机构名称获取网信办用户待办事件信息
     * @param eventStatus
     * @param userId
     * @param orgName
     * @return
     */
    // @Select("SELECT e.* FROM events e " +
    //         "LEFT JOIN organizations org ON e.assigned_to_organization = org.org_id " +
    //         "WHERE e.event_status = #{eventStatus} " +
    //         "AND e.assigned_by_user = #{userId} " +
    //         "AND org.org_name LIKE CONCAT('%', #{orgName}, '%')")
    List<Events> getToDoEventsByOrgNameForAdminUser(
            @Param("eventStatus") String eventStatus,
            @Param("userId") Integer userId,
            @Param("orgName") String orgName
    );

    /**
     * 根据网信办用户id获取网信办用户待办事件信息
     * @param userId
     * @param eventStatus
     * @return
     */
    List<Events> getToDoEventsForAdminUser(@Param("userId")Integer userId,@Param("eventStatus")String eventStatus);

    /**
     * 根据机构用户id获取机构用户待办事件信息
     * @param userId
     * @param eventStatusInstitution
     * @return
     */
    List<Events> getToDoEventsForInstitutionUser(@Param("userId")Integer userId,@Param("eventStatusInstitution")String eventStatusInstitution);

    /**
     * 根据网信办用户id获取网信办用户在办事件信息
     * @param userId
     * @param eventStatus
     * @return
     */
    List<Events> getAtToDoEventsForAdminUser(@Param("userId")Integer userId,@Param("eventStatus")String eventStatus,@Param("processStatus") String processStatus);

    /**
     * 为网信办更新待办事件信息
     * @param updateEventsInfoDTO
     * @return
     */
    Integer updateEventsInfoForAdminUser(UpdateEventsInfoDTO updateEventsInfoDTO);

    /**
     * 为机构用户更新待办事件信息
     * @param updateEventsInfoDTO
     * @return
     */
    Integer updateEventsInfoForInstitutionUser(UpdateEventsInfoDTO updateEventsInfoDTO);

    /**
     * 重置待办事件信息
     * @return
     */
    Integer resetToDoEventsInfo(UpdateEventsInfoDTO updateEventsInfoDTO);

    /**
     * 撤销事件信息
     * @return
     */
    Integer rollBackEventsInfo(UpdateEventsInfoDTO updateEventsInfoDTO);

    /**
     * 添加事件信息
     * @return
     */
    Integer addEventsInfo(Events events);

    /**
     * 添加事件信息
     * @return
     */
    Integer addEventsInfo(AddEventsInfoDTO addEventsInfoDTO);
}
