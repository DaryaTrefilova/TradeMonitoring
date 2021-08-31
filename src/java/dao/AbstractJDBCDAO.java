package dao;

import domain.Goods;
import exception.PersistException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;


public abstract class  AbstractJDBCDAO <T extends Identified<PK>, PK extends Integer> implements GenericDAO<T, PK>{
    
    private Connection connection;
    
    /** CRUD операци**/
    public abstract String getSelectQuery();
    public abstract String getCreateQuery();
    public abstract String getUpdateQuery();
    public abstract String getDeleteQuery();
    public abstract String getNameIdInDB();
    public abstract String getSearchCondition();
   
    protected abstract List<T> parseResultSet(ResultSet rs) throws PersistException;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws PersistException;
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws PersistException;

    public Connection getConnection (){
     return connection;
    }
    
    @Override
    public T getByPK(int key) throws PersistException {
        List<T> list;
        
        String sql = getSelectQuery();
        sql += " WHERE " + getNameIdInDB() + " = ?;"; //Обрати внимание
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        if (list == null || list.isEmpty()) {
            return null;
        }
        if (list.size() > 1) {
            throw new PersistException("Получено несколько записей.");
        }
        return list.iterator().next();
    }
    
    @Override
    public List<T> getFind(int key) throws PersistException {
        List<T> list;
        
        String sql = getSelectQuery();
        sql += " WHERE " + getSearchCondition() + ";";
  
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return list;
    }
    
     @Override
     public List<T> getFind(String dateBegin, String dateEnd, int mode) throws PersistException  {
        return null;   
     }
    
    @Override
    public List<T> getAll() throws PersistException {
        List<T> list;
        String sql = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return list;
    }

    @Override
    public T persist(T object) throws PersistException {
     
        if (object.getId() != 0) {
            throw new PersistException("Объект уже сохранён.");
        }
        
        T persistInstance;
                            
        String sql = getCreateQuery();
     
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("Добавлено более одной записи: " + count);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
       
        sql = getSelectQuery() + " WHERE " + getNameIdInDB() + "= last_insert_id();";
       
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            List<T> list = parseResultSet(rs);
            if ((list == null) || (list.size() != 1)) {
                throw new PersistException("Сохранённая запись не найдена");
            }
            persistInstance = list.iterator().next();
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return persistInstance;
    }

    @Override
    public void update(T object) throws PersistException {
        String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            prepareStatementForUpdate(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("Обновлено более одной записи: " + count);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public void delete(T object) throws PersistException {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            
            try {
                statement.setObject(1, object.getId());
            } catch (Exception e) {
                throw new PersistException(e);
            }
            
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("Удалено более одной записи: " + count);
            }
            statement.close();
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    public AbstractJDBCDAO(Connection connection) {
        this.connection = connection;
    }
}
