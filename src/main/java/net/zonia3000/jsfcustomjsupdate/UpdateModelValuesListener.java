package net.zonia3000.jsfcustomjsupdate;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *
 * @author zonia
 */
public class UpdateModelValuesListener implements PhaseListener {
    
    private static final long serialVersionUID = 4100250396609446232L;

    @Override
    public void afterPhase(PhaseEvent event) {
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        CustomUpdateResponseWriter.getCurrentInstance().applyJavaScriptUpdates();
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.UPDATE_MODEL_VALUES;
    }
}
