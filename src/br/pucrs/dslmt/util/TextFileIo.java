package br.pucrs.dslmt.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class TextFileIo {
	
	public String read(String path) throws Exception {
		InputStream inputStream = new FileInputStream(path);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();        
        String line;
        while((line = bufferedReader.readLine()) != null)
            stringBuilder.append(line + "\n");
        String result= stringBuilder.toString();
        return result.substring(0, result.length()-1);
	}
	
	public void write(String data, String path) throws Exception {
        OutputStream outputStream = new FileOutputStream(path);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        bufferedWriter.write(data, 0, data.length());
        bufferedWriter.flush();
	}
}
