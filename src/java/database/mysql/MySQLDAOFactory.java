package database.mysql;

import dao.DaoFactory;
import dao.GenericDAO;
import domain.CategoryGoods;
import domain.Goods;
import domain.GoodsOnCategory;
import domain.Realization;
import exception.PersistException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class MySQLDAOFactory implements DaoFactory<Connection>{

    private String user = "root";//Логин пользователя
    private String password = "";//Пароль пользователя
    private String url = "jdbc:mysql://localhost:3306/extrapolation";//URL адрес
    private String driver = "com.mysql.jdbc.Driver";//Имя драйвера
    
    private Map<Class, DaoCreator> creators;
    
   public Connection getContext() throws PersistException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return  connection;
    }

    @Override
    public GenericDAO getDAO(Connection connection, Class dtoClass) throws PersistException {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistException("DAO объект для " + dtoClass + " не найден.");
        }
        return creator.create(connection);
    }

    public MySQLDAOFactory() {
        try {
            Class.forName(driver);//Регистрируем драйвер
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        creators = new HashMap<Class, DaoCreator>();
        creators.put(Goods.class, new DaoCreator<Connection>() {
            @Override
            public GenericDAO create(Connection connection) {
                return new MySQLGoodsDAO(connection);
            }
        });
        
        creators.put(CategoryGoods.class, new DaoCreator<Connection>() {
            @Override
            public GenericDAO create(Connection connection) {
                return new MySQLCategoryGoodsDAO(connection);
            }
        });
        
         creators.put(Realization.class, new DaoCreator<Connection>() {
            @Override
            public GenericDAO create(Connection connection) {
                return new MySQLRealizationDAO(connection);
            }
        });
         
        creators.put(GoodsOnCategory.class, new DaoCreator<Connection>() {
            @Override
            public GenericDAO create(Connection connection) {
                return new MySQLGoodsOnCategoryDAO(connection);
            }
        });
        
    }
}