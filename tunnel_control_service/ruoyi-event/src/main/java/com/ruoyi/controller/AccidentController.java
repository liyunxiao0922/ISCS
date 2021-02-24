package com.ruoyi.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.domain.Accident;
import com.ruoyi.domain.Vo.EventPushVO;
import com.ruoyi.domain.Vo.EventVO;
import com.ruoyi.service.IAccidentService;
import com.ruoyi.websocket.PushAccidentWebSocket;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 事件管理Controller
 * 
 * @author 吴晋
 * @date 2021-02-07
 */
@Api("事件上报管理")
@RestController
@RequestMapping("/event/accident")
public class AccidentController extends BaseController {
	@Autowired
	private IAccidentService accidentService;
	@Resource
	private PushAccidentWebSocket accidentWebSocket;

	/**
	 * 查询事件管理列表
	 */
	@ApiOperation("获取事件上报列表")
	@PreAuthorize("@ss.hasPermi('event:accident:list')")
	@GetMapping("/list")
	public TableDataInfo list(Accident accident) {
		startPage();
		List<Accident> list = accidentService.selectAccidentList(accident);
		return getDataTable(list);
	}

	/**
	 * 导出事件管理列表
	 */
	@ApiOperation("导出事件管理列表")
	@PreAuthorize("@ss.hasPermi('event:accident:export')")
	@Log(title = "事件管理", businessType = BusinessType.EXPORT)
	@GetMapping("/export")
	public AjaxResult export(Accident accident) {
		List<Accident> list = accidentService.selectAccidentList(accident);
		ExcelUtil<Accident> util = new ExcelUtil<Accident>(Accident.class);
		return util.exportExcel(list, "accident");
	}

	/**
	 * 获取事件管理详细信息
	 */
	@ApiOperation("获取事件管理详细信息")
	@PreAuthorize("@ss.hasPermi('event:accident:query')")
	@GetMapping(value = "/{accidentId}")
	public AjaxResult getInfo(@PathVariable("accidentId") Long accidentId) {
		return AjaxResult.success(accidentService.selectAccidentById(accidentId));
	}

	/**
	 * 新增事件管理
	 */
	@ApiOperation("新增事件管理")
	@PreAuthorize("@ss.hasPermi('event:accident:add')")
	@Log(title = "事件管理", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@RequestBody Accident accident) {
		int result = accidentService.insertAccident(accident);
		// 新增事件给前端推送事件消息，推送字段可以自定义
		// --开始
		EventVO event = new EventVO();
		event.setEventId(accident.getAccidentId());
		event.setEventType(accident.getAccidentType());
		event.setDescription(accident.getMessage());
		accidentWebSocket.groupSendMessage(JSON.toJSONString(event));
		// --结束
		return toAjax(result);
	}

	/**
	 * 事件向外推送 按照简要需求文档，这里可以在推送时添加文字描述和图片 推送类型分为 1 微信，2 公众号，3 短信，4 邮件
	 */
	@ApiOperation("事件向外推送")
//	@PreAuthorize("@ss.hasPermi('event:accident:add')")
	@Log(title = "事件向外推送", businessType = BusinessType.OTHER)
	@PostMapping("/push")
	public AjaxResult push(@RequestBody EventPushVO eventPushVO) {
		int result = 0;// 操作结果
		switch (eventPushVO.getPushType()) {
		case 1:
			// 微信消息发送

			break;
		case 2:
			// 公众号信息推送

			break;
		case 3:
			// 短信发送

			break;
		case 4:
			// 邮件发送

			break;
		}
		return toAjax(result);
	}

	/**
	 * 修改事件管理
	 */
	@ApiOperation("修改事件管理")
	@PreAuthorize("@ss.hasPermi('event:accident:edit')")
	@Log(title = "事件管理", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@RequestBody Accident accident) {
		return toAjax(accidentService.updateAccident(accident));
	}

	/**
	 * 删除事件管理
	 */
	@ApiOperation("删除事件管理")
	@PreAuthorize("@ss.hasPermi('event:accident:remove')")
	@Log(title = "事件管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{accidentIds}")
	public AjaxResult remove(@PathVariable Long[] accidentIds) {
		return toAjax(accidentService.deleteAccidentByIds(accidentIds));
	}
}
