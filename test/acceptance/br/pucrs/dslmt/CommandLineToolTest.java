package br.pucrs.dslmt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.BeforeClass;
import org.junit.Test;

public class CommandLineToolTest {
	
	@BeforeClass
	public static void createJar() throws Exception {
		exec("ant dist");
	}
	
	@Test
	public void shouldRunTextToModelTransformation() throws Exception {
		String cmd= "java -jar dist/dslmt.jar -t2m models/fsm_t2m.xml models/fsm_model.fsm";
		assertEquals("models/fsm_model.xmi generated successfully.\n", exec(cmd));
	}
	
	@Test
	public void shouldRunModelToTextTransformation() throws Exception {
		String cmd= "java -jar dist/dslmt.jar -m2t models/fsm_m2t.xml models/fsm_model.xmi";
		assertEquals("models/fsm_model.fsm generated successfully.\n", exec(cmd));
	}
	
	@Test
	public void shouldReportInvalidParameters() throws Exception {
		String cmd= "java -jar dist/dslmt.jar";
		assertEquals("Invalid parameters.\nUsage: -<transf_type> <textual_spec> <input_model>\n", exec(cmd));
	}
	
	@Test
	public void shouldReportInvalidSpecFile() throws Exception {
		String cmd= "java -jar dist/dslmt.jar -m2t invalid.xml models/fsm_model.xmi";
		assertEquals("invalid.xml not found.\n", exec(cmd));
	}
	
	@Test
	public void shouldReportInvalidInputFile() throws Exception {
		String cmd= "java -jar dist/dslmt.jar -m2t models/fsm_m2t.xml invalid.xmi";
		assertEquals("invalid.xmi not found.\n", exec(cmd));
	}
	
	private static String exec(String cmd) throws Exception {
        Process proc = Runtime.getRuntime().exec(cmd);
        
        InputStream inputStream = proc.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        if(proc.waitFor() != 0)
            fail("exit value = " + proc.exitValue());

        StringBuilder stringBuilder = new StringBuilder();        
        String line;
        while ((line = bufferedReader.readLine()) != null)
            stringBuilder.append(line + "\n");
        return stringBuilder.toString();
    }
}
