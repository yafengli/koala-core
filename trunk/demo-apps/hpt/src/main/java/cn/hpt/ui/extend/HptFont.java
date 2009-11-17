package cn.hpt.ui.extend;

import cn.hpt.util.PropertiesLoader;
import java.awt.Font;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HptFont {

    @Autowired
    private PropertiesLoader pl;
    private Font size_14;
    private Font size_12;
    private Font size_12_b;
    private Font size_18_b;
    private static final long serialVersionUID = -3230435859900844074L;

    public HptFont() {
    }

    @PostConstruct
    private void init() {
        size_14 = new Font(pl.getString("font.dialog"), Font.PLAIN, 14);
        size_12 = new Font(pl.getString("font.dialog"), Font.PLAIN, 12);
        size_12_b = new Font(pl.getString("font.dialog"), Font.BOLD, 12);
        size_18_b = new Font(pl.getString("font.dialog"), Font.BOLD, 18);
    }

    public Font getSize_14() {
        return size_14;
    }

    public Font getSize_12() {
        return size_12;
    }

    public Font getSize_18_b() {
        return size_18_b;
    }

    public Font getSize_12_b() {
        return size_12_b;
    }
}
