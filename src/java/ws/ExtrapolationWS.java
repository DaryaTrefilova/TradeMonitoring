/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;


import dao.GenericDAO;
import database.mysql.MySQLDAOFactory;
import domain.CategoryGoods;
//import ext.Extrapolation;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import java.util.List;
import domain.Goods;
import domain.GoodsOnCategory;
import domain.Realization;
import exception.PersistException;
import ext.ExtExponentialSmoothing;
import ext.ExtLeastSquares;
import ext.ExtMovingAverage;
import ext.ResultExtrapolation;
import static java.lang.Math.abs;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


@WebService(serviceName = "ExtrapolationWS")
@Stateless()
public class ExtrapolationWS {

 
    @WebMethod(operationName = "listGoods")
    public List<Goods> listGoods() throws PersistException {
        
        Connection con;
        MySQLDAOFactory factory = new MySQLDAOFactory();
        con = factory.getContext();
        GenericDAO GoodsDAO;
        GoodsDAO = factory.getDAO(con, Goods.class);
           
       return GoodsDAO.getAll();
    }

    @WebMethod(operationName = "addGoods")
    public void addGoods(@WebParam(name = "goods") Goods goods) throws PersistException {
           
        Connection con;
        MySQLDAOFactory factory = new MySQLDAOFactory();
        con = factory.getContext();
        GenericDAO GoodsDAO;
        GoodsDAO = factory.getDAO(con, Goods.class);
        GoodsDAO.persist(goods);
    }
     
   @WebMethod(operationName = "editGoods")
    public void editGoods(@WebParam(name = "goods") Goods goods) throws PersistException {
           
        Connection con;
        MySQLDAOFactory factory = new MySQLDAOFactory();
        con = factory.getContext();
        GenericDAO GoodsDAO;
        GoodsDAO = factory.getDAO(con, Goods.class);
        GoodsDAO.update(goods);
    }
   
    @WebMethod(operationName = "delGoods")
    public void delGoods(@WebParam(name = "goods") Goods goods) throws PersistException {
           
        Connection con;
        MySQLDAOFactory factory = new MySQLDAOFactory();
        con = factory.getContext();
        GenericDAO GoodsDAO;
        GoodsDAO = factory.getDAO(con, Goods.class);
        GoodsDAO.delete(goods);
    } 
    
    
    @WebMethod(operationName = "getGoodsByPK")
    public Goods getGoodsByPK(@WebParam(name = "idGoods") int idGoods) throws PersistException {
           
        Connection con;
        MySQLDAOFactory factory = new MySQLDAOFactory();
        con = factory.getContext();
        GenericDAO GoodsDAO;
        GoodsDAO = factory.getDAO(con, Goods.class);
        return (Goods) GoodsDAO.getByPK(idGoods);  
    }
    
  @WebMethod(operationName = "listCategoryGoods")
    public List<CategoryGoods> listCategoryGoods() throws PersistException {
        
        Connection con;
        MySQLDAOFactory factory = new MySQLDAOFactory();
        con = factory.getContext();
        GenericDAO CategoryGoodsDAO;
        CategoryGoodsDAO = factory.getDAO(con, CategoryGoods.class);
           
       return CategoryGoodsDAO.getAll();
    }

    @WebMethod(operationName = "addCategoryGoods")
    public void addCategoryGoods(@WebParam(name = "categoryGoods") CategoryGoods categoryGoods) throws PersistException {
           
        Connection con;
        MySQLDAOFactory factory = new MySQLDAOFactory();
        con = factory.getContext();
        GenericDAO CategoryGoodsDAO;
        CategoryGoodsDAO = factory.getDAO(con, CategoryGoods.class);
        CategoryGoodsDAO.persist(categoryGoods);
    }
     
