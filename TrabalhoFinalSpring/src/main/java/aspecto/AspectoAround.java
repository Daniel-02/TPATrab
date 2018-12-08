package aspecto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.reflections.Reflections;
import org.springframework.dao.DataIntegrityViolationException;

import excecao.ViolacaoDeConstraintDesconhecidaException;

@Aspect
public class AspectoAround {
	private static Logger logger = null;
	private static File log = new File("log.txt");
	private static Map<String, Class<?>> map = new HashMap<String, Class<?>>();
	private static List<String> listaDeNomesDeConstraints;

	static {
		Reflections reflections = new Reflections("excecao");

		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(anotacao.ConstraintViolada.class);

		for (Class<?> classe : annotated) {
			map.put(classe.getAnnotation(anotacao.ConstraintViolada.class).nome(), classe);
		}

		listaDeNomesDeConstraints = new ArrayList<String>(map.keySet());
	}

	@Pointcut("call(* servico..*.*(..))")
	public void efetuaLogDeErro() {
	}
	@Pointcut("call(* servico.*.*(..))")
	public void traduzExcecaoAround() {}

	@Around("efetuaLogDeErro()")
	public Object efetuaLog(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			return joinPoint.proceed();
//		} catch ()
		}
		catch (Throwable throwable) {
			String metodo = joinPoint.getSignature().getName();

			String mensagem = (throwable.getMessage() != null ? throwable.getMessage() : "");

			Throwable t = throwable.getCause();

			while (t != null) {
				mensagem = mensagem + (t.getMessage() != null ? " <==> " + t.getMessage() : "");
				t = t.getCause();
			}

			// As 4 linhas de código abaixo geram o stack trace como um String
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			throwable.printStackTrace(pw);
			String stackTrace = sw.toString();

//			logger = Logger.getLogger(this.getClass().getName());
//
//			logger.error("   Classe do erro: " + throwable.getClass().getName() + "   Metodo: " + metodo
//					+ "   Mensagem: " + mensagem + "   Stack Trace: " + stackTrace);
//
			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter(log, true));
				writer.write(LocalDateTime.now() + ": Classe do erro: " + throwable.getClass().getName() + "   Metodo: "
						+ metodo + "   Mensagem: " + mensagem + "   Stack Trace: " + stackTrace);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					writer.close();
				} catch (Exception e) {
				}
			}
			throw throwable;
		}
	}
	
	@Around("traduzExcecaoAround()")
	public Object traduzExcecaoAround(ProceedingJoinPoint joinPoint) throws Throwable 
	{	try
		{	return joinPoint.proceed();
		}
		catch(org.springframework.dao.DataAccessException e)
		{	
			Throwable t = e;
			
			if( t instanceof DataIntegrityViolationException)
			{	
				t = t.getCause();
				while (t != null && !(t instanceof SQLException))
				{
					t = t.getCause();
				}
				
				String msg = (t.getMessage() != null) ? t.getMessage() : "";
				
				for(String nomeDeConstraint : listaDeNomesDeConstraints)
				{
					if(msg.indexOf(nomeDeConstraint) != -1)
					{
						throw (Exception)map.get(nomeDeConstraint).newInstance();
					}
				}
				throw new ViolacaoDeConstraintDesconhecidaException
					("A operação não foi realizada em função da violação de "
							+ "uma restrição no banco da dados.");
			}
			else
			{	throw e;
			}
		}
	}
}