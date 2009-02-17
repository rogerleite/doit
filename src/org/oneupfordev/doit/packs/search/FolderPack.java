/**
 * 
 */
package org.oneupfordev.doit.packs.search;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public class FolderPack {

	private String folderName = null;
	private File jarFile = null;
	private List<File> libFiles = null;

	FolderPack(final String folderName, final File jarFile, final File[] libFiles) {
		this.folderName = folderName;
		this.jarFile = jarFile;
		if (libFiles != null) {
			this.libFiles = Arrays.asList(libFiles);
		} else {
			this.libFiles = new ArrayList<File>();
		}
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("Folder: ").append(folderName);
		result.append(" Jar: ").append(jarFile.getName());
		if (libFiles != null && !libFiles.isEmpty()) {
			result.append(" Lib: ");
			for (File f : libFiles) {
				result.append(f.getName()).append("; ");
			}
		}
		return result.toString();
	}

}
