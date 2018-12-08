import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import modelo.Documento;
import servico.DocumentoAppService;
import util.Util;

public class Principal {
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");
        
		DocumentoAppService documentoAppService = 
				(DocumentoAppService)fabrica.getBean ("documentoAppService");
		
		List<Documento> teste = documentoAppService.recuperaDocumentosPelaData(Util.strToDate("10/10/2000"), 0, 10);
		for (Documento documento2 : teste) {
			System.out.println(documento2.getCabecalho());
		}
	}
}
