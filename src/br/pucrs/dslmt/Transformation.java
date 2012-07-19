package br.pucrs.dslmt;

public interface Transformation {
	public void run(String inputPath) throws Exception;
	public String getOutputFileName();
}
