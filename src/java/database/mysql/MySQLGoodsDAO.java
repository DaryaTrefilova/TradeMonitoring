package database.mysql;

import dao.AbstractJDBCDAO;
import domain.Goods;
import exception.PersistException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;


public class MySQLGoodsDAO extends AbstractJDBCDAO<Goods, Integer> {

    @Override
    public String getSearchCondition() {
        return "idGoods = ?";
    }

   
    private class PersistGoods extends Goods{
      public void setId(int id)
      {
          super.setIdGoods(id);
      }
    }
    
    @Override
    public Goods create() throws PersistException {
       Goods g = new Goods();
       return persist(g);
    }

    public MySQLGoodsDAO(Connection connection)
    {
        super(connection);
    }
   
     @Override
    public String getNameIdInDB() {
      return "idGoods";
    }
    
    @Override
    public String getSelectQuery() {
       return "SELECT `idGoods`, `SKU`, `Name` FROM Goods";
    }

    @Override
    public String getCreateQuery() {
        return  "INSERT INTO goods (SKU, Name) VALUES (?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE goods SET SKU = ?, Name = ? WHERE idGoods = ?;";
    }

    @Override
    public String getDeleteQuery() {
       return "DELETE FROM  goods WHERE idGoods = ?;";
    }

    @Override
    protected List<Goods> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Goods> result = new LinkedList<Goods>();
        
        try {
            while (rs.next()) {
                PersistGoods goods = new PersistGoods();
                goods.setId(rs.getInt("idGoods"));
                goods.setSKU(rs.getInt("SKU"));
                goods.setName(rs.getString("Name"));
                result.add(goods);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;

    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Goods object) throws PersistException {
      try {
            statement.setInt(1, object.getSKU());
            statement.setString(2, object.getName());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Goods object) throws PersistException {
        
        try {
            statement.setInt(1, object.getSKU());
            statement.setString(2, object.getName());
            statement.setInt(3, object.getId());
        } catch (Exception e) {
            throw new PersistException(e); 
     }
    }
}
