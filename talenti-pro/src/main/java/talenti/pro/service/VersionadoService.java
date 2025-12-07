package talenti.pro.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import talenti.pro.exceptionhandler.ExceptionTalentiPro;
import talenti.pro.exceptionhandler.ValidacaoException;
import talenti.pro.model.VersionadorBanco;
import talenti.pro.repository.VersionadorBancoRepository;

@RequestScoped
public class VersionadoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private VersionadorBancoRepository repository;

	@Transactional(TxType.REQUIRED)
	public void processarSQL(ServletContext servletContext) throws ExceptionTalentiPro, FileNotFoundException {

		if (!repository.existeTabela()) {
			throw new ValidacaoException(
					"Tabela versionadorbanco não existe no banco de dados, tabela deve ser criada.");
		}

		String caminhoPastaSQL = servletContext.getRealPath("db_sql_versionamento") + File.separator;
		File[] filesSql = new File(caminhoPastaSQL).listFiles();

		for (File file : filesSql) {

			if (!repository.arquivoJaRodado(file.getName())) {

				FileInputStream entradaArquivo = new FileInputStream(file);
				Scanner lerArquivo = new Scanner(entradaArquivo, "UTF-8");
				StringBuilder sql = new StringBuilder();

				while (lerArquivo.hasNext()) {
					sql.append(lerArquivo.nextLine());
					sql.append("\n");
				}

				repository.executeUpdateNativeSQL(sql.toString());
				repository.salvar(new VersionadorBanco(file.getName()));

				lerArquivo.close();
			}
		}

	}

	@Transactional(TxType.REQUIRED)
	public boolean existeTabela() {
		return repository.existeTabela();

	}

	@Transactional(TxType.REQUIRED)
	public boolean arquivoJaRodado(String file) {
		return repository.arquivoJaRodado(file);
	}

}
