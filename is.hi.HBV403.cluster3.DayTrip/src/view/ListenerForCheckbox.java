package view;

import com.jfoenix.controls.JFXCheckBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import model.Filter;


public class ListenerForCheckbox implements ChangeListener {
    private JFXCheckBox checkBox;
    private String property;
    private ListController listController;
    public ListenerForCheckbox(ListController lc, JFXCheckBox cb, String prop) {
        this.checkBox = cb;
        this.property = prop;
        this.listController = lc;
    }
    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        Boolean bool = (Boolean)newValue;
        Filter myFilter = listController.getMyFilter();
        switch(this.property) {
            case "coupleFriendly":
                if(bool) {
                    myFilter.coupleFriendly = bool;
                }
                else {
                    myFilter.coupleFriendly = null;
                }
                break;
            case "wheelChairAccess":
                if(bool) {
                    myFilter.wheelChairAccess = bool;
                }
                else {
                    myFilter.wheelChairAccess = null;
                }
                break;
            case "groupFriendly":
                if(bool) {
                    myFilter.groupFriendly = bool;
                }
                else {
                    myFilter.groupFriendly = null;
                }
                break;
            case "familyFriendly":
                if(bool) {
                    myFilter.familyFriendly = bool;
                }
                else {
                    myFilter.familyFriendly = null;
                }
                break;
        }
        listController.setMyFilter(myFilter);
        listController.filterOut(myFilter);
    }
}
