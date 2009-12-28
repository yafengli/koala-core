package cn.demo.scan.controller;

import cn.demo.dao.DemoPersonDao;
import cn.demo.pojo.DemoPerson;
import cn.demo.support.WebBeanDefinition;
import org.apache.log4j.Logger;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.jmesa.facade.TableFacade;
import org.jmesa.facade.TableFacadeFactory;
import org.jmesa.limit.ExportType;
import org.jmesa.limit.Limit;
import org.jmesa.limit.Sort;
import org.jmesa.view.editor.BasicCellEditor;
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.html.HtmlBuilder;
import org.jmesa.view.html.toolbar.HtmlToolbar;
import org.jmesa.view.html.component.HtmlTable;
import org.koala.page.ListPage;
import org.koala.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.context.annotation.Scope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;

/**
 * @author Administrator
 */
@Controller
@Scope(WebBeanDefinition.SCOPE_SESSION)
public class PageDemoController {

    Logger logger = Logger.getLogger(PageDemoController.class);
    @Autowired
    private DemoPersonDao demoPersonDaoImpl;

    @RequestMapping("/list.ftl")
    public ModelAndView list(@RequestParam(required = false, value = "pageNumber") String pageNumber,
                             @RequestParam(required = false, value = "pageSize") String pageSize,
                             @RequestParam(required = false, value = "sortName") String sortName,
                             @RequestParam(required = false, value = "orderName") String orderName) {
        ModelAndView mav = new ModelAndView("list");
        DemoPerson dp = demoPersonDaoImpl.findForObject("select count(*) as version from demo_person");
        Integer pn = pageNumber != null ? Integer.valueOf(pageNumber) : 1;
        Integer ps = pageSize != null ? Integer.valueOf(pageSize) : 15;
        String sqlToUse = String.format("select * from demo_person %s limit %s offset %s", fmCreateSortStr(sortName, orderName), ps, (pn - 1) * ps);
        logger.info(String.format("[%s]\n", sqlToUse));
        List<DemoPerson> pl = demoPersonDaoImpl.find(sqlToUse);

        try {
            Page page = new ListPage(pl, dp.getVersion(), Integer.valueOf(pageNumber), Integer.valueOf(pageSize));
            mav.addObject("page", page);
            String str = String.format("[pageNumber, pageSize, pl.size]is [%s, %s, %s, %s]", pageNumber, pageSize, pl.size(), page.getTotalNumberOfElements());
            mav.addObject("message", str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    @RequestMapping({"/jmesa.ftl", "/jmesa/ajax.ftl"})
    public ModelAndView demo(
            @RequestParam(required = false, value = "id") String id,
            HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("tab/jmesa");
        String views = render(id, request, response);
        if (views != null) {
            mav.addObject("views", views);
            return mav;
        } else {
            return null;
        }
    }

    @RequestMapping("/displaytag.ftl")
    public ModelAndView displayTag(
            @RequestParam(required = false, value = "id") String id,
            HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("tab/displaytag");
        String pageIndexName = new ParamEncoder("id").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
        Integer pageNumber = request.getParameter(pageIndexName) != null ? Integer.valueOf(request.getParameter(pageIndexName)) : 1;
        DemoPerson dp = demoPersonDaoImpl.findForObject("select count(*) as version from demo_person");
        String orderStr = displayTagCreateSortStr(request, "id");
        String sqlToUse = String.format("select * from demo_person %s limit %s offset %s", orderStr, 10, (pageNumber - 1) * 10);
        logger.info(String.format(
                "[sql,start,end][%s,%s,%s]\n", sqlToUse, pageNumber, (pageNumber - 1) * 10));
        Collection<DemoPerson> items = demoPersonDaoImpl.find(sqlToUse);
        request.setAttribute("test", items);
        mav.addObject("totalNumber", dp.getVersion());
        mav.addObject("pin", pageIndexName);
        mav.addObject("pageNumber", request.getParameter(pageIndexName));
        return mav;
    }

    private String render(String id, HttpServletRequest request, HttpServletResponse response) {
        /* create TableFacade*/
        TableFacade tableFacade = TableFacadeFactory.createTableFacade(id, request);
        tableFacade.setExportTypes(response, ExportType.CSV, ExportType.EXCEL);
        tableFacade.setColumnProperties("id", "name", "version");
        tableFacade.setStateAttr("restore");

        Limit limit = tableFacade.getLimit();
        /* set the TotalRows*/
        if (!limit.isComplete()) {
            DemoPerson dp = demoPersonDaoImpl.findForObject("select count(*) as version from demo_person");
            tableFacade.setTotalRows(dp.getVersion());
        }

        /*lookup the data of page,and set the data into tableFacade*/
        int start = limit.getRowSelect().getRowStart();
        int end = limit.getRowSelect().getRowEnd();

        String orderStr = jmesaCreateSortStr(limit);
        String sqlToUse = String.format(
                "select * from demo_person %s limit %d offset %d", orderStr, end - start, start);
         logger.info(String.format(
                "[sql,start,end][%s,%s,%s]\n", sqlToUse, start, end));

        Collection<DemoPerson> items = demoPersonDaoImpl.find(sqlToUse);
        tableFacade.setItems(items);

         /*set the page number toolbar*/
        HtmlToolbar toolbar = new HtmlToolbar();
        toolbar.enablePageNumbers(true);
        tableFacade.setToolbar(toolbar);

        /*export the file*/
        if (limit.isExported()) {
            tableFacade.getTable().getRow().getColumn("id").setTitle("标识");
            tableFacade.getTable().getRow().getColumn("name").setTitle("名称");
            tableFacade.getTable().getRow().getColumn("version").setTitle("版本");
            tableFacade.render();
            logger.info(String.format("[%s]\n", tableFacade.getTable().getRow().getColumns().size()));
            return null;
        } else {
            HtmlTable ht = (HtmlTable) tableFacade.getTable();
            ht.getRow().getColumn("id").getCellRenderer().setCellEditor(new CellEditor() {

                public Object getValue(Object item, String property, int rowcount) {
                    Object value = new BasicCellEditor().getValue(item, property, rowcount);
                    HtmlBuilder hb = new HtmlBuilder();
                    hb.a().href().quote().append("http://www.baidu.com").quote().close();
                    hb.append(value);
                    hb.aEnd();
                    return hb.toString();
                }
            });
            ht.getTableRenderer().setWidth("600px");
            return tableFacade.render();
        }
    }

    /**
     * create the string value of sort
     */
    private String jmesaCreateSortStr(Limit limit) {
        StringBuilder sb = new StringBuilder();
        Collection<Sort> cs = limit.getSortSet().getSorts();
        if (cs.size() > 0) {
            sb.append("order by ");
            for (Sort sort : cs) {
                sb.append(sort.getProperty());
                sb.append(" ");
                sb.append(sort.getOrder().toParam());
                sb.append(",");
            }
            return sb.toString().substring(0, sb.toString().length() - 1);
        }
        return "";
    }

    private String displayTagCreateSortStr(HttpServletRequest request, String idName) {

        String sortName = new ParamEncoder(idName).encodeParameterName(TableTagParameters.PARAMETER_SORT);
        String orderName = new ParamEncoder(idName).encodeParameterName(TableTagParameters.PARAMETER_ORDER);
        String sortValue = request.getParameter(sortName);
        String orderValue = request.getParameter(orderName);
        if (orderValue != null && orderValue.trim().length() > 0 && sortValue != null && sortValue.trim().length() > 0) {
            return String.format("order by %s %s", sortValue, orderValue.equalsIgnoreCase("1") ? "asc" : "desc");
        } else {
            return "";
        }
    }

    private String fmCreateSortStr(String sortName, String order) {
        if (sortName != null && order != null) {
            return String.format("order by %s %s", sortName, order);
        } else {
            return "";
        }
    }
}
