package talenti.pro.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Scanner;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletContext;

@RequestScoped
public class VersionadoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;

	public void processarSQL(ServletContext servletContext) throws Exception {

		String caminhoPastaSQL = servletContext.getRealPath("db_sql_versionamento") + File.separator;

		File[] filesSql = new File(caminhoPastaSQL).listFiles();

		Boolean existeTabela = (Boolean) em.createNativeQuery(
				"SELECT EXISTS ( SELECT 1 FROM information_schema.tables WHERE table_name = 'versionadorbanco' )")
				.getSingleResult();

		if (!existeTabela) {
			throw new Exception("Tabela versionadorbanco não existe no banco de dados, tabela deve ser criada.");
		}

		for (File file : filesSql) {

			boolean arquivoJaRodado = false;

			if (!file.getName().equalsIgnoreCase("V1_INIT.sql")) {

				arquivoJaRodado = (Boolean) em
						.createNativeQuery("select count(1) > 0 as rodado from versionadorbanco where arquivo_sql = '"
								+ file.getName() + "'")
						.getSingleResult();

			}

			if (!arquivoJaRodado) {

				FileInputStream entradaArquivo = new FileInputStream(file);
				Scanner lerArquivo = new Scanner(entradaArquivo, "UTF-8");
				StringBuilder sql = new StringBuilder();

				while (lerArquivo.hasNext()) {
					sql.append(lerArquivo.nextLine());
					sql.append("\n");
				}
				
				em.createNativeQuery(sql.toString()).executeUpdate();

				em.createNativeQuery("INSERT INTO versionadorbanco(arquivo_sql) VALUES ('" + file.getName() + "')").executeUpdate();

				lerArquivo.close();
			}
		}

	}

}
