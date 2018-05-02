package I_O;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Read {
	public void readfile(String FileName) {
		BufferedReader br = null;
		FileReader fr = null;

		try {
			fr = new FileReader(FileName);
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}

	public String readfilePass(String Filename) {
		BufferedReader br = null;
		FileReader fr = null;
		String s = "";
		try {

			// br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader(Filename);
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				s = s + sCurrentLine + "\n";
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		return s;
	}

	public String[] linedivision(String text) {
		String[] lines = null;
		if (text != null)
			lines = text.split("\n");
		return lines;
	}
}
