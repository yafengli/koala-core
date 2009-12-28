
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author YaFengLi
 */
public class TestServlet extends HttpServlet {

    private static StringBuilder fsb = new StringBuilder();

    static {
        fsb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        fsb.append("<RESPONSE>");
        fsb.append("<SERIAL_NO>%s</SERIAL_NO>");
        fsb.append("<RESULT>%s</RESULT>");
        fsb.append("<DESC>%s</DESC></RESPONSE>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            BufferedReader in = null;
            PrintWriter writer = resp.getWriter();
            in = new BufferedReader(new InputStreamReader(req.getInputStream()));
            String line = null;
            StringBuilder builder = new StringBuilder();
            while ((line = in.readLine()) != null) {
                builder.append(line);
            }
            Document doc = DocumentHelper.parseText(builder.toString());
            Element root = doc.getRootElement();
            Node action = root.selectSingleNode("ACTION");

            resp.setContentType("text/xml;charset=UTF-8");
            String str=String.format(fsb.toString(), 123123, 0, "successful");
            System.out.printf("Str:[%s]\n",str);
            writer.write(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
