package com.ruoyi.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.mapper.EventLogFileMapper;
import com.ruoyi.mapper.EventLogMapper;
import com.ruoyi.domain.EventLog;
import com.ruoyi.service.IEventLogService;

/**
 * 事件处置节点Service业务层处理
 * 
 * @author 吴晋
 * @date 2021-02-19
 */
@Service
public class EventLogServiceImpl implements IEventLogService {
	@Autowired
	private EventLogMapper eventLogMapper;
	@Autowired
	private EventLogFileMapper eventLogFileMapper;

	/**
	 * 查询事件处置节点
	 * 
	 * @param id 事件处置节点ID
	 * @return 事件处置节点
	 */
	@Override
	public EventLog selectEventLogById(Long id) {
		return eventLogMapper.selectEventLogById(id);
	}

	/**
	 * 查询事件处置节点列表
	 * 
	 * @param eventLog 事件处置节点
	 * @return 事件处置节点
	 */
	@Override
	public List<EventLog> selectEventLogList(EventLog eventLog) {
		return eventLogMapper.selectEventLogList(eventLog);
	}

	/**
	 * 新增事件处置节点
	 * 
	 * @param eventLog 事件处置节点
	 * @return 结果
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insertEventLog(EventLog eventLog) {

		eventLog.setCreateTime(DateUtils.getNowDate());
		int result = eventLogMapper.insertEventLog(eventLog);
		eventLog.getEventLogFiles().forEach(x -> {
			x.setLogId(eventLog.getId());
			eventLogFileMapper.insertEventLogFile(x);
		});
		return result;
	}

	/**
	 * 修改事件处置节点
	 * 
	 * @param eventLog 事件处置节点
	 * @return 结果
	 */
	@Override
	public int updateEventLog(EventLog eventLog) {
		return eventLogMapper.updateEventLog(eventLog);
	}

	/**
	 * 批量删除事件处置节点
	 * 
	 * @param ids 需要删除的事件处置节点ID
	 * @return 结果
	 */
	@Override
	public int deleteEventLogByIds(Long[] ids) {
		return eventLogMapper.deleteEventLogByIds(ids);
	}

	/**
	 * 删除事件处置节点信息
	 * 
	 * @param id 事件处置节点ID
	 * @return 结果
	 */
	@Override
	public int deleteEventLogById(Long id) {
		return eventLogMapper.deleteEventLogById(id);
	}
}
