package com.ruoyi.service;

import java.util.List;
import com.ruoyi.domain.EventLog;

/**
 * 事件处置节点Service接口
 * 
 * @author 吴晋
 * @date 2021-02-19
 */
public interface IEventLogService {
	/**
	 * 查询事件处置节点
	 * 
	 * @param id 事件处置节点ID
	 * @return 事件处置节点
	 */
	public EventLog selectEventLogById(Long id);

	/**
	 * 查询事件处置节点列表
	 * 
	 * @param eventLog 事件处置节点
	 * @return 事件处置节点集合
	 */
	public List<EventLog> selectEventLogList(EventLog eventLog);

	/**
	 * 新增事件处置节点
	 * 
	 * @param eventLog 事件处置节点
	 * @return 结果
	 */
	public int insertEventLog(EventLog eventLog);

	/**
	 * 修改事件处置节点
	 * 
	 * @param eventLog 事件处置节点
	 * @return 结果
	 */
	public int updateEventLog(EventLog eventLog);

	/**
	 * 批量删除事件处置节点
	 * 
	 * @param ids 需要删除的事件处置节点ID
	 * @return 结果
	 */
	public int deleteEventLogByIds(Long[] ids);

	/**
	 * 删除事件处置节点信息
	 * 
	 * @param id 事件处置节点ID
	 * @return 结果
	 */
	public int deleteEventLogById(Long id);
}
