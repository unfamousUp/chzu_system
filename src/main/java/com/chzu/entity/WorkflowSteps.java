package com.chzu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 步骤表
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkflowSteps {

    @TableId(value = "step_id", type = IdType.AUTO)
    private Integer stepId;

}
