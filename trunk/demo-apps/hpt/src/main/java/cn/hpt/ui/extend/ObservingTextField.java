package cn.hpt.ui.extend;

import com.qt.datapicker.DatePicker;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTextField;

/**
 *
 * @author Administrator
 */
public class ObservingTextField extends JTextField implements Observer {

    public void update(Observable o, Object arg) {
        Calendar calendar = (Calendar) arg;
        DatePicker dp = (DatePicker) o;
        String val = dp.formatDate(calendar, "yyyy-MM-dd HH:mm:ss");
        System.out.println("picked=" + val);
        setText(val);
    }
}
