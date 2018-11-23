package aspecto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectoAround {
	private static Logger logger = null;
	private static File log = new File("log.txt");

	@Pointcut("call(* servico..*.*(..))")
	public void efetuaLogDeErro() {
	}

	@Around("efetuaLogDeErro()")
	public Object efetuaLog(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			return joinPoint.proceed();
//		} catch ()
		} catch (Throwable throwable) {
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
}