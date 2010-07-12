package cn.demo.scan.flow;

import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.FlowExecutionOutcome;
import org.springframework.webflow.mvc.servlet.AbstractFlowHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: phoenixup
 * Date: 2010-7-2
 * Time: 15:20:31
 */
@Component
public class TestFlowHandler extends AbstractFlowHandler{
    @Override
    public String handleExecutionOutcome(FlowExecutionOutcome outcome, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("[Hello World!]");
        return "main";
    }
}
