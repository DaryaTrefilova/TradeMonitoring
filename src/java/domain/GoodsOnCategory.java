package domain;

import dao.Identified;


public class GoodsOnCategory implements Identified<Integer> {
    
    private int idGoodsOnCategory = 0;
    private Goods goods;
    private CategoryGoods categoryGoods;
    

    @Override
    public Integer getId() {
        return idGoodsOnCategory;
    }

    public CategoryGoods getCategoryGoods() {
        return categoryGoods;
    }

    public void setCategoryGoods(CategoryGoods categoryGoods) {
        this.categoryGoods = categoryGoods;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getIdGoodsOnCategory() {
        return idGoodsOnCategory;
    }

    public void setIdGoodsOnCategory(int idGoodsOnCategory) {
        this.idGoodsOnCategory = idGoodsOnCategory;
    }
    
    public GoodsOnCategory()
    {
      goods = new Goods();
      categoryGoods = new CategoryGoods();
    }
    

}
