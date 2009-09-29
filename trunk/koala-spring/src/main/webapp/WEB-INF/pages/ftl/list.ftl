<?xml version="1.0" encoding="UTF-8"/>
<#import "page.ftl" as p/>
<html>
<head>
    <title>list.ftl</title>
    <script src="${p.context}/resources/js/demo.js"></script>
</head>
<body>
<div id="listable">
    <table>
        <tr>
            <th style="display:none">文档编号</th>
            <th>标题</th>
            <th>栏目</th>
            <th>日期</th>
        </tr>
        <#list page.getThisPageElements()?if_exists as document>
        <tr class="listbg" onClick="this.className='listbgo'">
            <td>
                <label class="label">${document.id?if_exists}</label>
            </td>
            <td>
                <label class="label">${document.name?if_exists}</label>
            </td>
            <td>
                <label class="label">${document.version?if_exists}</label>
            </td>
        </tr>
        </#list>
    </table>
</div>
<div id="curspace">
    <div id="page"><@p.page page=page action="list.ftl"/></div>
</div>
</body>
</html>