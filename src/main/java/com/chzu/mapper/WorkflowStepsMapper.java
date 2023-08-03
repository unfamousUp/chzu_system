package com.chzu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chzu.entity.WorkflowSteps;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WorkflowStepsMapper extends BaseMapper<WorkflowSteps> {

    @Select("SELECT * FROM workflow_steps WHERE events_id = #{eventsId}")
    List<WorkflowSteps> getStepsByEventId(Integer eventsId);

}
