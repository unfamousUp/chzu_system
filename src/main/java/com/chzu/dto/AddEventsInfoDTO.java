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

    // public AddEventsInfoDTO(Integer orgId, String emergencyDegree, String originDepartment, String datasource, String illegalContent){
    //     super(orgId,emergencyDegree,datasource,illegalContent);
    //     this.orgId = orgId;
    //     this.originDepartment = originDepartment;
    // }

    public void setEventsInfo(){
        setAssignedToOrganization(orgId);
        setEventStatus("待办");
        setProcessStatus("待处理");
        setEventType("待通告");
        setEventStatusInstitution("待通知");
    }

    @Override
    public String toString() {
        return super.toString() + "AddEventsInfoDTO{" +
                "orgId=" + orgId +
                ", originDepartment='" + originDepartment + '\'' +
                '}';
    }


}
