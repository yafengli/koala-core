package test;

import com.qt.datapicker.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import org.junit.*;

public class PickerTest {

    @Test
    public void view() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            System.out.println("###########ONE");
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    System.out.println("##########TWO");
                    final JFrame frame = new JFrame("Test Date Picker");
                    frame.getContentPane().setLayout(new FlowLayout());
                    frame.setSize(300, 150);
                    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                    frame.setLocation((int) (dim.getWidth() - frame.getWidth()) / 2,
                            (int) (dim.getHeight() - frame.getHeight()) / 2);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    // create a text-field that implements Observer
                    final ObservingTextField textField = new ObservingTextField();
                    textField.setColumns(10);
                    textField.setText("");
                    textField.setToolTipText("This is a text field that implments Observer interface.");
                    frame.getContentPane().add(textField);

                    // create a button
                    String lang = "zh_CN";
                    final Locale locale = getLocale(lang);
                    final JButton btn = new JButton("Pick Date");
                    frame.getContentPane().add(btn);
                    btn.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            // instantiate the DatePicker
                            DatePicker dp = new DatePicker(textField, locale);
                            // previously selected date
                            Date selectedDate = dp.parseDate(textField.getText());
                            dp.setSelectedDate(selectedDate);
                            dp.start(textField);
                        }
                    });
                    frame.setVisible(true);
                }
            });
            Thread.sleep(30000L);
        } catch (Exception e) {
            e.printStackTrace();
            // Likely PlasticXP is not in the class path; ignore.
        }
    }

    private static Locale getLocale(String loc) {
        if (loc != null && loc.length() > 0) {
            return new Locale(loc);
        } else {
            return Locale.CHINESE;
        }
    }
}

class ObservingTextField extends JTextField implements Observer {

    public void update(Observable o, Object arg) {
        Calendar calendar = (Calendar) arg;
        DatePicker dp = (DatePicker) o;
        System.out.println("picked=" + dp.formatDate(calendar));
        setText(dp.formatDate(calendar));
    }
}
