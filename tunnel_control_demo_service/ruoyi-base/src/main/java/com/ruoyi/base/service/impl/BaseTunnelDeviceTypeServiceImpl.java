package com.ruoyi.base.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.base.domain.BaseDeviceTypePageRes;
import com.ruoyi.base.domain.BaseTunnelDeviceSimple;
import com.ruoyi.base.domain.TunnelDeviceTypeCollection;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.base.mapper.BaseTunnelDeviceTypeMapper;
import com.ruoyi.base.domain.BaseTunnelDeviceType;
import com.ruoyi.base.service.IBaseTunnelDeviceTypeService;

/**
 * 基础数据设备Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-02-07
 */
@Service
public class BaseTunnelDeviceTypeServiceImpl implements IBaseTunnelDeviceTypeService 
{
    @Autowired
    private BaseTunnelDeviceTypeMapper baseTunnelDeviceTypeMapper;

    /**
     * 查询基础数据设备
     * 
     * @param deviceTypeId 基础数据设备ID
     * @return 基础数据设备
     */
    @Override
    public BaseTunnelDeviceType selectBaseTunnelDeviceTypeById(Long deviceTypeId)
    {
        return baseTunnelDeviceTypeMapper.selectBaseTunnelDeviceTypeById(deviceTypeId);
    }

    /**
     * 查询基础数据设备列表
     * 
     * @param baseTunnelDeviceType 基础数据设备
     * @return 基础数据设备
     */
    @Override
    public List<BaseDeviceTypePageRes> selectBaseTunnelDeviceTypeList(BaseTunnelDeviceType baseTunnelDeviceType)
    {
        return baseTunnelDeviceTypeMapper.selectBaseTunnelDeviceTypeList(baseTunnelDeviceType);
    }

    /**
     * 新增基础数据设备
     * 
     * @param baseTunnelDeviceType 基础数据设备
     * @return 结果
     */
    @Override
    public int insertBaseTunnelDeviceType(BaseTunnelDeviceType baseTunnelDeviceType)
    {
        baseTunnelDeviceType.setCreateTime(DateUtils.getNowDate());
        return baseTunnelDeviceTypeMapper.insertBaseTunnelDeviceType(baseTunnelDeviceType);
    }

    /**
     * 修改基础数据设备
     * 
     * @param baseTunnelDeviceType 基础数据设备
     * @return 结果
     */
    @Override
    public int updateBaseTunnelDeviceType(BaseTunnelDeviceType baseTunnelDeviceType)
    {
        baseTunnelDeviceType.setUpdateTime(DateUtils.getNowDate());
        return baseTunnelDeviceTypeMapper.updateBaseTunnelDeviceType(baseTunnelDeviceType);
    }

    /**
     * 批量删除基础数据设备
     * 
     * @param deviceTypeIds 需要删除的基础数据设备ID
     * @return 结果
     */
    @Override
    public int deleteBaseTunnelDeviceTypeByIds(Long[] deviceTypeIds)
    {
        return baseTunnelDeviceTypeMapper.deleteBaseTunnelDeviceTypeByIds(deviceTypeIds);
    }

    /**
     * 删除基础数据设备信息
     * 
     * @param deviceTypeId 基础数据设备ID
     * @return 结果
     */
    @Override
    public int deleteBaseTunnelDeviceTypeById(Long deviceTypeId)
    {
        return baseTunnelDeviceTypeMapper.deleteBaseTunnelDeviceTypeById(deviceTypeId);
    }

    @Override
    public List<TunnelDeviceTypeCollection> queryTypeAndSubByDeviceIds(List<Long> deviceTypeIds) {
        List<TunnelDeviceTypeCollection> tunnelDeviceTypeCollections = baseTunnelDeviceTypeMapper.queryTypeAndSubByDeviceIds(deviceTypeIds);
        tunnelDeviceTypeCollections.forEach(tunnelDeviceTypeCollection -> {
            List<BaseTunnelDeviceSimple> tunnelDeviceUpList = tunnelDeviceTypeCollection.getTunnelDeviceList().stream().filter(baseTunnelDeviceSimple -> baseTunnelDeviceSimple.getDeviceGroup().equals("1")).collect(Collectors.toList());
            List<BaseTunnelDeviceSimple> tunnelDeviceDownList = tunnelDeviceTypeCollection.getTunnelDeviceList().stream().filter(baseTunnelDeviceSimple -> baseTunnelDeviceSimple.getDeviceGroup().equals("0")).collect(Collectors.toList());
            tunnelDeviceTypeCollection.setTunnelDeviceUpList(tunnelDeviceUpList);
            tunnelDeviceTypeCollection.setTunnelDeviceDownList(tunnelDeviceDownList);
        });
        return tunnelDeviceTypeCollections;

    }
}
