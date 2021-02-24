package com.ruoyi.base.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseTunnelDeviceSimple implements Serializable {

    private static final long serialVersionUID = -1563525139101717732L;

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
    @ApiModelProperty(notes = "分组：1，上行，0，下行")
    private String deviceGroup;

    /** 工作状态，来源与设备类型状态表 */
    @ApiModelProperty(notes = "工作状态，来源与设备类型状态表")
    private String workStatus;

    // 图片地址
    @ApiModelProperty(notes = "图片地址")
    private String imageAddress;
}
