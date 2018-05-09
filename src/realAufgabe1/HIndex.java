package realAufgabe1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class HIndex {
	
	public static File getResourceAsFile(String resourcePath) {
	    try {
	        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(resourcePath);
	        if (in == null) {
	            return null;
	        }

	        File tempFile = File.createTempFile(String.valueOf(in.hashCode()), ".tmp");
	        tempFile.deleteOnExit();

	        try (FileOutputStream out = new FileOutputStream(tempFile)) {
	            //copy stream
	            byte[] buffer = new byte[1024];
	            int bytesRead;
	            while ((bytesRead = in.read(buffer)) != -1) {
	                out.write(buffer, 0, bytesRead);
	            }
	        }
	        return tempFile;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public static void initLinkedList(List<PaperCitation> list) throws FileNotFoundException {
		InputStream stream = Main.class.getResourceAsStream("/publishingsList.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		try {
			String x;
			while ((x = br.readLine()) != null) {
				list.add(new PaperCitation(x.substring(x.indexOf("#author:") + 8, x.indexOf("#title:")),
						x.substring(x.indexOf("#title:") + 7, x.indexOf("#citations:")),
						Integer.parseInt(x.substring(x.indexOf("#citations:") + 11, x.indexOf("#end;")))));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
