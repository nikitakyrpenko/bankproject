package dao.util.consumer;

import java.sql.SQLException;

@FunctionalInterface
public interface TriConsumer<T,U> {
    void accept(T t, U first, U second) throws SQLException;

}
