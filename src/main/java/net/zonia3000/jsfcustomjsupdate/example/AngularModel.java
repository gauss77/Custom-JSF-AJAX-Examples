package net.zonia3000.jsfcustomjsupdate.example;

import java.io.Serializable;

/**
 *
 * @author zonia
 */
public class AngularModel implements Serializable {

    private static final long serialVersionUID = -8796350042772263437L;

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
