package cn.hpt.ui.component;

import cn.hpt.model.BillRecord;
import cn.hpt.ui.extend.HptFont;
import cn.hpt.ui.model.PriceIITabelModel;
import cn.hpt.ui.view.PriceIIDialog;
import cn.hpt.util.PropertiesLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.List;
import java.io.File;

/**
 * Date: 2009-9-30
 * Time: 10:17:35
 *
 * @version 1.0
 * @authtor YaFengLi
 */
@Service
public class PrintPanel extends JPanel implements Printable {

    @Autowired
    private PriceIIDialog dialog;
    @Autowired
    private PropertiesLoader pl;
    @Autowired
    private HptFont font;
    @Autowired
    private PriceIITabelModel tabelModel;
    @Autowired
    private PropertiesLoader propertiesLoader;

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setPaint(Color.black); // 设置打印颜色为黑色
        if (pageIndex >= 1) // 当打印页号大于需要打印的总页数时，打印工作结束
        {
            return Printable.NO_SUCH_PAGE;
        }
        g2.setFont(font.getSize_12());
        //打印患者姓名
        g2.drawString(dialog.getUserNameField().getText(), Float.parseFloat(pl.getString("print.user.x")), Float.parseFloat(pl.getString("print.user.y")));
        //打印日期
        g2.drawString(dialog.getIdDateField().getText(), Float.parseFloat(pl.getString("print.date.x")), Float.parseFloat(pl.getString("print.date.y")));
        //打印收款员
        g2.drawString(dialog.getOperatorField().getText(), Float.parseFloat(pl.getString("print.operator.x")), Float.parseFloat(pl.getString("print.operator.y")));
        //打印费用
        g2.drawString(dialog.getRealaccField().getText(), Float.parseFloat(pl.getString("print.price.x")), Float.parseFloat(pl.getString("print.price.y")));
        //打印药物清单
        Float itemx = Float.parseFloat(pl.getString("print.medicine.x"));
        Float itemy = Float.parseFloat(pl.getString("print.medicine.y"));
        List<BillRecord> lbr = tabelModel.getItems();
        for (BillRecord item : lbr) {
            g2.drawString(String.format("[%s  %s  %s]", item.getMedicine() != null ? item.getMedicine().getMname() : "",
                    item.getMedicine() != null ? item.getMedicine().getPrice() : "", item.getBnumber()), itemx, itemy);
            itemy += g2.getFont().getSize() + 1;
        }

        return PAGE_EXISTS;
    }


    /* test print image */
    public void testImage(){
        BufferedImage image = new BufferedImage(400, 300, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        g2.setFont(new Font("宋体", 0, 12));
        //打印患者姓名
        g2.drawString(dialog.getUserNameField().getText(), Float.parseFloat(propertiesLoader.getString("print.user.x")), Float.parseFloat(propertiesLoader.getString("print.user.y")));
        //打印日期
        g2.drawString(dialog.getIdDateField().getText(), Float.parseFloat(propertiesLoader.getString("print.date.x")), Float.parseFloat(propertiesLoader.getString("print.date.y")));
        //打印收款员
        g2.drawString(dialog.getOperatorField().getText(), Float.parseFloat(propertiesLoader.getString("print.operator.x")), Float.parseFloat(propertiesLoader.getString("print.operator.y")));
        //打印费用
        g2.drawString(dialog.getRealaccField().getText(), Float.parseFloat(propertiesLoader.getString("print.price.x")), Float.parseFloat(propertiesLoader.getString("print.price.y")));
        //打印药物清单
        Float itemx = Float.parseFloat(propertiesLoader.getString("print.medicine.x"));
        Float itemy = Float.parseFloat(propertiesLoader.getString("print.medicine.y"));
        java.util.List<BillRecord> lbr = tabelModel.getItems();
        for (BillRecord item : lbr) {
            g2.drawString(String.format("[%s  %s  %s]", item.getMedicine() != null ? item.getMedicine().getMname() : "",
                    item.getMedicine() != null ? item.getMedicine().getPrice() : "", item.getBnumber()), itemx, itemy);
            itemy += g2.getFont().getSize() + 1;
        }
        try {
            File f = new File("D:/hello.gif");
            if (!f.exists()) {
                f.createNewFile();
            }
            ImageIO.write(image, "gif", f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
