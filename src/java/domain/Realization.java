package domain;

import dao.Identified;


public class Realization implements Identified<Integer> {
    
private int idRealization = 0;
private java.util.Date RlztnDate;
private int Amount;
private Goods goods;

    @Override
    public Integer getId() {
        return  getIdRealization();
    }

    public void setIdRealization(int idRealization) {
        this.idRealization = idRealization;
    }

    public int getIdRealization() {
        return idRealization;
    }
             

    public java.util.Date getRlztnDate() {
        return RlztnDate;
    }

    public void setRlztnDate(java.util.Date rlztnDate) {
        this.RlztnDate = rlztnDate;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
    
}
