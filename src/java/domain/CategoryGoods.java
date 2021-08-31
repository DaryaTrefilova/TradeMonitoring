package domain;

import dao.Identified;


public class CategoryGoods implements Identified<Integer>{
    
private int idCategoryGoods = 0;
private String Name;   

    @Override
    public Integer getId() {
      return idCategoryGoods; 
    }

    public void setIdCategoryGoods(int idCategoryGoods) {
        this.idCategoryGoods = idCategoryGoods;
    }

    public int getIdCategoryGoods() {
        return idCategoryGoods;
    }
    
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
 
}
