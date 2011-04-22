<#macro page page action >
        <form action="${action}" method="POST">
                <#if page.isFirst()>
                <#else>
                <span style="font-size:12px;color:blue">
                <a href="${action}?pageNumber=${page.getPreviousPageNumber()}&pageSize=${page.getPageSize()}">上页</a>
                </span>
                </#if>
                <span style="font-size:12px;color:blue">第<span style="font-size:12px;color:blue">${page.getThisPageNumber()}</span>页</span>
                <#if page.isLast()>
                <#else>
                <span style="font-size:12px;color:blue">
                <a href="${action}?pageNumber=${page.getNextPageNumber()}&pageSize=${page.getPageSize()}">下页</a>
                </span>
                </#if>
                <span style="font-size:12px;color:blue">共 ${page.getLastPageNumber()} 页</span>
                <span style="font-size:12px;color:blue"> ${page.getPageSize()}条/页 </span>
                <span style="font-size:12px;color:blue">
                        <input type="hidden" name="pageSize" value="30"/>
                                <span><select name="pageNumber">
                                <script>
                                for(var i=1;i<=${page.getLastPageNumber()};++i)
                                {
                                        document.write("<option value='"+i+"'>"+i+"</option>");
                                }
                                </script>
                                </select></span>
                                <span><input type="submit" value="跳转"/></span>
                </span>
        </form>
</#macro>