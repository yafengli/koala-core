<#import "/ftl/page.ftl" as p/>
<@p.page page=page action="getredo.action"/>
<table>
<tr>
        <th>栏目</th>
        <th>标题</th>
        <th>标题图</th>
        <th>发布人</th>
        <th>日期</th>
        <th style="display:none">部门</th>
        <th>状态</th>
</tr>
<#list page.getThisPageElements() as pl>
	<tr>
        <td  class="reverse1" >
        ${pl.document.reverse1?if_exists}
        </td>
        <td  class="intime">
        ${pl.document.intime?if_exists}
        </td>
        <td class="hidedept" style="display:none">
        <div id="hidedept" title="${pl.document.reverse2?if_exists}">${pl.document.reverse2?if_exists}</div>
        </td>
        <td class="taskname"  >
        ${pl.taskname?if_exists}
        </td>
    </tr>
</#list>
</table>
<@p.page page=page action="getredo.action"/>
