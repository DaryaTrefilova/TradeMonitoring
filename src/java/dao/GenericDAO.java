package dao;

import exception.PersistException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


public interface GenericDAO<T, PK extends Serializable>  {
    
    public T create() throws PersistException;

    public T persist(T object)  throws PersistException;

    public T getByPK(int key) throws PersistException;

    public void update(T object) throws PersistException;

    public void delete(T object) throws PersistException;

    public List<T> getAll() throws PersistException;
    
    public List<T> getFind(int key) throws PersistException;
  
    public List<T> getFind(String dateBegin, String dateEnd, int mode) throws PersistException;
    
   
}
    
    

