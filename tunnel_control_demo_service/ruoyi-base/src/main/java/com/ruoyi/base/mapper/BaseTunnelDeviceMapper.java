package com.ruoyi.base.mapper;

import java.util.List;
import com.ruoyi.base.domain.BaseTunnelDevice;

/**
 * 基础数据设备Mapper接口
 * 
 * @author ruoyi
 * @date 2021-02-08
 */
public interface BaseTunnelDeviceMapper 
{
    /**
     * 查询基础数据设备
     * 
     * @param deviceId 基础数据设备ID
     * @return 基础数据设备
     */
    public BaseTunnelDevice selectBaseTunnelDeviceById(Long deviceId);

    /**
     * 查询基础数据设备列表
     * 
     * @param baseTunnelDevice 基础数据设备
     * @return 基础数据设备集合
     */
    public List<BaseTunnelDevice> selectBaseTunnelDeviceList(BaseTunnelDevice baseTunnelDevice);

    /**
     * 新增基础数据设备
     * 
     * @param baseTunnelDevice 基础数据设备
     * @return 结果
     */
    public int insertBaseTunnelDevice(BaseTunnelDevice baseTunnelDevice);

    /**
     * 修改基础数据设备
     * 
     * @param baseTunnelDevice 基础数据设备
     * @return 结果
     */
    public int updateBaseTunnelDevice(BaseTunnelDevice baseTunnelDevice);
    
    /**
     * 修改基础数据设备
     * 
     * @param baseTunnelDevice 基础数据设备
     * @return 结果
     */
    public int updateBaseTunnelDeviceByDeviceType(BaseTunnelDevice baseTunnelDevice);

    /**
     * 删除基础数据设备
     * 
     * @param deviceId 基础数据设备ID
     * @return 结果
     */
    public int deleteBaseTunnelDeviceById(Long deviceId);

    /**
     * 批量删除基础数据设备
     * 
     * @param deviceIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseTunnelDeviceByIds(Long[] deviceIds);
}
