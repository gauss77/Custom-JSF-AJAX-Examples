package net.zonia3000.jsfcustomjsupdate.example;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import net.zonia3000.jsfcustomjsupdate.CustomUpdateResponseWriter;
import net.zonia3000.jsfcustomjsupdate.JSUpdateWriter;

/**
 *
 * @author zonia
 */
@Named("inputTextBean")
@SessionScoped
public class CustomInputTextBean implements Serializable {

    private static final long serialVersionUID = -1173141786059776385L;

    private String initialValue, textValue;

    @PostConstruct
    public void init() {
        textValue = "edit me!";
        initialValue = textValue;
    }

    public boolean isTextChanged() {
        return !initialValue.equals(textValue);
    }

    public void textUpdated() {
        CustomUpdateResponseWriter.getCurrentInstance().addCustomUpdate("myform:myinput", new JSUpdateWriter() {

            @Override
            public String getUpdates() {
                return isTextChanged() + "";
            }
        });
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }
}
