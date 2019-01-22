package ar.edu.ubp.das.src.users.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.users.forms.CategoriasForm;

public class MSCategoriasDao extends DaoImpl {

	@Override
	public DynaActionForm make(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		this.connect();
		
		this.setProcedure("dbo.getCategorias", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

    	List<DynaActionForm> categorias = new LinkedList<DynaActionForm>();
    	
    	CategoriasForm categoria;
    	
    	ResultSet result = this.getStatement().executeQuery();
  
        while(result.next()) {
        	categoria = new CategoriasForm();
        	categoria.setIdCategoria(result.getInt("id_categoria"));
        	categoria.setImageURL(result.getString("image_url"));
        	categoria.setNombre(result.getString("nombre"));
        	categoria.setLang(result.getString("lang"));
            categorias.add(categoria);
        }
        
		this.disconnect();
		
		return categorias;
	}

	@Override
	public boolean valid(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
