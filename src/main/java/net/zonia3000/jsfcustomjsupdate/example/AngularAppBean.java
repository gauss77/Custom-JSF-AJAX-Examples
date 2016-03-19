package net.zonia3000.jsfcustomjsupdate.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import net.zonia3000.jsfcustomjsupdate.CustomUpdateResponseWriter;
import net.zonia3000.jsfcustomjsupdate.JSUpdateWriter;
import net.zonia3000.jsfcustomjsupdate.JavaScriptDataHandler;

/**
 *
 * @author zonia
 */
@Named("angularAppBean")
@SessionScoped
public class AngularAppBean implements Serializable {

    private static final long serialVersionUID = -3232363957383071231L;

    private static final transient Gson GSON = (new GsonBuilder()).serializeNulls().create();

    private AngularModel model;

    @PostConstruct
    public void init() {
        model = new AngularModel();
        model.setText("hello");
    }

    public void onLoad() {
        CustomUpdateResponseWriter.getCurrentInstance().addJavaScriptDataHandler(new JavaScriptDataHandler() {

            @Override
            public void manipulateJavaScriptObject(String jsData) {
                if (jsData != null) {
                    // Get JSON model from JavaScript and convert into a Java object
                    setModel(GSON.fromJson(jsData, AngularModel.class));
                }
            }
        });
    }

    public void processModel() {
        if (model.getText() != null) {
            model.setText(model.getText().toUpperCase());
        }

        CustomUpdateResponseWriter.getCurrentInstance().addCustomUpdate("myform:mypanel", new JSUpdateWriter() {

            @Override
            public String getUpdates() {
                // Return JSON model to update the AngularJS application
                return getJSONModel();
            }
        });
    }

    protected AngularModel getModel() {
        return model;
    }

    protected void setModel(AngularModel model) {
        this.model = model;
    }

    public String getJSONModel() {
        return GSON.toJson(model);
    }
}
