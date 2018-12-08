package aspecto;
import java.sql.SQLException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import excecao.ProdutoComLancesException;
import excecao.ProdutoJaCadastradoException;
import excecao.ViolacaoDeConstraintDesconhecidaException;

//Para utilizar este aspecto é preciso:
// 1. Sempre que for definida uma nova constraint no CREATE TABLE será necessário acrescentar 
//    esta nova constraint nesta classe.
// 2. Criar uma classe de exceção nova para esta constraint acrescentada ao CREATE TABLE.
// 3. Acrescentar código referente a esta nova constraint nos métodos afetados do managedbean.

@Aspect
public class AspectoAround 
{
	@Pointcut("call(* servico.*.*(..))")
	public void traduzExcecaoAround() {}

	@Around("traduzExcecaoAround()")
	public Object traduzExcecaoAround(ProceedingJoinPoint joinPoint) throws Throwable 
	{	try
		{	return joinPoint.proceed();
		}
		catch(org.springframework.dao.DataAccessException e)
		{	
			Throwable t = e;
			
			if( e instanceof org.springframework.dao.DataIntegrityViolationException)
			{	
				System.out.println("--------------------------------------------");
				
				System.out.println(t.getClass().getName());
				
				t = t.getCause();
				while (t != null && !(t instanceof SQLException))
				{
					System.out.println(t.getClass().getName());
					t = t.getCause();
				}

				if(t != null)
					System.out.println(t.getClass().getName());

				System.out.println("--------------------------------------------");

				String msg = (t.getMessage() != null) ? t.getMessage() : "";
				
				msg = msg.toUpperCase();
				
				if(msg.indexOf("LANCE_PRODUTO_FK") != -1)
				{	throw new ProdutoComLancesException("Este produto possui lances, logo não pode ser removido");
				}
				else if(msg.indexOf("PRODUTO_NOME_UN") != -1)
				{	throw new ProdutoJaCadastradoException("Produto com nome duplicado");
				}
				else
				{	throw new ViolacaoDeConstraintDesconhecidaException
						("A operação não foi realizada em função da violação de uma restrição no banco da dados.");
				}
			}
			else
			{	throw e;
			}
		}
	}
}