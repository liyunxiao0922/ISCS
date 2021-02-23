package com.ruoyi.base.service;

import java.util.List;
import com.ruoyi.base.domain.BaseDeviceStatusIcon;

/**
 * 基础数据设备Service接口
 * 
 * @author ruoyi
 * @date 2021-02-08
 */
public interface IBaseDeviceStatusIconService 
{
    /**
     * 查询基础数据设备
     * 
     * @param statusIconId 基础数据设备ID
     * @return 基础数据设备
     */
    public BaseDeviceStatusIcon selectBaseDeviceStatusIconById(Long statusIconId);

    /**
     * 查询基础数据设备列表
     * 
     * @param baseDeviceStatusIcon 基础数据设备
     * @return 基础数据设备集合
     */
    public List<BaseDeviceStatusIcon> selectBaseDeviceStatusIconList(BaseDeviceStatusIcon baseDeviceStatusIcon);

    /**
     * 新增基础数据设备
     * 
     * @param baseDeviceStatusIcon 基础数据设备
     * @return 结果
     */
    public int insertBaseDeviceStatusIcon(BaseDeviceStatusIcon baseDeviceStatusIcon);

    /**
     * 修改基础数据设备
     * 
     * @param baseDeviceStatusIcon 基础数据设备
     * @return 结果
     */
    public int updateBaseDeviceStatusIcon(BaseDeviceStatusIcon baseDeviceStatusIcon);

    /**
     * 批量删除基础数据设备
     * 
     * @param statusIconIds 需要删除的基础数据设备ID
     * @return 结果
     */
    public int deleteBaseDeviceStatusIconByIds(Long[] statusIconIds);

    /**
     * 删除基础数据设备信息
     * 
     * @param statusIconId 基础数据设备ID
     * @return 结果
     */
    public int deleteBaseDeviceStatusIconById(Long statusIconId);
}
