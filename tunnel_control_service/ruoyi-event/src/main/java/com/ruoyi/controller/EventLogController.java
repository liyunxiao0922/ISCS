package com.ruoyi.controller;

import java.util.List;
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
import com.ruoyi.domain.EventLog;
import com.ruoyi.service.IEventLogService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 事件处置节点Controller
 * 
 * @author 吴晋
 * @date 2021-02-19
 */
@RestController
@RequestMapping("/event/log")
public class EventLogController extends BaseController
{
    @Autowired
    private IEventLogService eventLogService;

    /**
     * 查询事件处置节点列表
     */
    @PreAuthorize("@ss.hasPermi('event:log:list')")
    @GetMapping("/list")
    public TableDataInfo list(EventLog eventLog)
    {
        startPage();
        List<EventLog> list = eventLogService.selectEventLogList(eventLog);
        return getDataTable(list);
    }

    /**
     * 导出事件处置节点列表
     */
    @PreAuthorize("@ss.hasPermi('event:log:export')")
    @Log(title = "事件处置节点", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EventLog eventLog)
    {
        List<EventLog> list = eventLogService.selectEventLogList(eventLog);
        ExcelUtil<EventLog> util = new ExcelUtil<EventLog>(EventLog.class);
        return util.exportExcel(list, "log");
    }

    /**
     * 获取事件处置节点详细信息
     */
    @PreAuthorize("@ss.hasPermi('event:log:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(eventLogService.selectEventLogById(id));
    }

    /**
     * 新增事件处置节点
     */
    @PreAuthorize("@ss.hasPermi('event:log:add')")
    @Log(title = "事件处置节点", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EventLog eventLog)
    {
        return toAjax(eventLogService.insertEventLog(eventLog));
    }

    /**
     * 修改事件处置节点
     */
    @PreAuthorize("@ss.hasPermi('event:log:edit')")
    @Log(title = "事件处置节点", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EventLog eventLog)
    {
        return toAjax(eventLogService.updateEventLog(eventLog));
    }

    /**
     * 删除事件处置节点
     */
    @PreAuthorize("@ss.hasPermi('event:log:remove')")
    @Log(title = "事件处置节点", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(eventLogService.deleteEventLogByIds(ids));
    }
}
