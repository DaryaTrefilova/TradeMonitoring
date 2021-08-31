package dao;


import exception.PersistException;

/** Фабрика объектов для работы с базой данных**/
public interface DaoFactory<Context> {

public interface DaoCreator<Context>{
 public GenericDAO create(Context context);
}
public Context getContext() throws PersistException;

public GenericDAO getDAO(Context context, Class dtoClass) throws PersistException;
}