   @WebMethod(operationName = "editCategoryGoods")
    public void editCategoryGoods(@WebParam(name = "categoryGoods") CategoryGoods categoryGoods) throws PersistException {
           
        Connection con;
        MySQLDAOFactory factory = new MySQLDAOFactory();
        con = factory.getContext();
        GenericDAO CategoryGoodsDAO;
        CategoryGoodsDAO = factory.getDAO(con, CategoryGoods.class);
        CategoryGoodsDAO.update(categoryGoods);
    }
   
    @WebMethod(operationName = "delCategoryGoods")
    public void delCategoryGoods(@WebParam(name = "categoryGoods") CategoryGoods categoryGoods) throws PersistException {
           
        Connection con;
        MySQLDAOFactory factory = new MySQLDAOFactory();
        con = factory.getContext();
        GenericDAO CategoryGoodsDAO;
        CategoryGoodsDAO = factory.getDAO(con, CategoryGoods.class);
        CategoryGoodsDAO.delete(categoryGoods);
    } 

    @WebMethod(operationName = "getCategoryGoodsByPK")
    public CategoryGoods getCategoryGoodsByPK(@WebParam(name = "idCategoryGoods") int idCategoryGoods) throws PersistException {
           
        Connection con;
        MySQLDAOFactory factory = new MySQLDAOFactory();
        con = factory.getContext();
        GenericDAO CategoryGoodsDAO;
        CategoryGoodsDAO = factory.getDAO(con, CategoryGoods.class);
        return (CategoryGoods) CategoryGoodsDAO.getByPK(idCategoryGoods);  
    }

    @WebMethod(operationName = "findGoodsOnCategory")
    public List<GoodsOnCategory> findGoodsOnCategory(@WebParam(name = "SearchValue") int SearchValue) throws PersistException {
        
        Connection con;
        MySQLDAOFactory factory = new MySQLDAOFactory();
        con = factory.getContext();
        GenericDAO GoodsOnCategoryDAO;
        GoodsOnCategoryDAO = factory.getDAO(con, GoodsOnCategory.class);
           
       return GoodsOnCategoryDAO.getFind(SearchValue);
    }
    
    @WebMethod(operationName = "addGoodsOnCategory")
    public void addGoodsOnCategory(@WebParam(name = "goodsOnCategory") GoodsOnCategory goodsOnCategory) throws PersistException {
           
        Connection con;
        MySQLDAOFactory factory = new MySQLDAOFactory();
        con = factory.getContext();
        GenericDAO GoodsOnCategoryDAO;
        GoodsOnCategoryDAO = factory.getDAO(con, GoodsOnCategory.class);
        GoodsOnCategoryDAO.persist(goodsOnCategory);
    }
    
    @WebMethod(operationName = "delGoodsOnCategory")
    public void delGoodsOnCategory(@WebParam(name = "goodsOnCategory") GoodsOnCategory goodsOnCategory) throws PersistException {
           
        Connection con;
        MySQLDAOFactory factory = new MySQLDAOFactory();
        con = factory.getContext();
        GenericDAO GoodsOnCategoryDAO;
        GoodsOnCategoryDAO = factory.getDAO(con, GoodsOnCategory.class);
        GoodsOnCategoryDAO.delete(goodsOnCategory);
    }
    
    @WebMethod(operationName = "listRealization")
    public List<Realization> listReazlization() throws PersistException {
        
        Connection con;
        MySQLDAOFactory factory = new MySQLDAOFactory();
        con = factory.getContext();
        GenericDAO RealizationDAO;
        RealizationDAO = factory.getDAO(con, Realization.class);
           
       return RealizationDAO.getAll();
    }

    @WebMethod(operationName = "addRealization")
    public void addRealization(@WebParam(name = "realization") Realization realization) throws PersistException {
           
        Connection con;
        MySQLDAOFactory factory = new MySQLDAOFactory();
        con = factory.getContext();
        GenericDAO RealizationDAO;
        RealizationDAO = factory.getDAO(con, Realization.class);
        RealizationDAO.persist(realization);
    }
     
