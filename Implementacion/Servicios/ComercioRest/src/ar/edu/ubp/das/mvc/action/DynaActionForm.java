package ar.edu.ubp.das.mvc.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class DynaActionForm {

    private String              name;
    private Map<String, Object> items;
    
    public DynaActionForm() {
        this.name  = this.getClass().getName();
        this.items = new HashMap<String, Object>();
    }
    
    public void setName(String name) {
        this.name = name;        
    }
    
    public void setItem(String name, String value) {
        this.items.put(name, value);
    }

    public void setItem(String name, String[] value) {
        this.items.put(name, value);
    }
    
    public void removeItem(String name) {
        this.items.remove(name);
    }
    
    public int size() {
        return this.items.size();
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getItem(String name) {
        if(this.items.containsKey(name)) {
            return String.valueOf(this.items.get(name));
        }
        return null;
    }
    
    public String[] getItemValues(String name) {
        if(this.items.containsKey(name)) {
            return String[].class.cast(this.items.get(name));
        }
        return null;
    }
    
    public Map<String, Object> getItems() {
    	return this.items;
    }
    
	public void validate(ActionMapping mapping, HttpServletRequest request) throws RuntimeException {	}

	@Override
    public String toString() {
        String key;
        StringBuilder    form = new StringBuilder();        
        Iterator<String> keys = this.items.keySet().iterator();
        while(keys.hasNext()) {
            key = keys.next().toString();
            form.append("Key: ");
            form.append(key);
            form.append(" - Value: ");
            form.append(this.items.get(key));
            form.append("\n");
        }
        return form.toString();
    }
	
}
