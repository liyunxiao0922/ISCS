package com.ruoyi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.domain.EventLogFile;
import com.ruoyi.mapper.EventLogFileMapper;
import com.ruoyi.service.IEventLogFileService;

/**
 * 事件处理节点文件Service业务层处理
 * 
 * @author 吴晋
 * @date 2021-02-24
 */
@Service
public class EventLogFileServiceImpl implements IEventLogFileService 
{
    @Autowired
    private EventLogFileMapper eventLogFileMapper;

    /**
     * 查询事件处理节点文件
     * 
     * @param id 事件处理节点文件ID
     * @return 事件处理节点文件
     */
    @Override
    public EventLogFile selectEventLogFileById(Long id)
    {
        return eventLogFileMapper.selectEventLogFileById(id);
    }

    /**
     * 查询事件处理节点文件列表
     * 
     * @param eventLogFile 事件处理节点文件
     * @return 事件处理节点文件
     */
    @Override
    public List<EventLogFile> selectEventLogFileList(EventLogFile eventLogFile)
    {
        return eventLogFileMapper.selectEventLogFileList(eventLogFile);
    }

    /**
     * 新增事件处理节点文件
     * 
     * @param eventLogFile 事件处理节点文件
     * @return 结果
     */
    @Override
    public int insertEventLogFile(EventLogFile eventLogFile)
    {
        return eventLogFileMapper.insertEventLogFile(eventLogFile);
    }

    /**
     * 修改事件处理节点文件
     * 
     * @param eventLogFile 事件处理节点文件
     * @return 结果
     */
    @Override
    public int updateEventLogFile(EventLogFile eventLogFile)
    {
        return eventLogFileMapper.updateEventLogFile(eventLogFile);
    }

    /**
     * 批量删除事件处理节点文件
     * 
     * @param ids 需要删除的事件处理节点文件ID
     * @return 结果
     */
    @Override
    public int deleteEventLogFileByIds(Long[] ids)
    {
        return eventLogFileMapper.deleteEventLogFileByIds(ids);
    }

    /**
     * 删除事件处理节点文件信息
     * 
     * @param id 事件处理节点文件ID
     * @return 结果
     */
    @Override
    public int deleteEventLogFileById(Long id)
    {
        return eventLogFileMapper.deleteEventLogFileById(id);
    }
}
