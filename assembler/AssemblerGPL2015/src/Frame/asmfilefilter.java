package Frame;

import java.io.File;

import javax.swing.filechooser.FileFilter;

class asmfilefilter extends FileFilter {

	public boolean accept(File file) {
		if (file.isDirectory())
			return true;
		String fname = file.getName().toLowerCase();
		return fname.endsWith("asm");
	}

	public String getDescription() {
		return "File assembly";
	}
}