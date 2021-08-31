package database.mysql;

import dao.AbstractJDBCDAO;
import domain.CategoryGoods;
import exception.PersistException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class MySQLCategoryGoodsDAO extends AbstractJDBCDAO<CategoryGoods, Integer> {

    @Override
    public String getSearchCondition() {
        return "idCategoryGoods = ?";
    }
    
     private class PersistCategoryGoods extends CategoryGoods{
      public void setId(Integer id)
      {
          super.setIdCategoryGoods(id);
      }
    }
 
     public MySQLCategoryGoodsDAO(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
      return "SELECT `idCategoryGoods`, `Name` FROM categorygoods";
    }

    @Override
    public String getCreateQuery() {
      return "INSERT INTO categorygoods (Name) VALUES (?);";
    }

    @Override
    public String getUpdateQuery() {
      return "UPDATE categorygoods SET `Name` = ? WHERE `idCategoryGoods` = ?;";
    }

    @Override
    public String getDeleteQuery() {
      return "DELETE FROM categorygoods WHERE `idCategoryGoods` = ?;";  
    }

    @Override
    public String getNameIdInDB() {
       return "idCategoryGoods";
    }

    @Override
    protected List<CategoryGoods> parseResultSet(ResultSet rs) throws PersistException {
         LinkedList<CategoryGoods> result = new LinkedList<CategoryGoods>();
        try {
            while (rs.next()) {
                PersistCategoryGoods categoryGoods = new PersistCategoryGoods();
                categoryGoods.setId(rs.getInt("idCategoryGoods"));
                categoryGoods.setName(rs.getString("Name"));
                result.add(categoryGoods);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, CategoryGoods object) throws PersistException {
     try {
            statement.setString(1, object.getName());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, CategoryGoods object) throws PersistException {
       try {
            statement.setString(1, object.getName());
            statement.setInt(2, object.getId());
        } catch (Exception e) {
            throw new PersistException(e); 
     }
    } 

    @Override
    public CategoryGoods create() throws PersistException {
        CategoryGoods  cg = new CategoryGoods();
       return persist(cg);
    }
    
   
}
