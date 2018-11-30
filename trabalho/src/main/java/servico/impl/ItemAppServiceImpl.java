package servico.impl;

import java.util.List;

import anotacao.Autowired;
import anotacao.RollbackFor;
import anotacao.Transactional;
import dao.ItemDAO;
import excecao.ItemNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Item;
import servico.ItemAppService;

public class ItemAppServiceImpl implements ItemAppService {
//	private static ItemDAO itemDAO = FabricaDeDAOs.getDAO(ItemDAO.class);
	@Autowired
	public static ItemDAO itemDAO;

	@Transactional
	public long inclui(Item item) {
		System.out.println("\nDentro de ItemAppServiceImpl. Vai chamar o m�todo inclui() de ItemDAOImpl.");

		long numero = itemDAO.inclui(item).getId();

		System.out.println("\nDentro de ItemAppServiceImpl. Chamou o m�todo inclui() de ItemDAOImpl.");

		return numero;
	}

	@Transactional
	@RollbackFor(nomes = { ItemNaoEncontradoException.class})
	public void altera(Item item) throws ItemNaoEncontradoException {
			System.out.println("\nVai chamar o m�todo altera() de ItemDAOImpl.");

			itemDAO.altera(item);

			System.out.println("\nChamou o m�todo altera() de ItemDAOImpl.");
	}

	@Transactional
	public void exclui(Item item) throws ItemNaoEncontradoException {
			System.out.println("Vai chamar o m�todo exclui() de ItemDAOImpl.");

			itemDAO.exclui(item);

			System.out.println("Chamou o m�todo exclui() de ItemDAOImpl.");
		}

	public Item recuperaItem(long numero) throws ItemNaoEncontradoException {
		try {
			// System.out.println("Vai chamar o m�todo recuperaUmaItem() de
			// ItemDAOImpl.");

			Item item = itemDAO.getPorId(numero);

			// System.out.println("Chamou o m�todo recuperaUmaItem() de
			// ItemDAOImpl.");

			return item;
		} catch (ObjetoNaoEncontradoException e) {
			throw new ItemNaoEncontradoException("Item n�o encontrado");
		}
	}

	public List<Item> recuperaItens() {
		// System.out.println("Vai chamar o m�todo recuperaItems() de
		// ItemDAOImpl.");

		List<Item> contas = itemDAO.recuperaListaDeItens();

		// System.out.println("Chamou o m�todo recuperaItems() de
		// ItemDAOImpl.");

		return contas;
	}
}