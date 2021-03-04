package com.ruoyi.base.service.impl;

import java.util.List;

import com.ruoyi.base.domain.BaseDeviceStatusIcon;
import com.ruoyi.base.mapper.BaseDeviceStatusIconMapper;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.base.mapper.BaseTunnelDeviceMapper;
import com.ruoyi.base.domain.BaseTunnelDevice;
import com.ruoyi.base.service.IBaseTunnelDeviceService;

/**
 * 基础数据设备Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-02-08
 */
@Service
public class BaseTunnelDeviceServiceImpl implements IBaseTunnelDeviceService {
	@Autowired
	private BaseTunnelDeviceMapper baseTunnelDeviceMapper;

	@Autowired
	private BaseDeviceStatusIconMapper baseDeviceStatusIconMapper;

	/**
	 * 查询基础数据设备
	 * 
	 * @param deviceId 基础数据设备ID
	 * @return 基础数据设备
	 */
	@Override
	public BaseTunnelDevice selectBaseTunnelDeviceById(Long deviceId) {
		return baseTunnelDeviceMapper.selectBaseTunnelDeviceById(deviceId);
	}

	/**
	 * 查询基础数据设备列表
	 * 
	 * @param baseTunnelDevice 基础数据设备
	 * @return 基础数据设备
	 */
	@Override
	public List<BaseTunnelDevice> selectBaseTunnelDeviceList(BaseTunnelDevice baseTunnelDevice) {
		return baseTunnelDeviceMapper.selectBaseTunnelDeviceList(baseTunnelDevice);
	}

	/**
	 * 新增基础数据设备
	 * 
	 * @param baseTunnelDevice 基础数据设备
	 * @return 结果
	 */
	@Override
	public int insertBaseTunnelDevice(BaseTunnelDevice baseTunnelDevice) {
		baseTunnelDevice.setCreateTime(DateUtils.getNowDate());
		BaseDeviceStatusIcon baseDeviceStatusIcon = baseDeviceStatusIconMapper
				.queryFirstIconAdd(baseTunnelDevice.getDeviceTypeId());
		if (baseDeviceStatusIcon == null) {
			throw new RuntimeException("没有添加该设备类型状态图标，请先添加");
		}
		baseTunnelDevice.setWorkStatus(baseDeviceStatusIcon.getIconCode());
		return baseTunnelDeviceMapper.insertBaseTunnelDevice(baseTunnelDevice);
	}

	/**
	 * 修改基础数据设备
	 * 
	 * @param baseTunnelDevice 基础数据设备
	 * @return 结果
	 */
	@Override
	public int updateBaseTunnelDevice(BaseTunnelDevice baseTunnelDevice) {
		baseTunnelDevice.setUpdateTime(DateUtils.getNowDate());
		return baseTunnelDeviceMapper.updateBaseTunnelDevice(baseTunnelDevice);
	}

	/**
	 * 批量删除基础数据设备
	 * 
	 * @param deviceIds 需要删除的基础数据设备ID
	 * @return 结果
	 */
	@Override
	public int deleteBaseTunnelDeviceByIds(Long[] deviceIds) {
		return baseTunnelDeviceMapper.deleteBaseTunnelDeviceByIds(deviceIds);
	}

	/**
	 * 删除基础数据设备信息
	 * 
	 * @param deviceId 基础数据设备ID
	 * @return 结果
	 */
	@Override
	public int deleteBaseTunnelDeviceById(Long deviceId) {
		return baseTunnelDeviceMapper.deleteBaseTunnelDeviceById(deviceId);
	}

	@Override
	public int updateBaseTunnelDeviceByDeviceType(BaseTunnelDevice baseTunnelDevice) {
		return baseTunnelDeviceMapper.updateBaseTunnelDeviceByDeviceType(baseTunnelDevice);
	}
}
