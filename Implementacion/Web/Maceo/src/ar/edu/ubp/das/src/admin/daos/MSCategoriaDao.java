package ar.edu.ubp.das.src.admin.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Types;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.admin.forms.CategoriaTransForm;
import ar.edu.ubp.das.src.productos.forms.CategoriasForm;

public class MSCategoriaDao extends DaoImpl {

	@Override
	public DynaActionForm make(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		try {
        	this.connectWAutoFalse();

//        	Guardo el comercio
    		this.setProcedure("dbo.saveCategoria(?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

    		this.setParameter(1, form.getItem("spanish"));
    		this.setParameter(2, form.getItem("english"));
    		this.setParameter(3, form.getItem("imageURL"));
    		
    		this.getStatement().execute();		
     	
			this.commit();
		} catch (SQLException e) {
			this.rollback();
			throw e;
		}finally {
			this.autoCommit(true);
			this.disconnect();
		}
	}

	@Override
	public void update(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		try {
        	this.connectWAutoFalse();

//        	Guardo el comercio
        	this.setProcedure("dbo.updateCategoria(?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        	this.setParameter(1, Integer.parseInt(form.getItem("idCategoria")));
        	String spanishStr = form.getItem("spanish");
    		this.setParameter(2, spanishStr);
    		this.setParameter(3, form.getItem("imageURL"));
    		
    		this.getStatement().execute();
    		
    		this.setProcedure("dbo.updateTranslationCategoria(?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

    		this.setParameter(1, Integer.parseInt(form.getItem("idCategoria")));
    		this.setParameter(2, "es");
    		this.setParameter(3, spanishStr);
    		
    		this.getStatement().execute();

    		this.setProcedure("dbo.updateTranslationCategoria(?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

    		this.setParameter(1, Integer.parseInt(form.getItem("idCategoria")));
    		this.setParameter(2, "en");
    		this.setParameter(3, form.getItem("english"));
    		
    		this.getStatement().execute();		
     	
			this.commit();
		} catch (SQLException e) {
			this.rollback();
			throw e;
		}finally {
			this.autoCommit(true);
			this.disconnect();
		}
	}

	@Override
	public void delete(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		this.connect();
		
		this.setProcedure("dbo.getCategoriasAdmin(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		if(form.getItem("idCategoria")!=null) {
			this.setParameter(1, form.getItem("idCategoria"));
		}else {
			this.setNull(1, Types.INTEGER);
		}
		
		List<DynaActionForm> categorias = new LinkedList<DynaActionForm>();
		
    	CategoriasForm categoria;
    	
    	CategoriaTransForm categoriaTranslated;
    	Integer idCategoria;
    	Map<String, String> translations;
  
    	ResultSet result = this.getStatement().executeQuery();
    	result.next();
        while(result.getRow() > 0) {
        	idCategoria = result.getInt("id_categoria");
        	
        	categoriaTranslated = new CategoriaTransForm();
        	categoriaTranslated.setIdCategoria(result.getInt("id_categoria"));
        	categoriaTranslated.setHabilitada(result.getBoolean("habilitado"));
        	categoriaTranslated.setImageURL(result.getString("image_url"));
        	if(form.getItem("idCategoria")!=null) {
        		this.setProcedure("dbo.checkHabilitable(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
    			this.setParameter(1, form.getItem("idCategoria"));
    			
    			ResultSet result2 = this.getStatement().executeQuery();
    			if(!result2.next()) {
    				categoriaTranslated.setIsQualifiable(true);
    			}else {
    				categoriaTranslated.setIsQualifiable(false);
    			}
    			result2.close();
    		}else {
    			categoriaTranslated.setIsQualifiable(false);
    		}
        	
        	translations = new HashMap<String, String>();
        	
        	while(result.getRow() > 0 && idCategoria == result.getInt("id_categoria")) {
            	categoria = new CategoriasForm();
            	translations.put(result.getString("lang"), result.getString("nombre"));
        		result.next();
            }
        	categoriaTranslated.setTranslations(translations);
            categorias.add(categoriaTranslated);
        }
    	
    	
		this.disconnect();
		
		return categorias;
	}

	@Override
	public DynaActionForm valid(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
