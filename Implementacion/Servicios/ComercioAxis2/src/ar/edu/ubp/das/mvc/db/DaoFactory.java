package ar.edu.ubp.das.mvc.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class DaoFactory {

    private static Properties propFile = new Properties();
    private static boolean    loadProp = false;

    private DaoFactory() {}

    public static Dao getDao(String daoName, String daoPackage) throws SQLException {
    	return DaoFactory.getDao(daoName, daoPackage, "default");	
    }
    
    public static Dao getDao(String daoName, String daoPackage, String datasourceId) throws SQLException {
        try {
        	DaoImpl dao = DaoImpl.class.cast(Class.forName(DaoFactory.getDaoClassName(daoName, daoPackage)).newInstance());
            return dao;            
        }
        catch(InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException | SecurityException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    private static String getDaoClassName(String daoName, String daoPackage) throws SQLException {
        try {
        	if(!DaoFactory.loadProp) {
                InputStream file = DaoFactory.class.getResourceAsStream("DaoFactory.properties");
                DaoFactory.propFile.load(file);
                file.close();

                DaoFactory.loadProp = true;
            }
            return daoPackage + ".daos." + DaoFactory.propFile.getProperty("CurrentDaoFactory") + daoName + "Dao";
        }
        catch(IOException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

}
