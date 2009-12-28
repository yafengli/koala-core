package cn.hpt.ui.component;

import cn.hpt.model.BillRecord;
import cn.hpt.ui.extend.HptFont;
import cn.hpt.ui.model.PriceIITabelModel;
import cn.hpt.ui.view.PriceIIDialog;
import cn.hpt.util.PrintConfig;
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
    private HptFont font;
    @Autowired
    private PriceIITabelModel tabelModel;
    @Autowired
    private PrintConfig printConfig;

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        Graphics2D g2 = (Graphics2D) graphics;
        makeGraphics(graphics);
        return PAGE_EXISTS;
    }


    /* test print image */
    public void testImage() {
        BufferedImage image = new BufferedImage(480, 320, BufferedImage.TYPE_INT_RGB);
        makeGraphics(image.getGraphics());
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

    private void makeGraphics(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setFont(font.getSize_12());
        //打印患者姓名
        g2.drawString("患者姓名：" + dialog.getUserNameField().getText(), Float.parseFloat(printConfig.getString("print.user.x")),
                Float.parseFloat(printConfig.getString("print.user.y")));
        //打印票据单号
        g2.drawString("单据编号：" + dialog.getIdNumField().getText(), Float.parseFloat(printConfig.getString("print.id.x")),
                Float.parseFloat(printConfig.getString("print.id.y")));
        //打印日期
        g2.drawString("收费日期：" + dialog.getIdDateField().getText(), Float.parseFloat(printConfig.getString("print.date.x")),
                Float.parseFloat(printConfig.getString("print.date.y")));
        //打印收款员
        g2.drawString("收款员：" + dialog.getOperatorField().getText(), Float.parseFloat(printConfig.getString("print.operator.x")),
                Float.parseFloat(printConfig.getString("print.operator.y")));
        //打印费用
        g2.drawString("费用：" + dialog.getRealaccField().getText(), Float.parseFloat(printConfig.getString("print.price.x")),
                Float.parseFloat(printConfig.getString("print.price.y")));
        //打印药物清单
        Float itemx = Float.parseFloat(printConfig.getString("print.medicine.x"));
        Float itemy = Float.parseFloat(printConfig.getString("print.medicine.y"));
        Float items = Float.parseFloat(printConfig.getString("print.medicine.space"));
        java.util.List<BillRecord> lbr = tabelModel.getItems();
        g2.drawString("项目名称", itemx, itemy);
        g2.drawString("项目单价", itemx + items, itemy);
        g2.drawString("项目次数", itemx + items + items, itemy);
        for (BillRecord item : lbr) {
            itemy += g2.getFont().getSize() + 1;
            g2.drawString(String.valueOf(item.getMedicine().getMname()), itemx, itemy);
            g2.drawString(String.valueOf(item.getMedicine().getPrice()), itemx + items, itemy);
            g2.drawString(String.valueOf(item.getBnumber()), itemx + items + items, itemy);
        }
    }
}
