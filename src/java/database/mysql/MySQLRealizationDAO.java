package database.mysql;

import dao.AbstractJDBCDAO;
import domain.Goods;
import domain.Realization;
import exception.PersistException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;


public class MySQLRealizationDAO extends AbstractJDBCDAO<Realization, Integer> {

    private final String SQLExtrapolationAll = 
            "SELECT \n" +
            "r.`idRealization`, \n" +
            "r.`Date`, \n" +
            "SUM(r.`Amount`) Amount, \n" +
            "NULL as idGoods, \n" +
            "NULL as SKU, \n" +
            "NULL as `Name` \n" +
            "FROM realization r \n" +
            "JOIN goods g ON g.`idGoods` = r.`idGoods` \n" +
            "WHERE r.`date` between ? and ? \n"+
            "GROUP BY MONTH(date)";
            
    @Override
    public String getSearchCondition() {
        return "r.`date` between ? and ?";
    }

    private class PersistRealization extends Realization {
      public void setId(int id)
      {
          super.setIdRealization(id);
      }
    }

    public MySQLRealizationDAO(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
       return "SELECT r.`idRealization`, r.`Date`, r.`Amount`, r.`idGoods`, g.`SKU`, g.`Name` \n" +
              "FROM realization r\n" +
              "JOIN goods g ON  g.`idGoods` = r.`idGoods` ";
    }

    @Override
    public String getCreateQuery() {
      return "INSERT INTO realization (`Date`, `Amount`, `idGoods`) VALUES (?, ?, ?);";  
    }

    @Override
    public String getUpdateQuery() {
      return "UPDATE realization SET `Date` = ?, `Amount` = ?, `idGoods` = ? WHERE `idRealization` = ?;";   
    }

    @Override
    public String getDeleteQuery() {
      return "DELETE FROM realization WHERE `idRealization` = ?;";
    }

    @Override
    public String getNameIdInDB() {
      return "r.`idRealization`";  
    }

    @Override
    protected List<Realization> parseResultSet(ResultSet rs) throws PersistException {
          LinkedList<Realization> result = new LinkedList<Realization>();
        try {
            while (rs.next()) {
                Goods goods = new Goods();
                goods.setIdGoods(rs.getInt("idGoods"));
                goods.setName(rs.getString("Name"));
                goods.setSKU(rs.getInt("SKU"));
                
                PersistRealization realization = new PersistRealization();
                realization.setId(rs.getInt("idRealization"));
                realization.setRlztnDate(rs.getDate("Date"));
                realization.setAmount(rs.getInt("Amount"));
                realization.setGoods(goods);
                
                result.add(realization);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Realization object) throws PersistException {
         try {
             
            java.sql.Date RlztDate =  new java.sql.Date(object.getRlztnDate().getTime());
            statement.setDate(1, RlztDate);
            statement.setInt(2, object.getAmount());
            statement.setInt(3, object.getGoods().getIdGoods());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Realization object) throws PersistException {
        try {
            java.sql.Date RlztDate =  new java.sql.Date(object.getRlztnDate().getTime());
            statement.setDate(1, RlztDate);
            statement.setInt(2, object.getAmount());
            statement.setInt(3, object.getGoods().getIdGoods());
            statement.setInt(4, object.getId());
        } catch (Exception e) {
            throw new PersistException(e); 
     }
    }

    @Override
    public Realization create() throws PersistException {
        Realization r = new Realization();
       return persist(r);
    }
    
    @Override
    public List<Realization> getFind(String dateBegin, String dateEnd, int mode) throws PersistException {
        List<Realization> list;
       
        String sql = "";
 
        switch (mode){
            case 1: sql = SQLExtrapolationAll;
                 break;
            default:sql = getSelectQuery() + " WHERE " + getSearchCondition() + ";"; 
        }
            
        try (
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) 
        {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date parsed = format.parse(dateBegin);
                java.sql.Date sqlDateBegin = new java.sql.Date(parsed.getTime());
                parsed = format.parse(dateEnd);
                java.sql.Date slqDateEnd = new java.sql.Date(parsed.getTime());
                statement.setDate(1, sqlDateBegin);
                statement.setDate(2, slqDateEnd);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return list;
    }
    
}
