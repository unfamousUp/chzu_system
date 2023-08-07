package com.chzu.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.chzu.entity.Events;
import com.chzu.entity.SensitiveWord;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义的读取监听器
 */
public class ExcelListener extends AnalysisEventListener<Events> {

    List<Events> datas = new ArrayList<>();

    @Override
    public void invoke(Events data, AnalysisContext context) {
        // 处理每行数据的读取逻辑
        datas.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 所有数据解析完成后的逻辑
    }

    public List<Events> getDatas() {
        return datas;
    }


}
