package com.ruoyi.mapper;

import java.util.List;
import com.ruoyi.domain.Accident;

/**
 * 事件管理Mapper接口
 * 
 * @author 吴晋
 * @date 2021-02-07
 */
public interface AccidentMapper {
	/**
	 * 查询事件管理
	 * 
	 * @param accidentId 事件管理ID
	 * @return 事件管理
	 */
	public Accident selectAccidentById(Long accidentId);

	/**
	 * 查询事件管理列表
	 * 
	 * @param accident 事件管理
	 * @return 事件管理集合
	 */
	public List<Accident> selectAccidentList(Accident accident);

	/**
	 * 新增事件管理
	 * 
	 * @param accident 事件管理
	 * @return 结果
	 */
	public int insertAccident(Accident accident);

	/**
	 * 修改事件管理
	 * 
	 * @param accident 事件管理
	 * @return 结果
	 */
	public int updateAccident(Accident accident);

	/**
	 * 删除事件管理
	 * 
	 * @param accidentId 事件管理ID
	 * @return 结果
	 */
	public int deleteAccidentById(Long accidentId);

	/**
	 * 批量删除事件管理
	 * 
	 * @param accidentIds 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteAccidentByIds(Long[] accidentIds);
}
