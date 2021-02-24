package com.ruoyi.base.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TunnelDeviceTypeCollection implements Serializable {

    private static final long serialVersionUID = 4656293338420158357L;

    /** 主键id */
    @ApiModelProperty(notes = "主键id")
    private Long deviceTypeId;

    /** 隧道id */
    @ApiModelProperty(notes = "主键id")
    private Long tunnelId;

    /** 设备类型编码 */
    @ApiModelProperty(notes = "主键id")
    private String deviceTypeCode;

    /** 该隧道设备间距 */
    @ApiModelProperty(notes = "主键id")
    private String deviceSpace;

    /** 设备类型名称 */
    @ApiModelProperty(notes = "主键id")
    private String deviceTypeName;

    /** 设备类型图标地址 */
    @ApiModelProperty(notes = "主键id")
    private String deviceImageAddress;

    /** 设备起始位置 */
    @ApiModelProperty(notes = "主键id")
    private String startPosition;

    private List<BaseTunnelDeviceSimple> tunnelDeviceList;

    // 上行list
    @ApiModelProperty(notes = "上行list")
    private List<BaseTunnelDeviceSimple> tunnelDeviceUpList;

    // 下行List
    @ApiModelProperty(notes = "下行List")
    private List<BaseTunnelDeviceSimple> tunnelDeviceDownList;
}
