package com.ruoyi.base.domain;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 基础数据设备对象 base_tunnel_device
 * 
 * @author ruoyi
 * @date 2021-02-08
 */
public class BaseTunnelDevice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备主键id */
    @ApiModelProperty(notes = "设备主键id")
    private Long deviceId;

    /** 设备类型id */
    @ApiModelProperty(notes = "设备类型id")
    private Long deviceTypeId;

    /** 设备具体编码 */
    @ApiModelProperty(notes = "设备具体编码")
    private String deviceCode;

    /** 分组：1，上行，0，下行 */
    @ApiModelProperty(notes = "工作状态，来源与设备类型状态表 ")
    private String deviceGroup;

    /** 工作状态，来源与设备类型状态表 */
    @ApiModelProperty(notes = "工作状态，来源与设备类型状态表 ")
    private String workStatus;

    /** 设备状态，1，正常，0，删除 */
    @ApiModelProperty(notes = "设备状态，1，正常，0，删除")
    private String status;

    public void setDeviceId(Long deviceId) 
    {
        this.deviceId = deviceId;
    }

    public Long getDeviceId() 
    {
        return deviceId;
    }
    public void setDeviceTypeId(Long deviceTypeId) 
    {
        this.deviceTypeId = deviceTypeId;
    }

    public Long getDeviceTypeId() 
    {
        return deviceTypeId;
    }
    public void setDeviceCode(String deviceCode) 
    {
        this.deviceCode = deviceCode;
    }

    public String getDeviceCode() 
    {
        return deviceCode;
    }
    public void setDeviceGroup(String deviceGroup) 
    {
        this.deviceGroup = deviceGroup;
    }

    public String getDeviceGroup() 
    {
        return deviceGroup;
    }
    public void setWorkStatus(String workStatus) 
    {
        this.workStatus = workStatus;
    }

    public String getWorkStatus() 
    {
        return workStatus;
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
            .append("deviceId", getDeviceId())
            .append("deviceTypeId", getDeviceTypeId())
            .append("deviceCode", getDeviceCode())
            .append("deviceGroup", getDeviceGroup())
            .append("workStatus", getWorkStatus())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
