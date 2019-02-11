package ar.edu.ubp.das.src.orchestrator.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.orchestrator.forms.ConfigsForm;

public class MSConfigDao extends DaoImpl {

	@Override
	public DynaActionForm make(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(DynaActionForm form) throws SQLException {
	
	}

	@Override
	public void update(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(DynaActionForm form) throws SQLException {
	
	}

	@Override
	public List<DynaActionForm> select(DynaActionForm form) throws SQLException {		
		this.connect();
		this.setProcedure("dbo.getOrchConfig", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				
		List<DynaActionForm> configs = new LinkedList<DynaActionForm>();
  
    	ResultSet result = this.getStatement().executeQuery();
    	
    	ConfigsForm config = new ConfigsForm();
    	
    	List<String> regex = new LinkedList<String>();
    	List<String> words = new LinkedList<String>();
    	List<String> cssClasses = new LinkedList<String>();
    	
        while(result.next()) {
        	if(result.getInt("tipo_config") == 1) {
        		regex.add(result.getString("palabra_ignorar"));
        	}else if (result.getInt("tipo_config") == 2) {
        		words.add(result.getString("palabra_ignorar"));
        	}else {
        		cssClasses.add(result.getString("palabra_ignorar"));
        	}
        	
        } 	
        config.setRegex(regex);
        config.setCssClasses(cssClasses);
        config.setWords(words);
        
        configs.add(config);
		this.disconnect();
		
		return configs;
	}

	@Override
	public boolean valid(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
