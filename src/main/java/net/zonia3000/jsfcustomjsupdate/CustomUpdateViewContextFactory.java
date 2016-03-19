package net.zonia3000.jsfcustomjsupdate;

import javax.faces.context.FacesContext;
import javax.faces.context.PartialViewContext;
import javax.faces.context.PartialViewContextFactory;

/**
 *
 * @author zonia
 */
public class CustomUpdateViewContextFactory extends PartialViewContextFactory {

    private final PartialViewContextFactory wrapped;

    public CustomUpdateViewContextFactory(PartialViewContextFactory wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public PartialViewContext getPartialViewContext(FacesContext context) {
        return new CustomUpdateViewContext(wrapped.getPartialViewContext(context));
    }
}
