package dao;

import dao.exception.DataBaseSqlRuntimeException;
import dao.util.consumer.BiConsumer;
import dao.util.ConnectorDB;
import dao.util.Page;
import dao.util.Pageable;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractCrudDaoImp<E> implements CrudDao<E>, CrudPageableDao<E> {
    private static Logger LOGGER = Logger.getLogger(AbstractCrudDaoImp.class);

    protected final ConnectorDB connector;
    private final String findByIdQuery;
    private final String findAllQuery;
    private final String findAllPageableQuery;

    protected final BiConsumer<PreparedStatement, Integer> INT_PARAM_SETTER =
            ((statement, integer) -> statement.setObject(1, integer));

    protected final BiConsumer<PreparedStatement, String> STRING_PARAM_SETTER =
            (statement, string) -> statement.setString(1, string);


    protected final BiConsumer<PreparedStatement, Integer> BI_INT_PARAM_SETTER =
            ((statement, integer) -> {
                statement.setObject(1, integer);
                statement.setObject(2, integer);
            });


    protected AbstractCrudDaoImp(ConnectorDB connector, String findByIdQuery, String findAllQuery, String findAllPageableQuery) {
        this.connector = connector;
        this.findByIdQuery = findByIdQuery;
        this.findAllQuery = findAllQuery;
        this.findAllPageableQuery = findAllPageableQuery;
    }

    protected <P> Optional<E> findByParam(P param, String findByParam, BiConsumer<PreparedStatement, P> designatedParamSetter) {
        try (final PreparedStatement preparedStatement =
                     connector.getConnection().prepareStatement(findByParam)) {
            designatedParamSetter.accept(preparedStatement, param);

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToEntity(resultSet));
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DataBaseSqlRuntimeException("", e);
        }

        return Optional.empty();
    }

    protected <P> List<E> findAllByParams(P p, String findByParam, BiConsumer<PreparedStatement, P> designatedParamSetter) {
        List<E> result = new ArrayList<>();
        try (final PreparedStatement preparedStatement =
                     connector.getConnection().prepareStatement(findByParam)) {
            designatedParamSetter.accept(preparedStatement, p);

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(mapResultSetToEntity(resultSet));
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DataBaseSqlRuntimeException("", e);
        }

        return result;
    }

    @Override
    public List<E> findAll() {
        List<E> result = new ArrayList<>();
        try (final PreparedStatement preparedStatement =
                     connector.getConnection().prepareStatement(findAllQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(mapResultSetToEntity(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DataBaseSqlRuntimeException("", e);
        }
        return result;
    }

    @Override
    public Pageable<E> findAll(Page page) {
        List<E> result = new ArrayList<>();

        int pageOffset = page.getPageNumber() * page.getItemsPerPage();
        int pageSize = page.getItemsPerPage();

        try (final PreparedStatement preparedStatement =
                     connector.getConnection().prepareStatement(findAllPageableQuery)) {
            preparedStatement.setInt(1, pageOffset);
            preparedStatement.setInt(2, pageSize);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(mapResultSetToEntity(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return new Pageable<>(result, page.getPageNumber(), page.getItemsPerPage(), 10);
    }

    @Override
    public Optional findById(Integer id) {
        return findByParam(id, findByIdQuery, INT_PARAM_SETTER);
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Unsupported exception");
    }

    protected abstract E mapResultSetToEntity(ResultSet resultSet) throws SQLException;
}
