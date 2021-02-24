package com.ruoyi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 事件管理对象 event_accident
 * 
 * @author 吴晋
 * @date 2021-02-07
 */
public class Accident extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 主键 */
	private Long accidentId;

	/** 事件类型 */
	@Excel(name = "事件类型")
	private Long accidentType;

	/** 位置 */
	@Excel(name = "位置")
	private String location;

	/** 方向 */
	@Excel(name = "方向")
	private String direction;

	/** 桩号 */
	@Excel(name = "桩号")
	private Long stakeNumber;

	/** 路况 */
	@Excel(name = "路况")
	private String roadConditions;

	/** 事件等级 */
	@Excel(name = "事件等级")
	private String level;

	/** 当事人手机号 */
	@Excel(name = "当事人手机号")
	private Long phoneNum;

	/** 简要描述 */
	@Excel(name = "简要描述")
	private String message;

	public void setAccidentId(Long accidentId) {
		this.accidentId = accidentId;
	}

	public Long getAccidentId() {
		return accidentId;
	}

	public void setAccidentType(Long accidentType) {
		this.accidentType = accidentType;
	}

	public Long getAccidentType() {
		return accidentType;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getDirection() {
		return direction;
	}

	public void setStakeNumber(Long stakeNumber) {
		this.stakeNumber = stakeNumber;
	}

	public Long getStakeNumber() {
		return stakeNumber;
	}

	public void setRoadConditions(String roadConditions) {
		this.roadConditions = roadConditions;
	}

	public String getRoadConditions() {
		return roadConditions;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLevel() {
		return level;
	}

	public void setPhoneNum(Long phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Long getPhoneNum() {
		return phoneNum;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("accidentId", getAccidentId())
				.append("accidentType", getAccidentType()).append("location", getLocation())
				.append("direction", getDirection()).append("stakeNumber", getStakeNumber())
				.append("roadConditions", getRoadConditions()).append("level", getLevel())
				.append("phoneNum", getPhoneNum()).append("message", getMessage()).toString();
	}
}
