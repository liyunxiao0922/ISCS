package com.ruoyi.base.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.base.mapper.BaseDeviceStatusIconMapper;
import com.ruoyi.base.domain.BaseDeviceStatusIcon;
import com.ruoyi.base.service.IBaseDeviceStatusIconService;

/**
 * 基础数据设备Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-02-08
 */
@Service
public class BaseDeviceStatusIconServiceImpl implements IBaseDeviceStatusIconService 
{
    @Autowired
    private BaseDeviceStatusIconMapper baseDeviceStatusIconMapper;

    /**
     * 查询基础数据设备
     * 
     * @param statusIconId 基础数据设备ID
     * @return 基础数据设备
     */
    @Override
    public BaseDeviceStatusIcon selectBaseDeviceStatusIconById(Long statusIconId)
    {
        return baseDeviceStatusIconMapper.selectBaseDeviceStatusIconById(statusIconId);
    }

    /**
     * 查询基础数据设备列表
     * 
     * @param baseDeviceStatusIcon 基础数据设备
     * @return 基础数据设备
     */
    @Override
    public List<BaseDeviceStatusIcon> selectBaseDeviceStatusIconList(BaseDeviceStatusIcon baseDeviceStatusIcon)
    {
        return baseDeviceStatusIconMapper.selectBaseDeviceStatusIconList(baseDeviceStatusIcon);
    }

    /**
     * 新增基础数据设备
     * 
     * @param baseDeviceStatusIcon 基础数据设备
     * @return 结果
     */
    @Override
    public int insertBaseDeviceStatusIcon(BaseDeviceStatusIcon baseDeviceStatusIcon)
    {
        baseDeviceStatusIcon.setCreateTime(DateUtils.getNowDate());
        String iconCode = baseDeviceStatusIconMapper.queryLastCode();
        String nextIconCode = null;
        if (iconCode == null || iconCode == "") {
            int i = 0;
            nextIconCode = String.format("%04d", ++i);
        } else {
            int i = Integer.valueOf(iconCode);
            nextIconCode = String.format("%04d", ++i);
        }
        baseDeviceStatusIcon.setIconCode(nextIconCode);
        return baseDeviceStatusIconMapper.insertBaseDeviceStatusIcon(baseDeviceStatusIcon);
    }

    /**
     * 修改基础数据设备
     * 
     * @param baseDeviceStatusIcon 基础数据设备
     * @return 结果
     */
    @Override
    public int updateBaseDeviceStatusIcon(BaseDeviceStatusIcon baseDeviceStatusIcon)
    {
        baseDeviceStatusIcon.setUpdateTime(DateUtils.getNowDate());
        return baseDeviceStatusIconMapper.updateBaseDeviceStatusIcon(baseDeviceStatusIcon);
    }

    /**
     * 批量删除基础数据设备
     * 
     * @param statusIconIds 需要删除的基础数据设备ID
     * @return 结果
     */
    @Override
    public int deleteBaseDeviceStatusIconByIds(Long[] statusIconIds)
    {
        return baseDeviceStatusIconMapper.deleteBaseDeviceStatusIconByIds(statusIconIds);
    }

    /**
     * 删除基础数据设备信息
     * 
     * @param statusIconId 基础数据设备ID
     * @return 结果
     */
    @Override
    public int deleteBaseDeviceStatusIconById(Long statusIconId)
    {
        return baseDeviceStatusIconMapper.deleteBaseDeviceStatusIconById(statusIconId);
    }
}
