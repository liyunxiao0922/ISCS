package com.ruoyi.mapper;

import java.util.List;

import com.ruoyi.domain.EventLogFile;

/**
 * 事件处理节点文件Mapper接口
 * 
 * @author 吴晋
 * @date 2021-02-24
 */
public interface EventLogFileMapper 
{
    /**
     * 查询事件处理节点文件
     * 
     * @param id 事件处理节点文件ID
     * @return 事件处理节点文件
     */
    public EventLogFile selectEventLogFileById(Long id);

    /**
     * 查询事件处理节点文件列表
     * 
     * @param eventLogFile 事件处理节点文件
     * @return 事件处理节点文件集合
     */
    public List<EventLogFile> selectEventLogFileList(EventLogFile eventLogFile);

    /**
     * 新增事件处理节点文件
     * 
     * @param eventLogFile 事件处理节点文件
     * @return 结果
     */
    public int insertEventLogFile(EventLogFile eventLogFile);

    /**
     * 修改事件处理节点文件
     * 
     * @param eventLogFile 事件处理节点文件
     * @return 结果
     */
    public int updateEventLogFile(EventLogFile eventLogFile);

    /**
     * 删除事件处理节点文件
     * 
     * @param id 事件处理节点文件ID
     * @return 结果
     */
    public int deleteEventLogFileById(Long id);

    /**
     * 批量删除事件处理节点文件
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEventLogFileByIds(Long[] ids);
}
