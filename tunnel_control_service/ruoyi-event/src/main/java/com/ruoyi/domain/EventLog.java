package com.ruoyi.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 事件处置节点对象 event_log
 * 
 * @author 吴晋
 * @date 2021-02-19
 */
public class EventLog extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 主键 */
	private Long id;

	/** 事件主键 */
	@Excel(name = "事件主键")
	private Long accidentId;

	/** 处理阶段 */
	@Excel(name = "处理阶段")
	private String status;

	/** 处理详情 */
	@Excel(name = "处理详情")
	private String description;

	private List<EventLogFile> eventLogFiles;

	public List<EventLogFile> getEventLogFiles() {
		return eventLogFiles;
	}

	public void setEventLogFiles(List<EventLogFile> eventLogFiles) {
		this.eventLogFiles = eventLogFiles;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setAccidentId(Long accidentId) {
		this.accidentId = accidentId;
	}

	public Long getAccidentId() {
		return accidentId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", getId())
				.append("accidentId", getAccidentId()).append("status", getStatus())
				.append("description", getDescription()).append("createTime", getCreateTime()).toString();
	}
}
