package net.zonia3000.jsfcustomjsupdate;

import javax.faces.context.PartialResponseWriter;
import javax.faces.context.PartialViewContext;
import javax.faces.context.PartialViewContextWrapper;

/**
 * Thanks to http://stackoverflow.com/a/12198117
 * 
 * @author zonia
 */
public class CustomUpdateViewContext extends PartialViewContextWrapper {

    private final PartialViewContext wrapped;
    private final PartialResponseWriter writer;

    public CustomUpdateViewContext(PartialViewContext wrapped) {
        this.wrapped = wrapped;
        this.writer = new CustomUpdateResponseWriter(wrapped.getPartialResponseWriter());
    }

    @Override
    public PartialResponseWriter getPartialResponseWriter() {
        return writer;
    }

    @Override
    public void setPartialRequest(boolean isPartialRequest) {
        wrapped.setPartialRequest(isPartialRequest);
    }

    @Override
    public PartialViewContext getWrapped() {
        return wrapped;
    }
}
