<?xml version="1.0" encoding="UTF-8"/>
<#assign context="/koala-spring"/>
<#macro page page action>
<form action="${action}" method="POST">
    <#if !page.isFirst()>
    <a href="${action}?pageNumber=1&pageSize=${page.getPageSize()}">首页</a>
    <#else>
    首页
    </#if>|<#if page.hasPrevious()>
    <a href="${action}?pageNumber=${page.getPreviousPageNumber()}&pageSize=${page.getPageSize()}">上一页</a>
    <#else>
    上一页
    </#if>|<#if page.hasNext()>
    <a href="${action}?pageNumber=${page.getNextPageNumber()}&pageSize=${page.getPageSize()}">下一页</a>
    <#else>
    下一页
    </#if>|<#if !page.isLast()>
    <a href="${action}?pageNumber=${page.getLastPageNumber()}&pageSize=${page.getPageSize()}">末页</a>
    <#else>
    末页
    </#if>
    <span>共有条<strong>${page.getTotalNumberOfElements()}</strong>结果,</span>
    <span><strong>${page.getPageSize()}</strong>条/页,</span>
    <span>共<strong>${page.getLastPageNumber()}</strong> 页</span>
    <span>这是第<strong>${page.getPageNumber()}</strong>页</span>
		<span>
			<input type="hidden" name="pageSize" value="${page.getPageSize()}"/>
				<span><select name="pageNumber">
                    <script>
                        for (var i = 1; i <=${page.getLastPageNumber()}; ++i)
                        {
                            if (i ==${page.getPageNumber()})
                                document.write("<option value='" + i + "' selected>" + i + "</option>");
                            else
                                document.write("<option value='" + i + "'>" + i + "</option>");
                        }
                    </script>
                </select></span>
				<span><input type="submit" value="跳转"/></span>
		</span>
</form></#macro>
