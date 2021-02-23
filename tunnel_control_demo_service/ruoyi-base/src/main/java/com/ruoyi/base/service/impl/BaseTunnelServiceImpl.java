package com.ruoyi.base.service.impl;

import java.util.Collection;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.base.mapper.BaseTunnelMapper;
import com.ruoyi.base.domain.BaseTunnel;
import com.ruoyi.base.service.IBaseTunnelService;
import org.springframework.util.CollectionUtils;

/**
 * 基础数据设备Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-02-07
 */
@Service
@Slf4j
public class BaseTunnelServiceImpl implements IBaseTunnelService 
{
    @Autowired
    private BaseTunnelMapper baseTunnelMapper;

    /**
     * 查询基础数据设备
     * 
     * @param tunnelId 基础数据设备ID
     * @return 基础数据设备
     */
    @Override
    public BaseTunnel selectBaseTunnelById(Long tunnelId)
    {
        return baseTunnelMapper.selectBaseTunnelById(tunnelId);
    }

    /**
     * 查询基础数据设备列表
     * 
     * @param baseTunnel 基础数据设备
     * @return 基础数据设备
     */
    @Override
    public List<BaseTunnel> selectBaseTunnelList(BaseTunnel baseTunnel)
    {
        return baseTunnelMapper.selectBaseTunnelList(baseTunnel);
    }

    /**
     * 新增基础数据设备
     * 
     * @param baseTunnel 基础数据设备
     * @return 结果
     */
    @Override
    public int insertBaseTunnel(BaseTunnel baseTunnel)
    {
        List<BaseTunnel> tunnelList = baseTunnelMapper.selectBaseTunnelList(baseTunnel);
        if (!CollectionUtils.isEmpty(tunnelList)) {
            log.info("添加隧道检测到该隧道名称已经存在,tunnelName:"+baseTunnel);
            return 0;
        }
        baseTunnel.setCreateTime(DateUtils.getNowDate());
        return baseTunnelMapper.insertBaseTunnel(baseTunnel);
    }

    /**
     * 修改基础数据设备
     * 
     * @param baseTunnel 基础数据设备
     * @return 结果
     */
    @Override
    public int updateBaseTunnel(BaseTunnel baseTunnel)
    {
        baseTunnel.setUpdateTime(DateUtils.getNowDate());
        return baseTunnelMapper.updateBaseTunnel(baseTunnel);
    }

    /**
     * 批量删除基础数据设备
     * 
     * @param tunnelIds 需要删除的基础数据设备ID
     * @return 结果
     */
    @Override
    public int deleteBaseTunnelByIds(Long[] tunnelIds)
    {
        return baseTunnelMapper.deleteBaseTunnelByIds(tunnelIds);
    }

    /**
     * 删除基础数据设备信息
     * 
     * @param baseTunnel 基础数据设备
     * @return 结果
     */
    @Override
    public int deleteBaseTunnelById(BaseTunnel baseTunnel)
    {
        return baseTunnelMapper.deleteBaseTunnelById(baseTunnel);
    }
}
