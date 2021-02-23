package com.ruoyi.base.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 基础数据设备对象 base_tunnel_device_type
 * 
 * @author ruoyi
 * @date 2021-02-07
 */
public class BaseTunnelDeviceType extends BaseEntity
{

    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long deviceTypeId;

    /** 隧道id */
    private Long tunnelId;

    /** 设备类型编码 */
    private String deviceTypeCode;

    /** 该隧道设备间距 */
    private String deviceSpace;

    /** 设备类型名称 */
    private String deviceTypeName;

    /** 设备类型图标地址 */
    private String deviceImageAddress;

    /** 设备起始位置 */
    private String startPosition;

    /** 1:正常，0，删除 */
    private String status;

    public void setDeviceTypeId(Long deviceTypeId)
    {
        this.deviceTypeId = deviceTypeId;
    }

    public Long getDeviceTypeId()
    {
        return deviceTypeId;
    }
    public void setTunnelId(Long tunnelId)
    {
        this.tunnelId = tunnelId;
    }

    public Long getTunnelId()
    {
        return tunnelId;
    }
    public void setDeviceTypeCode(String deviceTypeCode)
    {
        this.deviceTypeCode = deviceTypeCode;
    }

    public String getDeviceTypeCode()
    {
        return deviceTypeCode;
    }
    public void setDeviceSpace(String deviceSpace)
    {
        this.deviceSpace = deviceSpace;
    }

    public String getDeviceSpace()
    {
        return deviceSpace;
    }
    public void setDeviceTypeName(String deviceTypeName)
    {
        this.deviceTypeName = deviceTypeName;
    }

    public String getDeviceTypeName()
    {
        return deviceTypeName;
    }
    public void setDeviceImageAddress(String deviceImageAddress)
    {
        this.deviceImageAddress = deviceImageAddress;
    }

    public String getDeviceImageAddress()
    {
        return deviceImageAddress;
    }
    public void setStartPosition(String startPosition)
    {
        this.startPosition = startPosition;
    }

    public String getStartPosition()
    {
        return startPosition;
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
                .append("deviceTypeId", getDeviceTypeId())
                .append("tunnelId", getTunnelId())
                .append("deviceTypeCode", getDeviceTypeCode())
                .append("deviceSpace", getDeviceSpace())
                .append("deviceTypeName", getDeviceTypeName())
                .append("deviceImageAddress", getDeviceImageAddress())
                .append("startPosition", getStartPosition())
                .append("status", getStatus())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .toString();
    }
}
