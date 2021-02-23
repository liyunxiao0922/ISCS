package com.ruoyi.base.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
import com.ruoyi.base.domain.BaseDeviceStatusIcon;
import com.ruoyi.base.service.IBaseDeviceStatusIconService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 基础数据设备Controller
 * 
 * @author ruoyi
 * @date 2021-02-08
 */
@RestController
@RequestMapping("/baseStatusIcon")
@Api("设备类型的图标管理")
@Slf4j
public class BaseDeviceStatusIconController extends BaseController
{
    @Autowired
    private IBaseDeviceStatusIconService baseDeviceStatusIconService;

    /**
     * 查询基础数据设备列表
     */
    @PreAuthorize("@ss.hasPermi('base:statusIcon:list')")
    @GetMapping("/list")
    @ApiOperation("设备类型的图标分页查询")
    public TableDataInfo list(BaseDeviceStatusIcon baseDeviceStatusIcon)
    {
        startPage();
        List<BaseDeviceStatusIcon> list = baseDeviceStatusIconService.selectBaseDeviceStatusIconList(baseDeviceStatusIcon);
        return getDataTable(list);
    }

    /**
     * 导出基础数据设备列表
     */
    @PreAuthorize("@ss.hasPermi('base:statusIcon:export')")
    @Log(title = "基础数据设备", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    @ApiOperation("设备类型图标导出")
    public AjaxResult export(BaseDeviceStatusIcon baseDeviceStatusIcon)
    {
        List<BaseDeviceStatusIcon> list = baseDeviceStatusIconService.selectBaseDeviceStatusIconList(baseDeviceStatusIcon);
        ExcelUtil<BaseDeviceStatusIcon> util = new ExcelUtil<BaseDeviceStatusIcon>(BaseDeviceStatusIcon.class);
        return util.exportExcel(list, "statusIcon");
    }

    /**
     * 获取基础数据设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('base:statusIcon:query')")
    @GetMapping(value = "/getInfo")
    @ApiOperation("设备类型图标详情")
    public AjaxResult getInfo(Long statusIconId)
    {
        return AjaxResult.success(baseDeviceStatusIconService.selectBaseDeviceStatusIconById(statusIconId));
    }

    /**
     * 新增基础数据设备
     */
    @PreAuthorize("@ss.hasPermi('base:statusIcon:add')")
    @Log(title = "基础数据设备", businessType = BusinessType.INSERT)
    @PostMapping("add")
    @ApiOperation("设备类型图标添加")
    public AjaxResult add(@RequestBody BaseDeviceStatusIcon baseDeviceStatusIcon)
    {
        return toAjax(baseDeviceStatusIconService.insertBaseDeviceStatusIcon(baseDeviceStatusIcon));
    }

    /**
     * 修改基础数据设备
     */
    @PreAuthorize("@ss.hasPermi('base:statusIcon:edit')")
    @Log(title = "基础数据设备", businessType = BusinessType.UPDATE)
    @PutMapping("edit")
    @ApiOperation("设备类型图标编辑")
    public AjaxResult edit(@RequestBody BaseDeviceStatusIcon baseDeviceStatusIcon)
    {
        return toAjax(baseDeviceStatusIconService.updateBaseDeviceStatusIcon(baseDeviceStatusIcon));
    }

    /**
     * 删除基础数据设备
     */
    @PreAuthorize("@ss.hasPermi('base:statusIcon:remove')")
    @Log(title = "基础数据设备", businessType = BusinessType.DELETE)
	@DeleteMapping("/remove")
    @ApiOperation("设备类型图标删除")
    public AjaxResult remove(Long statusIconId)
    {
        return toAjax(baseDeviceStatusIconService.deleteBaseDeviceStatusIconById(statusIconId));
    }
}
