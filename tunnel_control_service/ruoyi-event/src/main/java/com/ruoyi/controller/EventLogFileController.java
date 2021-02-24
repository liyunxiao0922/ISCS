package com.ruoyi.controller;

import java.util.List;

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

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.domain.EventLogFile;
import com.ruoyi.service.IEventLogFileService;

/**
 * 事件处理节点文件Controller
 * 
 * @author 吴晋
 * @date 2021-02-24
 */
@RestController
@RequestMapping("/event/file")
public class EventLogFileController extends BaseController
{
    @Autowired
    private IEventLogFileService eventLogFileService;

    /**
     * 查询事件处理节点文件列表
     */
    @PreAuthorize("@ss.hasPermi('event:file:list')")
    @GetMapping("/list")
    public TableDataInfo list(EventLogFile eventLogFile)
    {
        startPage();
        List<EventLogFile> list = eventLogFileService.selectEventLogFileList(eventLogFile);
        return getDataTable(list);
    }

    /**
     * 导出事件处理节点文件列表
     */
    @PreAuthorize("@ss.hasPermi('event:file:export')")
    @Log(title = "事件处理节点文件", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EventLogFile eventLogFile)
    {
        List<EventLogFile> list = eventLogFileService.selectEventLogFileList(eventLogFile);
        ExcelUtil<EventLogFile> util = new ExcelUtil<EventLogFile>(EventLogFile.class);
        return util.exportExcel(list, "file");
    }

    /**
     * 获取事件处理节点文件详细信息
     */
    @PreAuthorize("@ss.hasPermi('event:file:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(eventLogFileService.selectEventLogFileById(id));
    }

    /**
     * 新增事件处理节点文件
     */
    @PreAuthorize("@ss.hasPermi('event:file:add')")
    @Log(title = "事件处理节点文件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EventLogFile eventLogFile)
    {
        return toAjax(eventLogFileService.insertEventLogFile(eventLogFile));
    }

    /**
     * 修改事件处理节点文件
     */
    @PreAuthorize("@ss.hasPermi('event:file:edit')")
    @Log(title = "事件处理节点文件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EventLogFile eventLogFile)
    {
        return toAjax(eventLogFileService.updateEventLogFile(eventLogFile));
    }

    /**
     * 删除事件处理节点文件
     */
    @PreAuthorize("@ss.hasPermi('event:file:remove')")
    @Log(title = "事件处理节点文件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(eventLogFileService.deleteEventLogFileByIds(ids));
    }
}
