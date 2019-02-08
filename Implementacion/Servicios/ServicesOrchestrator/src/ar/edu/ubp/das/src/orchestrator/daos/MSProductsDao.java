package ar.edu.ubp.das.src.orchestrator.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.orchestrator.forms.ProductForm;

public class MSProductsDao extends DaoImpl {

	@Override
	public DynaActionForm make(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		try {
			System.out.println("AAAA");
        	this.connectWAutoFalse();
    		this.setProcedure("dbo.saveProducto(?,?,?,?,?,?,?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
//    		@nombre            	varchar(255),
//    	    @marca            	varchar(255),
//    	    @modelo            	varchar(255),
//    		@modeloNativo		varchar(255),
//    	    @idCategoria      	smallint,
//    		@idComercio		SMALLINT,
//    		@precio				float,
//    		@urlProducto		varchar (500),
//    		@imageUrl           varchar (500)
    		ProductForm producto = (ProductForm)form;
    		this.setParameter(1, producto.getNombre());
    		this.setParameter(2, producto.getMarca());
    		this.setParameter(3, producto.getModelo());
    		this.setParameter(4, producto.getNativeModelo());
    		this.setParameter(5, producto.getIdCategoria());
    		this.setParameter(6, producto.getIdComercio());
    		this.setParameter(7, producto.getPrecio());
    		this.setParameter(8, producto.getProdURL());
    		this.setParameter(9, producto.getImgURL());
    		
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

	}

	@Override
	public void delete(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		try {
        	this.connectWAutoFalse();

    		this.setProcedure("dbo.disableProducts", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

    		this.getStatement().execute();		
    		this.getStatement().close();
    		
			this.commit();
			
			System.out.println("Productos deshabilitados");
			
		} catch (SQLException e) {
			e.printStackTrace();
			this.rollback();
			throw e;
		}finally {
			this.autoCommit(true);
			this.disconnect();
		}
	}

	@Override
	public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean valid(DynaActionForm form) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
