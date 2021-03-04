package com.ruoyi.base.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.base.domain.BaseTunnelDevice;
import com.ruoyi.base.service.IBaseTunnelDeviceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 基础数据设备Controller
 * 
 * @author ruoyi
 * @date 2021-02-08
 */
@RestController
@RequestMapping("/baseTunnelDevice")
@Api(tags = "具体设备管理")
public class BaseTunnelDeviceController extends BaseController {
	@Autowired
	private IBaseTunnelDeviceService baseTunnelDeviceService;

	/**
	 * 查询基础数据设备列表
	 */
	@PreAuthorize("@ss.hasPermi('base:tunnelDevice:list')")
	@GetMapping("/list")
	@ApiOperation("分页查询")
	public TableDataInfo list(BaseTunnelDevice baseTunnelDevice) {
		startPage();
		List<BaseTunnelDevice> list = baseTunnelDeviceService.selectBaseTunnelDeviceList(baseTunnelDevice);
		return getDataTable(list);
	}

	/**
	 * 导出基础数据设备列表
	 */
	@PreAuthorize("@ss.hasPermi('base:tunnelDevice:export')")
	@Log(title = "基础数据设备", businessType = BusinessType.EXPORT)
	@GetMapping("/export")
	@ApiOperation("导出")
	public AjaxResult export(BaseTunnelDevice baseTunnelDevice) {
		List<BaseTunnelDevice> list = baseTunnelDeviceService.selectBaseTunnelDeviceList(baseTunnelDevice);
		ExcelUtil<BaseTunnelDevice> util = new ExcelUtil<BaseTunnelDevice>(BaseTunnelDevice.class);
		return util.exportExcel(list, "tunnelDevice");
	}

	/**
	 * 获取基础数据设备详细信息
	 */
	@PreAuthorize("@ss.hasPermi('base:tunnelDevice:query')")
	@GetMapping(value = "/getInfo")
	@ApiOperation("单个设备详情")
	public AjaxResult getInfo(Long deviceId) {
		return AjaxResult.success(baseTunnelDeviceService.selectBaseTunnelDeviceById(deviceId));
	}

	/**
	 * 新增基础数据设备
	 */
	@PreAuthorize("@ss.hasPermi('base:tunnelDevice:add')")
	@Log(title = "基础数据设备", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ApiOperation("添加")
	public AjaxResult add(@RequestBody BaseTunnelDevice baseTunnelDevice) {
		return toAjax(baseTunnelDeviceService.insertBaseTunnelDevice(baseTunnelDevice));
	}

	/**
	 * 修改基础数据设备
	 */
	@PreAuthorize("@ss.hasPermi('base:tunnelDevice:edit')")
	@Log(title = "基础数据设备", businessType = BusinessType.UPDATE)
	@PutMapping("/edit")
	@ApiOperation("编辑")
	public AjaxResult edit(@RequestBody BaseTunnelDevice baseTunnelDevice) {
		return toAjax(baseTunnelDeviceService.updateBaseTunnelDevice(baseTunnelDevice));
	}
	
	/**
	 * 修改基础数据设备
	 */
	@PreAuthorize("@ss.hasPermi('base:tunnelDevice:edit')")
	@Log(title = "基础数据设备", businessType = BusinessType.UPDATE)
	@PutMapping("/editByDeviceType")
	@ApiOperation("编辑")
	public AjaxResult editByDeviceType(@RequestBody BaseTunnelDevice baseTunnelDevice) {
		return toAjax(baseTunnelDeviceService.updateBaseTunnelDeviceByDeviceType(baseTunnelDevice));
	}

	/**
	 * 删除基础数据设备
	 */
	@PreAuthorize("@ss.hasPermi('base:tunnelDevice:remove')")
	@Log(title = "基础数据设备", businessType = BusinessType.DELETE)
	@DeleteMapping("/remove")
	@ApiOperation("删除")
	public AjaxResult remove(Long deviceId) {
		return toAjax(baseTunnelDeviceService.deleteBaseTunnelDeviceById(deviceId));
	}
}
