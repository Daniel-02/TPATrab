package dao.controle;

import org.reflections.Reflections;

import excecao.DoisImplsCandidatos;
import net.sf.cglib.proxy.Enhancer;

public class FabricaDeDAOs {
	private static Reflections reflections = new Reflections("dao.impl");

	@SuppressWarnings("unchecked")
	public static <T> T getDAO(Class<T> tipo) {
		if (reflections.getSubTypesOf(tipo).isEmpty()) {
			System.out.println("Não foi encontrado uma classe que implemente: " + tipo.getSimpleName());
			throw new RuntimeException();
		}
		if (reflections.getSubTypesOf(tipo).size() > 1) 
			throw new DoisImplsCandidatos("Existem duas classes que implementam: " + tipo.getSimpleName());
		Class<?> dao = null;
		for (Class<?> classe : reflections.getSubTypesOf(tipo)) {
			dao = classe;
		}
		return (T) Enhancer.create(dao, new InterceptadorDeDAO());
	}
}
