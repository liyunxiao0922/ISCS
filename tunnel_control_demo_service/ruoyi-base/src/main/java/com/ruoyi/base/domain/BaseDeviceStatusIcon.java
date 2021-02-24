package com.ruoyi.base.domain;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 基础数据设备对象 base_device_status_icon
 * 
 * @author ruoyi
 * @date 2021-02-08
 */
public class BaseDeviceStatusIcon extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 图标id */
    @ApiModelProperty(notes = "图标id")
    private Long statusIconId;

    /** 设备类型id */
    @ApiModelProperty(notes = "设备类型id")
    private Long deviceTypeId;

    /** 图标地址 */
    @ApiModelProperty(notes = "图标地址")
    private String imageAddress;

    /** 图标code */
    @ApiModelProperty(notes = "图标code")
    private String iconCode;

    /** 图标描述 */
    @ApiModelProperty(notes = "图标描述")
    private String iconDescription;

    /** 1:正常，0，删除 */
    @ApiModelProperty(notes = "1:正常，0，删除")
    private String status;

    public void setStatusIconId(Long statusIconId) 
    {
        this.statusIconId = statusIconId;
    }

    public Long getStatusIconId() 
    {
        return statusIconId;
    }
    public void setDeviceTypeId(Long deviceTypeId) 
    {
        this.deviceTypeId = deviceTypeId;
    }

    public Long getDeviceTypeId() 
    {
        return deviceTypeId;
    }
    public void setImageAddress(String imageAddress) 
    {
        this.imageAddress = imageAddress;
    }

    public String getImageAddress() 
    {
        return imageAddress;
    }
    public void setIconCode(String iconCode) 
    {
        this.iconCode = iconCode;
    }

    public String getIconCode() 
    {
        return iconCode;
    }
    public void setIconDescription(String iconDescription) 
    {
        this.iconDescription = iconDescription;
    }

    public String getIconDescription() 
    {
        return iconDescription;
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
            .append("statusIconId", getStatusIconId())
            .append("deviceTypeId", getDeviceTypeId())
            .append("imageAddress", getImageAddress())
            .append("iconCode", getIconCode())
            .append("iconDescription", getIconDescription())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
