package com.ruoyi.base.controller;

import java.util.List;

import com.ruoyi.base.domain.BaseDeviceTypePageRes;
import com.ruoyi.base.domain.BaseTunnelDevice;
import com.ruoyi.base.domain.TunnelDeviceTypeCollection;
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
import com.ruoyi.base.domain.BaseTunnelDeviceType;
import com.ruoyi.base.service.IBaseTunnelDeviceTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 基础数据设备Controller
 * 
 * @author ruoyi
 * @date 2021-02-07
 */
@RestController
@RequestMapping("/baseDeviceType")
@Api("隧道设备类型管理")
public class BaseTunnelDeviceTypeController extends BaseController
{
    @Autowired
    private IBaseTunnelDeviceTypeService baseTunnelDeviceTypeService;

    /**
     * 查询基础数据设备列表
     */
    @PreAuthorize("@ss.hasPermi('base:deviceType:list')")
    @GetMapping("/list")
    @ApiOperation("隧道设备类型分页查询")
    public TableDataInfo list(BaseTunnelDeviceType baseTunnelDeviceType)
    {
        startPage();
        List<BaseDeviceTypePageRes> list = baseTunnelDeviceTypeService.selectBaseTunnelDeviceTypeList(baseTunnelDeviceType);
        return getDataTable(list);
    }

    /**
     * 查询基础数据设备列表
     */
    @PreAuthorize("@ss.hasPermi('base:deviceType:queryWhole')")
    @GetMapping("/queryWhole")
    @ApiOperation("根据隧道Id查询全部设备类型")
    public AjaxResult queryWhole(BaseTunnelDeviceType baseTunnelDeviceType) {
        List<BaseDeviceTypePageRes> list = baseTunnelDeviceTypeService.selectBaseTunnelDeviceTypeList(baseTunnelDeviceType);
        return AjaxResult.success(list);
    }

    /**
     * 导出基础数据设备列表
     */
    @PreAuthorize("@ss.hasPermi('base:deviceType:export')")
    @Log(title = "基础数据设备", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    @ApiOperation("隧道设备类型导出")
    public AjaxResult export(BaseTunnelDeviceType baseTunnelDeviceType)
    {
        List<BaseDeviceTypePageRes> list = baseTunnelDeviceTypeService.selectBaseTunnelDeviceTypeList(baseTunnelDeviceType);
        ExcelUtil<BaseDeviceTypePageRes> util = new ExcelUtil<BaseDeviceTypePageRes>(BaseDeviceTypePageRes.class);
        return util.exportExcel(list, "deviceType");
    }

    /**
     * 获取基础数据设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('base:deviceType:query')")
    @GetMapping(value = "/getInfo")
    @ApiOperation("隧道设备类型详情")
    public AjaxResult getInfo(Long deviceTypeId)
    {
        return AjaxResult.success(baseTunnelDeviceTypeService.selectBaseTunnelDeviceTypeById(deviceTypeId));
    }

    /**
     * 新增基础数据设备
     */
    @PreAuthorize("@ss.hasPermi('base:deviceType:add')")
    @Log(title = "基础数据设备", businessType = BusinessType.INSERT)
    @PostMapping("add")
    @ApiOperation("隧道设备类型添加")
    public AjaxResult add(@RequestBody BaseTunnelDeviceType baseTunnelDeviceType)
    {
        return toAjax(baseTunnelDeviceTypeService.insertBaseTunnelDeviceType(baseTunnelDeviceType));
    }

    /**
     * 修改基础数据设备
     */
    @PreAuthorize("@ss.hasPermi('base:deviceType:edit')")
    @Log(title = "基础数据设备", businessType = BusinessType.UPDATE)
    @PutMapping("edit")
    @ApiOperation("隧道设备类型编辑")
    public AjaxResult edit(@RequestBody BaseTunnelDeviceType baseTunnelDeviceType)
    {
        return toAjax(baseTunnelDeviceTypeService.updateBaseTunnelDeviceType(baseTunnelDeviceType));
    }

    /**
     * 删除基础数据设备
     *
     */
    @PreAuthorize("@ss.hasPermi('base:deviceType:remove')")
    @Log(title = "隧道设备类型删除", businessType = BusinessType.DELETE)
	@DeleteMapping("/delete")
    @ApiOperation("隧道设备类型删除")
    public AjaxResult delete(Long deviceTypeId)
    {
        return toAjax(baseTunnelDeviceTypeService.deleteBaseTunnelDeviceTypeById(deviceTypeId));
    }

    /**
     * 删除基础数据设备
     *
     */
    @PreAuthorize("@ss.hasPermi('base:deviceType:queryTypeAndSubByDeviceIds')")
    @Log(title = "隧道设备类型删除", businessType = BusinessType.OTHER)
    @PostMapping("/queryTypeAndSubByDeviceIds")
    @ApiOperation("根据设备类型id查询全部设备类型详情及全部具体设备全部")
    public AjaxResult queryTypeAndSubByDeviceIds(@RequestBody List<Long> deviceTypeIds)
    {
        return AjaxResult.success(baseTunnelDeviceTypeService.queryTypeAndSubByDeviceIds(deviceTypeIds));
    }
}
