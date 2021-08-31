package domain;
import dao.Identified;


public class Goods implements  Identified<Integer>{
    
private int idGoods = 0;
private int SKU;
private String Name;
     
    public int getIdGoods() {
        return idGoods;
    }
  
    public void setIdGoods(int idGoods) {
        this.idGoods = idGoods;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getSKU() {
        return SKU;
    }

    public void setSKU(int SKU) {
        this.SKU = SKU;
    }

    @Override
    public Integer getId() {
       return  getIdGoods();
    }

  
}
