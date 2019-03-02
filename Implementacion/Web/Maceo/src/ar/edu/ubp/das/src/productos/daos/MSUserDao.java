package ar.edu.ubp.das.src.productos.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.productos.forms.OfertasForm;
import ar.edu.ubp.das.src.users.forms.UserForm;
import ar.edu.ubp.das.security.jbcrypt.BCrypt;

public class MSUserDao extends DaoImpl {

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

		this.connect();
		
		this.setProcedure("dbo.getUserData(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		this.setParameter(1, form.getItem("idUsuario"));
		
		ResultSet result = this.getStatement().executeQuery();
		List<DynaActionForm> users  = new LinkedList<DynaActionForm>();
		if(result.next()) {
			UserForm user = new UserForm();
			user.setNombre(result.getString("nombre"));
			user.setApellido(result.getString("apellido"));
			user.setDni(result.getInt("dni"));
			user.setEmail(result.getString("email"));
			user.setIdUser(result.getInt("id_usuario"));
			user.setNombreUsuario(result.getString("usuario"));
			users.add(user);
		}
		return users;
	}
	
	@Override
	public DynaActionForm valid(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
