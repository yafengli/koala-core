package com.qt.datapicker;

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

public class TestDayPicker {

    public static void main(String argv[]) {
        try {
            UIManager
                    .setLookAndFeel("com.jgoodies.plaf.plastic.PlasticXPLookAndFeel");
        } catch (Exception e) {
            // Likely PlasticXP is not in the class path; ignore.
        }
        System.out.println("Usage: java -jar DatePacker.jar [ISO Language Code, e.g. de]");
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
        String lang = null;
        if(argv.length>0)
            lang=argv[0];
        final Locale locale=getLocale(lang);
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
            };
        });
        frame.setVisible(true);
    }
    private static Locale getLocale(String loc)
    {
        if(loc!=null && loc.length()>0)
            return new Locale(loc);
        else
            return Locale.CHINESE;
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
