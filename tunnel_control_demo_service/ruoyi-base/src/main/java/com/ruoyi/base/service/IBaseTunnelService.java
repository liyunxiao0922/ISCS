package com.ruoyi.base.service;

import java.util.List;
import com.ruoyi.base.domain.BaseTunnel;

/**
 * 基础数据设备Service接口
 * 
 * @author ruoyi
 * @date 2021-02-07
 */
public interface IBaseTunnelService 
{
    /**
     * 查询基础数据设备
     * 
     * @param tunnelId 基础数据设备ID
     * @return 基础数据设备
     */
    public BaseTunnel selectBaseTunnelById(Long tunnelId);

    /**
     * 查询基础数据设备列表
     * 
     * @param baseTunnel 基础数据设备
     * @return 基础数据设备集合
     */
    public List<BaseTunnel> selectBaseTunnelList(BaseTunnel baseTunnel);

    /**
     * 新增基础数据设备
     * 
     * @param baseTunnel 基础数据设备
     * @return 结果
     */
    public int insertBaseTunnel(BaseTunnel baseTunnel);

    /**
     * 修改基础数据设备
     * 
     * @param baseTunnel 基础数据设备
     * @return 结果
     */
    public int updateBaseTunnel(BaseTunnel baseTunnel);

    /**
     * 批量删除基础数据设备
     * 
     * @param tunnelIds 需要删除的基础数据设备ID
     * @return 结果
     */
    public int deleteBaseTunnelByIds(Long[] tunnelIds);

    /**
     * 删除基础数据设备信息
     * 
     * @param tunnelId 基础数据设备ID
     * @return 结果
     */
    public int deleteBaseTunnelById(BaseTunnel baseTunnel);
}
