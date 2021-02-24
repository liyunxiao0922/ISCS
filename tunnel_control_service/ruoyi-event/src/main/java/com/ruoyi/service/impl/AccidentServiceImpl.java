package com.ruoyi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.domain.Accident;
import com.ruoyi.mapper.AccidentMapper;
import com.ruoyi.service.IAccidentService;

/**
 * 事件管理Service业务层处理
 * 
 * @author 吴晋
 * @date 2021-02-07
 */
@Service
public class AccidentServiceImpl implements IAccidentService {
	@Autowired
	private AccidentMapper accidentMapper;

	/**
	 * 查询事件管理
	 * 
	 * @param accidentId 事件管理ID
	 * @return 事件管理
	 */
	@Override
	public Accident selectAccidentById(Long accidentId) {
		return accidentMapper.selectAccidentById(accidentId);
	}

	/**
	 * 查询事件管理列表
	 * 
	 * @param accident 事件管理
	 * @return 事件管理
	 */
	@Override
	public List<Accident> selectAccidentList(Accident accident) {
		return accidentMapper.selectAccidentList(accident);
	}

	/**
	 * 新增事件管理
	 * 
	 * @param accident 事件管理
	 * @return 结果
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insertAccident(Accident accident) {
		int result = accidentMapper.insertAccident(accident);
		return result;
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     

	/**
	 * 修改事件管理
	 * 
	 * @param accident 事件管理
	 * @return 结果
	 */
	@Override
	public int updateAccident(Accident accident) {
		return accidentMapper.updateAccident(accident);
	}

	/**
	 * 批量删除事件管理
	 * 
	 * @param accidentIds 需要删除的事件管理ID
	 * @return 结果
	 */
	@Override
	public int deleteAccidentByIds(Long[] accidentIds) {
		return accidentMapper.deleteAccidentByIds(accidentIds);
	}

	/**
	 * 删除事件管理信息
	 * 
	 * @param accidentId 事件管理ID
	 * @return 结果
	 */
	@Override
	public int deleteAccidentById(Long accidentId) {
		return accidentMapper.deleteAccidentById(accidentId);
	}
}
