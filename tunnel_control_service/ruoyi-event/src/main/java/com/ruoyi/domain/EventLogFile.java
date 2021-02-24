package com.ruoyi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 事件处理节点文件对象 event_log_file
 * 
 * @author 吴晋
 * @date 2021-02-24
 */
public class EventLogFile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 处理节点id */
    @Excel(name = "处理节点id")
    private Long logId;

    /** 文件地址 */
    @Excel(name = "文件地址")
    private String uri;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setLogId(Long logId) 
    {
        this.logId = logId;
    }

    public Long getLogId() 
    {
        return logId;
    }
    public void setUri(String uri) 
    {
        this.uri = uri;
    }

    public String getUri() 
    {
        return uri;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("logId", getLogId())
            .append("uri", getUri())
            .append("fileName", getFileName())
            .toString();
    }
}
