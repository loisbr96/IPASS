package nl.hu.ipass.persistence;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

/*In deze class wordt de connectie gemaakt met Mysql: */
public class mysqlBaseDao {
    protected final Connection getConnection(){
        Connection result = null;

        try{
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/MysqlDS");

            result = ds.getConnection();;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return result;
    }
}
