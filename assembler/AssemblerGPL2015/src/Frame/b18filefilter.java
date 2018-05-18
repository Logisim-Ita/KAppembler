package Frame;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class b18filefilter extends FileFilter {
	public boolean accept(File file) {
		if (file.isDirectory())
			return true;
		String fname = file.getName().toLowerCase();
		return fname.endsWith("b18");
	}

	public String getDescription() {
		return "File Linguaggio Macchina(.b18)";
	}
}
