package dao.controle;

import net.sf.cglib.proxy.Enhancer;

public class FabricaDeDAO {
	@SuppressWarnings("unchecked")
	public static <T> T getDao(String classeDoDaoComoString) throws Exception {
		Class<?> classeDoDao = Class.forName(classeDoDaoComoString);

		return (T) Enhancer.create(classeDoDao, new InterceptadorDeDAO());

		// O proxy deve estender a classe (ProdutoDAOImpl por exemplo),
		// que deve estender a classe JPADaoGenerico. O proxy deve ainda
		// chamar o método intercept() da classe interceptadora, isto é, da
		// classe InterceptadorDeDAO (classe callback).

		// Enhancer enhancer = new Enhancer();
		// enhancer.setSuperclass(classeDoDao); // Superclasse do DAO
		// enhancer.setCallback(new InterceptadorDeDAO()); // Interceptador do DAO

		// return (T) enhancer.create();
	}
}
