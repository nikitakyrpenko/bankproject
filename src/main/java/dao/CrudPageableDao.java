package dao;

import dao.util.Page;
import dao.util.Pageable;

public interface CrudPageableDao<E> extends CrudDao<E> {

    Pageable<E> findAll(Page page);


}