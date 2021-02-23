package com.ruoyi.framework.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.ruoyi.common.utils.ServletUtils;
import org.springframework.util.StringUtils;

/**
 * 服务相关配置
 * 
 * @author ruoyi
 */
@Component
public class ServerConfig
{

    @Value("${ruoyi.domainName}")
    private String domainName;

    /**
     * 获取完整的请求路径，包括：域名，端口，上下文访问路径
     * 
     * @return 服务地址
     */
    public String getUrl()
    {
        HttpServletRequest request = ServletUtils.getRequest();
        return getDomain(request,domainName);
    }

    public static String getDomain(HttpServletRequest request, String domainName)
    {
        if (!StringUtils.isEmpty(domainName)) {
            return domainName;
        }
        StringBuffer url = request.getRequestURL();
        String contextPath = request.getServletContext().getContextPath();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).append(contextPath).toString();
    }
}
