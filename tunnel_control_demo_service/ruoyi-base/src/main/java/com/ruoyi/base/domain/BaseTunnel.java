package com.ruoyi.base.domain;

import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 基础数据设备对象 base_tunnel
 * 
 * @author ruoyi
 * @date 2021-02-07
 */
public class BaseTunnel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 隧道id */
    @ApiParam("隧道id")
    private Long tunnelId;

    /** 隧道名字 */
    @ApiParam("隧道名字")
    private String tunnelName;

    /** 隧道位置 */
    @ApiParam("隧道位置")
    private String tunnelPosition;

    /** 起始标志 */
    @ApiParam("起始标志")
    private String startSign;

    /** 隧道长度 */
    @ApiParam("隧道长度")
    private String tunnelLength;

    /** 隧道详情 */
    @ApiParam("隧道详情")
    private String tunnelDetail;

    /** 隧道状态 */
    private String status;

    public void setTunnelId(Long tunnelId) 
    {
        this.tunnelId = tunnelId;
    }

    public Long getTunnelId() 
    {
        return tunnelId;
    }
    public void setTunnelName(String tunnelName) 
    {
        this.tunnelName = tunnelName;
    }

    public String getTunnelName() 
    {
        return tunnelName;
    }
    public void setTunnelPosition(String tunnelPosition) 
    {
        this.tunnelPosition = tunnelPosition;
    }

    public String getTunnelPosition() 
    {
        return tunnelPosition;
    }
    public void setStartSign(String startSign) 
    {
        this.startSign = startSign;
    }

    public String getStartSign() 
    {
        return startSign;
    }
    public void setTunnelLength(String tunnelLength) 
    {
        this.tunnelLength = tunnelLength;
    }

    public String getTunnelLength() 
    {
        return tunnelLength;
    }
    public void setTunnelDetail(String tunnelDetail) 
    {
        this.tunnelDetail = tunnelDetail;
    }

    public String getTunnelDetail() 
    {
        return tunnelDetail;
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
            .append("tunnelId", getTunnelId())
            .append("tunnelName", getTunnelName())
            .append("tunnelPosition", getTunnelPosition())
            .append("startSign", getStartSign())
            .append("tunnelLength", getTunnelLength())
            .append("tunnelDetail", getTunnelDetail())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
