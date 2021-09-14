package com.logictrue.production.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.logictrue.common.core.annotation.Excel;
import com.logictrue.common.core.web.domain.BaseEntity;

/**
 * 生产订单对象 production_factoryplan
 * 
 * @author zhoumin
 * @date 2021-08-05
 */
public class ProductionFactoryplan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单id */
    private Long purchaseId;

    /** 申请单编号 */
    @Excel(name = "申请单编号")
    private String purchaseOrderno;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setPurchaseId(Long purchaseId) 
    {
        this.purchaseId = purchaseId;
    }

    public Long getPurchaseId() 
    {
        return purchaseId;
    }
    public void setPurchaseOrderno(String purchaseOrderno) 
    {
        this.purchaseOrderno = purchaseOrderno;
    }

    public String getPurchaseOrderno() 
    {
        return purchaseOrderno;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("purchaseId", getPurchaseId())
            .append("purchaseOrderno", getPurchaseOrderno())
            .append("remarks", getRemarks())
            .append("status", getStatus())
            .toString();
    }
}
