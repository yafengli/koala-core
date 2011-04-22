package cn.hpt.ui.component;

import cn.hpt.bean.ParamStruct;
import cn.hpt.model.Operator;
import cn.hpt.ui.extend.HptFont;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.FormLayout;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ModifyInfoPanel extends JPanel {

    @Autowired
    private HptFont font;
    private static final long serialVersionUID = 8104192395718635289L;
    public static List<ParamStruct> psl = new ArrayList<ParamStruct>();

    static {
        psl.add(new ParamStruct("loginname", "用户名：", "用户名", 'P', 8,
                new JTextField()));
        psl.add(new ParamStruct("desc", "用户信息：", "用户信息", 'I', 15,
                new JTextField()));
        psl.add(new ParamStruct("cpassword", "原密码：", "原密码", 'C', 15,
                new JPasswordField()));
        psl.add(new ParamStruct("npassword", "新密码：", "新密码", 'N', 15,
                new JPasswordField()));
        psl.add(new ParamStruct("mpassword", "确认密码：", "确认密码", 'M', 15,
                new JPasswordField()));
    }

    @PostConstruct
    public void init() {
        setLayout(new BorderLayout());

        PanelBuilder builder = new PanelBuilder(new FormLayout(""));
        builder.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        builder.appendColumn("right:pref");
        builder.appendColumn("fill:max(pref; 100px)");
        builder.appendRow("top:pref");
        builder.addLabel(psl.get(0).getLabel());
        builder.nextColumn();
        builder.add(psl.get(0).getField());

        for (int i = 1; i < psl.size(); i++) {
            builder.appendRow("top:pref");
            builder.nextLine();
            JLabel label = new JLabel(psl.get(i).getLabel());
            builder.add(label);
            builder.nextColumn();
            builder.add(psl.get(i).getField());
        }

        /*
        FormLayout layout = new FormLayout(
        "right:max(40dlu;pref), 3dlu, 50dlu, 7dlu, "
        + "right:max(40dlu;pref), 3dlu, 50dlu",
        "p, 3dlu, p, 3dlu, p, 3dlu, p, 9dlu, "
        + "p, 3dlu, p, 3dlu, p, 3dlu, p, 9dlu, "
        + "p, 3dlu, p, 3dlu, p, 3dlu, p");

        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();

        
        for (final ParamStruct ps : psl) {
        builder.appendRow("top:pref");
        builder.addLabel(ps.getLabel());
        builder.nextColumn(2);
        builder.add(ps.getField());
        builder.nextLine(2);
        }
         */
        add(builder.getPanel(), BorderLayout.CENTER);
    }

    public void load(Operator operator) {
        for (final ParamStruct ps : psl) {
            final JTextField field = ps.getField();
            Object obj = readField(operator, ps.getLabelName());

            field.setText(obj != null ? obj.toString() : null);
            OperatorInoAdapter adapter = new OperatorInoAdapter(operator, ps,
                    field);
            field.addKeyListener(adapter);
            field.addFocusListener(adapter);
            field.getDocument().addDocumentListener(adapter);
        }
    }

    private void writeField(Object obj, String labelName, Object... args) {
        try {
            BeanInfo info = Introspector.getBeanInfo(Operator.class);
            for (PropertyDescriptor pd : info.getPropertyDescriptors()) {

                if (pd.getName().equalsIgnoreCase(labelName)) {
                    try {
                        Method wd = pd.getWriteMethod();
                        wd.invoke(obj, args);
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object readField(Object obj, String labelName) {
        try {
            BeanInfo info = Introspector.getBeanInfo(Operator.class);
            for (PropertyDescriptor pd : info.getPropertyDescriptors()) {

                if (pd.getName().equalsIgnoreCase(labelName)) {
                    try {
                        Method rd = pd.getReadMethod();
                        Object value = rd.invoke(obj, new Object[0]);
                        return value;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    class OperatorInoAdapter extends KeyAdapter implements FocusListener,
            DocumentListener {

        private Operator operator;
        private ParamStruct ps;
        private JTextField field;

        public OperatorInoAdapter(Operator operator, ParamStruct ps,
                JTextField field) {
            this.operator = operator;
            this.ps = ps;
            this.field = field;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_ENTER) {
                field.transferFocus();
            }
        }

        @Override
        public void focusGained(FocusEvent e) {
        }

        @Override
        public void focusLost(FocusEvent e) {
            persist();

        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            persist();

        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            persist();

        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            persist();
        }

        private void persist() {
            writeField(operator, ps.getLabelName(), field.getText());
        }
    }
}
