package com.chzu.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.chzu.entity.SensitiveWord;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义的读取监听器
 */
public class ExcelListener extends AnalysisEventListener<SensitiveWord> {

    List<SensitiveWord> datas = new ArrayList<>();

    @Override
    public void invoke(SensitiveWord data, AnalysisContext context) {
        // 处理每行数据的读取逻辑
        datas.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 所有数据解析完成后的逻辑
    }

    public List<SensitiveWord> getDatas() {
        return datas;
    }

}
