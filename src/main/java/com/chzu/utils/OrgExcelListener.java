package com.chzu.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.chzu.entity.Events;
import com.chzu.entity.Organizations;

import java.util.ArrayList;
import java.util.List;

public class OrgExcelListener extends AnalysisEventListener<Organizations> {
    List<Organizations> datas = new ArrayList<>();

    @Override
    public void invoke(Organizations data, AnalysisContext context) {
        // 处理每行数据的读取逻辑
        datas.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 所有数据解析完成后的逻辑
    }

    public List<Organizations> getDatas() {
        return datas;
    }
}
