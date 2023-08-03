package com.chzu.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.chzu.entity.Organizations;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
@TableName("organizations")
public interface OrganizationsMapper {

    Organizations getOrgInfoByOrgId(@Param("orgId") Integer orgId);

}
