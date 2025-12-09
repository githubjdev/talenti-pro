package talenti.pro.multitenancy;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;

import talenti.pro.exceptionhandler.ErroInternoException;

public class SchemaMultiTenantConnectionProvider implements MultiTenantConnectionProvider {

	private static final long serialVersionUID = 1L;

	private DataSource dataSource;

	public SchemaMultiTenantConnectionProvider() {
		try {
			InitialContext ctx = new InitialContext();
			this.dataSource = (DataSource) ctx.lookup("java:jboss/datasources/talentiDS");
		} catch (NamingException e) {
			throw new ErroInternoException("Erro ao buscar DataSource talentiDS no JNDI do TalentPRO", e);
		}
	}

	/*
	 * Retorna qualquer conexão do pool do WildFly.
	 * Ela ainda não tem tenant configurado.
	 * Serve somente para pegar uma conexão “em branco”, sem tenant.
	 * */
	@Override
	public Connection getAnyConnection() throws SQLException {
		return dataSource.getConnection();
	}

	/**
	 * 1 - Pega uma conexão do pool (sem schema)
	 * 2 - Devolve a conexão já configurada para o tenantIdentifier.
	 */
	@Override
	public Connection getConnection(String tenantIdentifier) throws SQLException {
		final Connection connection = getAnyConnection();
		connection.createStatement().execute("SET search_path TO " + tenantIdentifier);
		return connection;
	}

	/**
	 * 1 - Antes de devolver a conexão ao pool reseta o schema para o padrão:
	 */
	@Override
	public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
		connection.createStatement().execute("SET search_path TO public");
		connection.close();
	}
	
	/**
	 * Esse método é usado quando o Hibernate quer soltar uma conexão que não está associada a um tenant.
	 */
	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		connection.createStatement().execute("SET search_path TO public");
		connection.close();
	}

	@Override
	public boolean isUnwrappableAs(Class unwrapType) {
		return false;
	}

	/**
	 * Permitem que bibliotecas externas tentem “desembrulhar” o provider.
	 * No seu caso, não precisa suportar isso, então:
	 */
	@Override
	public <T> T unwrap(Class<T> unwrapType) {
		return null;
	}

	/**
	 * Indica ao Hibernate que ele pode liberar a conexão sempre que puder.
	 */
	@Override
	public boolean supportsAggressiveRelease() {
		return true;
	}

}
