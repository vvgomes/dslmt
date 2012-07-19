package br.pucrs.dslmt.m2t;

import java.util.Map;

import br.pucrs.dslmt.textualSpec.TextualSpec;
import br.pucrs.dslmt.util.TextFileIo;

public class TemplateGenerator {
	public void generate(TextualSpec spec) throws Exception{
		TextFileIo io= new TextFileIo();
		Map<String, String> templates= spec.getTemplates();
		for(String eClassName : templates.keySet())  {
			String path= "src/br/pucrs/dslmt/m2t/" + eClassName + ".st";
			io.write(templates.get(eClassName), path);
		}
	}
}