package net.zonia3000.jsfcustomjsupdate.example;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import net.zonia3000.jsfcustomjsupdate.CustomUpdateResponseWriter;

/**
 *
 * @author zonia
 */
@Named("removableItemsBean")
@SessionScoped
public class RemovableItemsBean implements Serializable {
    
    private static final long serialVersionUID = -734958953707631664L;
    
    private List<String> myList;
    
    private String lastRemoved;
    
    @PostConstruct
    public void init() {
        myList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            myList.add("Item " + i);
        }
    }
    
    public List<String> getMyList() {
        return myList;
    }
    
    public void removeItem(int index) {
        lastRemoved = myList.remove(index);
        CustomUpdateResponseWriter.getCurrentInstance().renderElement("myform:custom-list");
    }
    
    public String getLastRemoved() {
        return lastRemoved;
    }
}
