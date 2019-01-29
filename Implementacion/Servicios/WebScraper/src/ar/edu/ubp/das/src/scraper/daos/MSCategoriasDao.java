package ar.edu.ubp.das.src.scraper.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.scraper.forms.CategoriaForm;

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
		
		this.setProcedure("dbo.getCategoriasLang(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

		this.setParameter(1, "es"); //Solo obtener las de 1 idioma para evitar repetidas
		
    	List<DynaActionForm> categorias = new LinkedList<DynaActionForm>();
    	
    	CategoriaForm categoria;
    	
    	ResultSet result = this.getStatement().executeQuery();
  
        while(result.next()) {
        	categoria = new CategoriaForm();
        	categoria.setID(result.getInt("id_categoria"));
        	categoria.setNombre(result.getString("nombre"));
            categorias.add(categoria);
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