   @WebMethod(operationName = "editRealization")
    public void editRealization(@WebParam(name = "realization") Realization realization) throws PersistException {
           
        Connection con;
        MySQLDAOFactory factory = new MySQLDAOFactory();
        con = factory.getContext();
        GenericDAO RealizationDAO;
        RealizationDAO = factory.getDAO(con, Realization.class);
        RealizationDAO.update(realization);
    }
   
    @WebMethod(operationName = "delRealization")
    public void delRealization(@WebParam(name = "realization") Realization realization) throws PersistException {
           
        Connection con;
        MySQLDAOFactory factory = new MySQLDAOFactory();
        con = factory.getContext();
        GenericDAO RealizationDAO;
        RealizationDAO = factory.getDAO(con, Realization.class);
        RealizationDAO.delete(realization);
    } 

    @WebMethod(operationName = "getRealizationByPK")
    public Realization getRealizationByPK(@WebParam(name = "idRealization") int idRealization) throws PersistException {
           
        Connection con;
        MySQLDAOFactory factory = new MySQLDAOFactory();
        con = factory.getContext();
        GenericDAO RealizationDAO;
        RealizationDAO = factory.getDAO(con, Realization.class);
        return (Realization) RealizationDAO.getByPK(idRealization);  
    }
    
    @WebMethod(operationName = "getRealizationForPeriod")
    public List<Realization> getRealizationForPeriod(@WebParam(name = "dateBegin") String dateBegin,@WebParam(name = "dateEnd") String dateEnd ) throws PersistException {
           
        Connection con;
        MySQLDAOFactory factory = new MySQLDAOFactory();
        con = factory.getContext();
        GenericDAO RealizationDAO;
        RealizationDAO = factory.getDAO(con, Realization.class);
  
 
        return RealizationDAO.getFind( dateBegin, dateEnd, 0);
    }
    
    @WebMethod(operationName = "ExtrapolationLeastSquares")
    public List<ResultExtrapolation> ExtrapolationLeastSquares(@WebParam(name = "dateBegin") String dateBegin,@WebParam(name = "dateEnd") String dateEnd,@WebParam(name = "forecastPeriod") int forecastPeriod) throws PersistException {
           
        Connection con;
        MySQLDAOFactory factory = new MySQLDAOFactory();
        con = factory.getContext();
        GenericDAO RealizationDAO;
        RealizationDAO = factory.getDAO(con, Realization.class);
        List<Realization> listRealization =  RealizationDAO.getFind( dateBegin, dateEnd, 1);
    
        ExtLeastSquares extLeastSquares_Itogo = new ExtLeastSquares();
        List<ExtLeastSquares> listExtLeastSquares = new ArrayList<ExtLeastSquares>();
        
        int N = listRealization.size() - 1;
        for (int i = 0; i <= N; i++ )
        {
           ExtLeastSquares extLeastSquares = new ExtLeastSquares();
           extLeastSquares.month = listRealization.get(i).getRlztnDate().getMonth();
           extLeastSquares.amount = listRealization.get(i).getAmount();
           extLeastSquares.x = i+1;
           extLeastSquares.amountX = extLeastSquares.amount * extLeastSquares.x;
           extLeastSquares.squareX = extLeastSquares.x*extLeastSquares.x;
           
           extLeastSquares_Itogo.amount = extLeastSquares_Itogo.amount + extLeastSquares.amount;
           extLeastSquares_Itogo.amountX = extLeastSquares_Itogo.amountX + extLeastSquares.amountX;
           extLeastSquares_Itogo.squareX = extLeastSquares_Itogo.squareX + extLeastSquares.squareX;
           
           listExtLeastSquares.add(extLeastSquares);
        }
        int a = (extLeastSquares_Itogo.amountX -(extLeastSquares_Itogo.x*extLeastSquares_Itogo.amount)/N)
           /(extLeastSquares_Itogo.squareX-(extLeastSquares_Itogo.x* extLeastSquares_Itogo.x)/N);
        int b = extLeastSquares_Itogo.amount/N-a*extLeastSquares_Itogo.x/N;
        
        for (int i = 0; i <= N; i++ ) 
        {
            listExtLeastSquares.get(i).yr = a * listExtLeastSquares.get(i).x + b;
            listExtLeastSquares.get(i).meanRelativeError = ((listExtLeastSquares.get(i).amount - listExtLeastSquares.get(i).yr)
            /listExtLeastSquares.get(i).amount) * 100;
           extLeastSquares_Itogo.meanRelativeError = extLeastSquares_Itogo.meanRelativeError + listExtLeastSquares.get(i).meanRelativeError;
        }

        
        List<ResultExtrapolation> listResultLeastSquares = new ArrayList<ResultExtrapolation>();
        
        Locale loc = Locale.forLanguageTag("ru");
        for (int i = 1; i<=forecastPeriod; i++){
            ResultExtrapolation resultLeastSquares = new ResultExtrapolation();
            Month mnth = Month.of(listExtLeastSquares.get(N).month + 1); 
            resultLeastSquares.month = mnth.getDisplayName(TextStyle.FULL_STANDALONE, loc);
            resultLeastSquares.value = a * (listExtLeastSquares.get(N).month + 1) + b;
            resultLeastSquares.meanRelativeError = extLeastSquares_Itogo.meanRelativeError/N;
            
            listResultLeastSquares.add(resultLeastSquares);
        }
        
        return  listResultLeastSquares;    
    } 

