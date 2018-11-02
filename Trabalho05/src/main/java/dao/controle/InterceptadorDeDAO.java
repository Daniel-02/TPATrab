package dao.controle;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import excecao.InfraestruturaException;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import servico.controle.JPAUtil;

public class InterceptadorDeDAO implements MethodInterceptor {
	/*
	 * Parametros:
	 * 
	 * objeto - "this", o objeto "enhanced", isto �, o proxy.
	 * 
	 * metodo - o m�todo interceptado, isto �, um m�todo da interface ContaDAO,
	 * LanceDAO, etc.
	 * 
	 * args - um array de args; tipos primitivos s�o empacotados. Cont�m os
	 * argumentos que o m�todo interceptado recebeu.
	 * 
	 * metodoProxy - utilizado para executar um m�todo super. Veja o coment�rio
	 * abaixo.
	 * 
	 * MethodProxy - Classes geradas pela classe Enhancer passam este objeto para o
	 * objeto MethodInterceptor registrado quando um m�todo interceptado �
	 * executado. Ele pode ser utilizado para invocar o m�todo original, ou chamar o
	 * mesmo m�todo sobre um objeto diferente do mesmo tipo.
	 * 
	 */

	public Object intercept(Object objeto, Method metodo, Object[] args, MethodProxy metodoDoProxy) throws Throwable {
		try {
//			em = JPAUtil.getEntityManager();
			Field[] fields = objeto.getClass().getSuperclass().getFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(anotacao.PersistenceContext.class))
					field.set(objeto, JPAUtil.getEntityManager());
			}

			Object obj = metodoDoProxy.invokeSuper(objeto, args);

			return obj;
		} catch (RuntimeException e) {
			throw new InfraestruturaException(e);
		}

	}
}
