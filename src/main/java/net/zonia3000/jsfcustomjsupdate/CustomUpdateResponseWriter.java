package net.zonia3000.jsfcustomjsupdate;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.PartialResponseWriter;
import javax.faces.context.ResponseWriter;

/**
 * This class is responsable for writing the JSF AJAX response.
 *
 * @author zonia
 */
public class CustomUpdateResponseWriter extends PartialResponseWriter {

    private final Map<String, JSUpdateWriter> jsUpdatesWriters;
    private final Set<String> elementsToRender;
    private JavaScriptDataHandler javaScriptDataHandler;

    public CustomUpdateResponseWriter(ResponseWriter wrapped) {
        super(wrapped);
        jsUpdatesWriters = new HashMap<>();
        elementsToRender = new HashSet<>();
    }

    public void applyJavaScriptUpdates() {
        if (javaScriptDataHandler != null) {
            Map<String, String> requestMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            String customJSData = requestMap.get("customJSData");
            javaScriptDataHandler.manipulateJavaScriptObject(customJSData);
        }
    }

    @Override
    public void endDocument() throws IOException {
        FacesContext fctx = FacesContext.getCurrentInstance();

        // Add extension for JavaScript updates
        if (!jsUpdatesWriters.isEmpty()) {
            startExtension(Collections.singletonMap("id", "jsupdates"));
            for (Map.Entry<String, JSUpdateWriter> entry : jsUpdatesWriters.entrySet()) {
                startElement("jsupdate", null);
                writeAttribute("src", entry.getKey(), null);
                write(entry.getValue().getUpdates());
                endElement("jsupdate");
            }
            endExtension();
        }

        // Add extension for HTML updates
        if (!elementsToRender.isEmpty()) {
            startExtension(Collections.singletonMap("id", "htmlupdates"));
            for (String componentId : elementsToRender) {
                startElement("htmlupdate", null);
                writeAttribute("src", componentId, null);
                UIComponent component = fctx.getViewRoot().findComponent(componentId);
                component.encodeAll(fctx); // write components update in HTML
                endElement("htmlupdate");
            }
            endExtension();
        }

        super.endDocument();
    }

    /**
     * Call this method to specify which components should be rendered in HTML
     * in the custom response element
     *
     * @param componentId
     */
    public void renderElement(String componentId) {
        elementsToRender.add(componentId);
    }

    public void addJavaScriptDataHandler(JavaScriptDataHandler updateHandler) {
        this.javaScriptDataHandler = updateHandler;
    }

    public void addCustomUpdate(String componentId, JSUpdateWriter updateWriter) {
        jsUpdatesWriters.put(componentId, updateWriter);
    }

    public static CustomUpdateResponseWriter getCurrentInstance() {
        return (CustomUpdateResponseWriter) FacesContext.getCurrentInstance().getPartialViewContext().getPartialResponseWriter();
    }
}