 @WebMethod(operationName = "ExtrapolationMovingAverage")
    public List<ResultExtrapolation> ExtrapolationMovingAverage(@WebParam(name = "dateBegin") String dateBegin,@WebParam(name = "dateEnd") String dateEnd,@WebParam(name = "forecastPeriod") int forecastPeriod) throws PersistException {
           
        Connection con;
        MySQLDAOFactory factory = new MySQLDAOFactory();
        con = factory.getContext();
        GenericDAO RealizationDAO;
        RealizationDAO = factory.getDAO(con, Realization.class);
        List<Realization> listRealization =  RealizationDAO.getFind( dateBegin, dateEnd, 1);
     
        int Itogo = 0; 
        List<ExtMovingAverage> listextMovingAverage = new ArrayList<ExtMovingAverage>();
        
        final int n = 3;//интервал сглаживания 
        int N = listRealization.size() - 1;
        for (int i = 0; i <= N; i++ )
        {
           ExtMovingAverage extMovingAverage = new ExtMovingAverage();
           extMovingAverage.month = listRealization.get(i).getRlztnDate().getMonth();
           extMovingAverage.amount = listRealization.get(i).getAmount();
          if (i != 0 || i!=N){
           extMovingAverage.movingAverage = (listRealization.get(i-1).getAmount() 
                    + listRealization.get(i).getAmount() + listRealization.get(i + 1).getAmount())/n;
          extMovingAverage.meanRelativeError = ((extMovingAverage.amount - extMovingAverage.movingAverage)
                  /extMovingAverage.amount) * 100;
          }
         
          Itogo = Itogo + extMovingAverage.meanRelativeError; 
         
          listextMovingAverage.add(extMovingAverage); 
        }
        
        for (int i = 0; i < forecastPeriod; i++)
        {
            ExtMovingAverage extMovingAverage = new ExtMovingAverage();
            extMovingAverage.month = listextMovingAverage.get(N + i).month + 1;
            extMovingAverage.amount = listextMovingAverage.get(N - 1).movingAverage + 1/n 
             *(listextMovingAverage.get(N).amount - listextMovingAverage.get(N-1).amount);
            if (i != forecastPeriod - 1){
            listextMovingAverage.get(N + i).movingAverage = (listRealization.get(N-1).getAmount() 
                    + listRealization.get(N).getAmount() + extMovingAverage.amount)/n;
            }
           
            listextMovingAverage.add(extMovingAverage); 
        }

        
        List<ResultExtrapolation> listResultMovingAverage = new ArrayList<ResultExtrapolation>();
        
        Locale loc = Locale.forLanguageTag("ru");
        for (int i = 1; i<=forecastPeriod; i++){
            ResultExtrapolation resultMovingAverage = new ResultExtrapolation();
            Month mnth = Month.of(listextMovingAverage.get(N + i).month + 1); 
            resultMovingAverage.month = mnth.getDisplayName(TextStyle.FULL_STANDALONE, loc);
            resultMovingAverage.value = listextMovingAverage.get(N + i).amount;
            resultMovingAverage.meanRelativeError = Itogo / N - 2;
            
            listResultMovingAverage.add(resultMovingAverage);
        }
        
        return  listResultMovingAverage;    
    } 

