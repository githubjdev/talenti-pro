package talenti.pro.multitenancy;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;

import jakarta.enterprise.inject.Vetoed;

@Vetoed
public class SchemaMultiTenantConnectionProvider implements MultiTenantConnectionProvider {

    private static final long serialVersionUID = 1L;

    private DataSource dataSource;

    public SchemaMultiTenantConnectionProvider() {
        try {
            InitialContext ctx = new InitialContext();
            this.dataSource = (DataSource) ctx.lookup("java:jboss/datasources/talentiDS");
        } catch (NamingException e) {
            throw new RuntimeException("Erro ao fazer lookup do DataSource", e);
        }
    }

    @Override
    public Connection getAnyConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        final Connection connection = getAnyConnection();
        connection.createStatement().execute("SET search_path TO " + tenantIdentifier);
        return connection;
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        releaseConnection(null, connection);
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        connection.createStatement().execute("SET search_path TO public");
        connection.close();
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return true;
    }

    @Override
    public boolean isUnwrappableAs(Class unwrapType) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        return null;
    }
}
