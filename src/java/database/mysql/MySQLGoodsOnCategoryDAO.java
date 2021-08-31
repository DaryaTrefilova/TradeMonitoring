package database.mysql;

import dao.AbstractJDBCDAO;
import domain.CategoryGoods;
import domain.Goods;
import domain.GoodsOnCategory;
import exception.PersistException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;



public class MySQLGoodsOnCategoryDAO extends AbstractJDBCDAO<GoodsOnCategory, Integer> {
    
     private class PersistGoodsOnCategory extends GoodsOnCategory {
      public void setId(int id)
      {
          super.setIdGoodsOnCategory(id);
      }
    }

    public MySQLGoodsOnCategoryDAO(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
       return "SELECT \n" +
                "`idGoodsOnCategory`, \n" +
                "cg.idCategoryGoods, \n" +
                "cg.`Name` NameCategoryGoods, \n" +
                "g.idGoods, \n" +
                "g.`Name` NameGoods, \n" +
                "g.SKU  \n" +
                "FROM goodsoncategory  gonc \n" +
                "JOIN categorygoods cg\n" +
                " ON cg.idCategoryGoods = gonc.idCategoryGoods \n" +
                "JOIN goods g\n" +
                " ON g.idGoods = gonc.idGoods";
    }

    @Override
    public String getCreateQuery() {
      return "INSERT INTO goodsoncategory (`idCategoryGoods`, `idGoods`) VALUES (?, ?);";  
    }

    @Override
    public String getUpdateQuery() {
      return "UPDATE goodsoncategory SET `idCategoryGoods` = '?', `idGoods` = ? WHERE `idGoodsOnCategory` = ?;";   
    }

    @Override
    public String getDeleteQuery() {
      return "DELETE FROM goodsoncategory WHERE `idGoodsOnCategory` = ?;";
    }

    @Override
    public String getNameIdInDB() {
      return "idGoodsOnCategory";  
    }
    @Override
    public  String getSearchCondition(){
       return "cg.idCategoryGoods = ?";
    }
    
    @Override
    protected List<GoodsOnCategory> parseResultSet(ResultSet rs) throws PersistException {
          LinkedList<GoodsOnCategory> result = new LinkedList<GoodsOnCategory>();
        try {
            while (rs.next()) {         
                CategoryGoods categoryGoods = new CategoryGoods();
                categoryGoods.setIdCategoryGoods(rs.getInt("IdCategoryGoods"));
                categoryGoods.setName(rs.getString("NameCategoryGoods"));
                
                Goods goods = new Goods();
                goods.setIdGoods(rs.getInt("idGoods"));
                goods.setSKU(rs.getInt("SKU"));
                goods.setName(rs.getString("NameGoods"));                
                
                PersistGoodsOnCategory pgoc = new PersistGoodsOnCategory();              
                pgoc.setId(rs.getInt("idGoodsOnCategory"));
                pgoc.setCategoryGoods(categoryGoods);
                pgoc.setGoods(goods);
                
                result.add(pgoc);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, GoodsOnCategory object) throws PersistException {
         try {
            statement.setInt(1, object.getCategoryGoods().getIdCategoryGoods());
            statement.setInt(2, object.getGoods().getIdGoods());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, GoodsOnCategory object) throws PersistException {
        try {
            statement.setInt(1, object.getCategoryGoods().getIdCategoryGoods());
            statement.setInt(2, object.getGoods().getIdGoods());
            statement.setInt(3, object.getId());
        } catch (Exception e) {
            throw new PersistException(e); 
     }
    }
    
    @Override
    public GoodsOnCategory create() throws PersistException {
        GoodsOnCategory goc = new GoodsOnCategory();
       return persist(goc);
    }
    
}
