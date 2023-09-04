package com.chzu.dto;

import com.chzu.entity.Events;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddEventsInfoDTO extends Events {

    // 机构id
    private Integer orgId;

    private String originDepartment;

    public AddEventsInfoDTO(Integer orgId, String emergencyDegree, String originDepartment, String datasource, String illegalContent){
        super(emergencyDegree,datasource,illegalContent);
        this.orgId = orgId;
        this.originDepartment = originDepartment;
    }

    @Override
    public String toString() {
        return super.toString() + "AddEventsInfoDTO{" +
                "orgId=" + orgId +
                ", originDepartment='" + originDepartment + '\'' +
                '}';
    }
}
