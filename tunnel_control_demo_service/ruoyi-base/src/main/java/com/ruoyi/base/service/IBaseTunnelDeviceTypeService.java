package com.ruoyi.base.service;

import java.util.List;

import com.ruoyi.base.domain.BaseDeviceTypePageRes;
import com.ruoyi.base.domain.BaseTunnelDeviceType;
import com.ruoyi.base.domain.TunnelDeviceTypeCollection;

/**
 * 基础数据设备Service接口
 * 
 * @author ruoyi
 * @date 2021-02-07
 */
public interface IBaseTunnelDeviceTypeService 
{
    /**
     * 查询基础数据设备
     * 
     * @param deviceTypeId 基础数据设备ID
     * @return 基础数据设备
     */
    public BaseTunnelDeviceType selectBaseTunnelDeviceTypeById(Long deviceTypeId);

    /**
     * 查询基础数据设备列表
     * 
     * @param baseTunnelDeviceType 基础数据设备
     * @return 基础数据设备集合
     */
    public List<BaseDeviceTypePageRes> selectBaseTunnelDeviceTypeList(BaseTunnelDeviceType baseTunnelDeviceType);

    /**
     * 新增基础数据设备
     * 
     * @param baseTunnelDeviceType 基础数据设备
     * @return 结果
     */
    public int insertBaseTunnelDeviceType(BaseTunnelDeviceType baseTunnelDeviceType);

    /**
     * 修改基础数据设备
     * 
     * @param baseTunnelDeviceType 基础数据设备
     * @return 结果
     */
    public int updateBaseTunnelDeviceType(BaseTunnelDeviceType baseTunnelDeviceType);

    /**
     * 批量删除基础数据设备
     * 
     * @param deviceTypeIds 需要删除的基础数据设备ID
     * @return 结果
     */
    public int deleteBaseTunnelDeviceTypeByIds(Long[] deviceTypeIds);

    /**
     * 删除基础数据设备信息
     * 
     * @param deviceTypeId 基础数据设备ID
     * @return 结果
     */
    public int deleteBaseTunnelDeviceTypeById(Long deviceTypeId);

    /**
     * 批量删除基础数据设备
     *
     * @param deviceTypeIds 需要删除的基础数据设备ID
     * @return 结果
     */
    public List<TunnelDeviceTypeCollection> queryTypeAndSubByDeviceIds(List<Long> deviceTypeIds);
}