    @WebMethod(operationName = "ExtrapolationExponentialSmoothing")
    public List<ResultExtrapolation> ExtrapolationExponentialSmoothing(@WebParam(name = "dateBegin") String dateBegin,@WebParam(name = "dateEnd") String dateEnd,@WebParam(name = "forecastPeriod") int forecastPeriod) throws PersistException {
       
        Connection con;
        MySQLDAOFactory factory = new MySQLDAOFactory();
        con = factory.getContext();
        GenericDAO RealizationDAO;
        RealizationDAO = factory.getDAO(con, Realization.class);
        List<Realization> listRealization =  RealizationDAO.getFind( dateBegin, dateEnd, 1);

        List<ExtExponentialSmoothing> listExtExponentialSmoothing = new ArrayList<ExtExponentialSmoothing>();
        
        double a = 2. / (listRealization.size() + 1);
        int U = listRealization.get(0).getAmount();
        double meanRelativeError = 0.00;
        int N = listRealization.size() - 1 + forecastPeriod;       
        for (int i=0; i < N; i++ )
        {
           ExtExponentialSmoothing extExponentialSmoothing = new ExtExponentialSmoothing();
           Calendar calendar = Calendar.getInstance();
           
           if (i < (N - forecastPeriod)){
           extExponentialSmoothing.amount =  listRealization.get(i).getAmount();
           calendar.setTime(listRealization.get(i).getRlztnDate());
           int month =  calendar.get(Calendar.MONTH);
           extExponentialSmoothing.month = month+1;
           }
           else {
            extExponentialSmoothing.amount = listExtExponentialSmoothing.get(i-1).amount;
            extExponentialSmoothing.month = listExtExponentialSmoothing.get(i-1).month+1;
           }
           if (i == 0) {       
            extExponentialSmoothing.ExpSmoothing = extExponentialSmoothing.amount * a + (1 - a) * U;
        
           }
           else {
              extExponentialSmoothing.ExpSmoothing = extExponentialSmoothing.amount * a + (1 - a) 
                      * listExtExponentialSmoothing.get(i-1).ExpSmoothing;
              meanRelativeError = meanRelativeError + abs(((extExponentialSmoothing.amount - extExponentialSmoothing.ExpSmoothing)/ extExponentialSmoothing.amount) * 100);
             
           }
           
            listExtExponentialSmoothing.add(extExponentialSmoothing);  
        }
        
        meanRelativeError = (meanRelativeError / (N + forecastPeriod));
        List<ResultExtrapolation> listResultExplSmoothing = new ArrayList<ResultExtrapolation>();
        
        Locale loc = Locale.forLanguageTag("ru");

        N =  listExtExponentialSmoothing.size() - (forecastPeriod+1) ;
        for (int i = 1; i<= forecastPeriod; i++){
             ResultExtrapolation resultExponentialSmoothing = new ResultExtrapolation();
         
            Month mnth = Month.of(listExtExponentialSmoothing.get(N+i).month+1);     
         
            resultExponentialSmoothing.month = mnth.getDisplayName(TextStyle.FULL_STANDALONE, loc);
            resultExponentialSmoothing.value = (int) listExtExponentialSmoothing.get(N+i).ExpSmoothing;
            resultExponentialSmoothing.meanRelativeError =  (int) meanRelativeError;
            
            listResultExplSmoothing.add(resultExponentialSmoothing);
        }
        return  listResultExplSmoothing;    
    } 
    
    

}
