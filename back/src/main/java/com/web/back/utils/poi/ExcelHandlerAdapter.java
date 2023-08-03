package com.web.back.utils.poi;

/**
 * @author by hongdou
 * @date 2023/8/3.
 * @DESC:
 */
public interface ExcelHandlerAdapter {
    /**
     * 格式化
     *
     * @param value 单元格数据值
     * @param args excel注解args参数组
     *
     * @return 处理后的值
     */
    Object format(Object value, String[] args);
}
