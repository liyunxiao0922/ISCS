package com.ruoyi.domain.Vo;

/**
 * 
 * @ClassName: EventPushVO
 * @Description: 向外推送消息时使用，可自定义字段
 * @author wujin
 * @date 2021年2月24日
 *
 */
public class EventPushVO {
	/**
	 * 推送类别
	 */
	private Integer pushType;
	/**
	 * 事件id
	 */
	private Long eventId;
	/**
	 * 事件类型
	 */
	private Long eventType;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 图片地址
	 */
	private String url;

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getEventType() {
		return eventType;
	}

	public void setEventType(Long eventType) {
		this.eventType = eventType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPushType() {
		return pushType;
	}

	public void setPushType(Integer pushType) {
		this.pushType = pushType;
	}

	

}
