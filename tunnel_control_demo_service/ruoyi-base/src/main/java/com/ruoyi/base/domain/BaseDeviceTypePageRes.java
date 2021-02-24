package com.ruoyi.base.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseDeviceTypePageRes implements Serializable {
    private static final long serialVersionUID = 8097585273775687104L;

    /** 主键id */
    @ApiModelProperty(notes = "主键id")
    private Long deviceTypeId;

    /** 隧道id */
    @ApiModelProperty(notes = "隧道id")
    private Long tunnelId;

    /** 设备类型编码 */
    @ApiModelProperty(notes = "设备类型编码")
    private String deviceTypeCode;

    /** 该隧道设备间距 */
    @ApiModelProperty(notes = "该隧道设备间距")
    private String deviceSpace;

    /** 设备类型名称 */
    @ApiModelProperty(notes = "设备类型名称")
    private String deviceTypeName;

    /** 设备类型图标地址 */
    @ApiModelProperty(notes = "设备类型图标地址")
    private String deviceImageAddress;

    /** 设备起始位置 */
    @ApiModelProperty(notes = "设备起始位置")
    private String startPosition;

    /** 设备类型名称 */
    @ApiModelProperty(notes = "设备类型名称")
    private String dictLabel;
}
