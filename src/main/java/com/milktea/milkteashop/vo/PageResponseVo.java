package com.milktea.milkteashop.vo;

import java.util.List;

/**
 * 分页查询返回参数
 * @author caojia
 *
 */
public class PageResponseVo<T> extends ResponseHeader{

    private Long total;
    
    private List<T> rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
