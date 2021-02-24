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
import com.ruoyi.base.domain.BaseTunnel;
import com.ruoyi.base.service.IBaseTunnelService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 基础数据设备Controller
 * 
 * @author ruoyi
 * @date 2021-02-07
 */
@RestController
@RequestMapping("/base/tunnel")
@Api(tags = "隧道管理")
public class BaseTunnelController extends BaseController
{
    @Autowired
    private IBaseTunnelService baseTunnelService;

    /**
     * 查询基础数据设备列表
     */
    @PreAuthorize("@ss.hasPermi('base:tunnel:list')")
    @GetMapping("/list")
    @ApiOperation("隧道分页查询")
    public TableDataInfo list(BaseTunnel baseTunnel)
    {
        startPage();
        List<BaseTunnel> list = baseTunnelService.selectBaseTunnelList(baseTunnel);
        return getDataTable(list);
    }

    /**
     * 导出基础数据设备列表
     */
    @PreAuthorize("@ss.hasPermi('base:tunnel:export')")
    @Log(title = "基础数据设备", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    @ApiOperation("隧道数据导出")
    public AjaxResult export(BaseTunnel baseTunnel)
    {
        List<BaseTunnel> list = baseTunnelService.selectBaseTunnelList(baseTunnel);
        ExcelUtil<BaseTunnel> util = new ExcelUtil<BaseTunnel>(BaseTunnel.class);
        return util.exportExcel(list, "tunnel");
    }

    /**
     * 获取基础数据设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('base:tunnel:query')")
    @GetMapping(value = "/getInfo")
    @ApiOperation("查询隧道详情")
    public AjaxResult getInfo(Long tunnelId)
    {
        return AjaxResult.success(baseTunnelService.selectBaseTunnelById(tunnelId));
    }

    /**
     * 新增基础数据设备
     */
    @PreAuthorize("@ss.hasPermi('base:tunnel:add')")
    @Log(title = "基础数据设备", businessType = BusinessType.INSERT)
    @PostMapping("add")
    @ApiOperation("隧道添加")
    public AjaxResult add(@RequestBody BaseTunnel baseTunnel)
    {
        return toAjax(baseTunnelService.insertBaseTunnel(baseTunnel));
    }

    /**
     * 修改基础数据设备
     */
    @PreAuthorize("@ss.hasPermi('base:tunnel:edit')")
    @Log(title = "基础数据设备", businessType = BusinessType.UPDATE)
    @PutMapping("edit")
    @ApiOperation("隧道编辑")
    public AjaxResult edit(@RequestBody BaseTunnel baseTunnel)
    {
        return toAjax(baseTunnelService.updateBaseTunnel(baseTunnel));
    }

    /**
     * 删除基础数据设备
     */
    @PreAuthorize("@ss.hasPermi('base:tunnel:remove')")
    @Log(title = "基础数据设备", businessType = BusinessType.DELETE)
	@DeleteMapping("/delete")
    @ApiOperation("隧道删除")
    public AjaxResult delete(BaseTunnel baseTunnel)
    {
        return toAjax(baseTunnelService.deleteBaseTunnelById(baseTunnel));
    }
}
