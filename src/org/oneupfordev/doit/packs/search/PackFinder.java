/**
 * 
 */
package org.oneupfordev.doit.packs.search;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public class PackFinder {

	private FileFilter directoryOnly = new FileFilter() {
		public boolean accept(File file) {
			return file.isDirectory();
		}
	};

	private FileFilter jarOnly = new FileFilter() {
		public boolean accept(File file) {
			return file.isFile() && file.getName().toLowerCase().endsWith(".jar");
		}
	};

	public List<FolderPack> lookForPacks(String directory) {
		File dir = new File(directory);
		if (!dir.exists() || !dir.isDirectory()) {
			throw new RuntimeException("Directory not valid: '" + directory + "'.");
		}
		List<FolderPack> packs = new ArrayList<FolderPack>();
		for (File subDir : dir.listFiles(directoryOnly)) {
			List<File> jarFiles = Arrays.asList(subDir.listFiles(jarOnly));
			if (jarFiles.size() == 1) {
				File[] libJars = null;

				File libFolder = new File(subDir, "lib");
				if (libFolder.exists() && libFolder.isDirectory()) {
					libJars = libFolder.listFiles(jarOnly);
				}

				FolderPack folderPack = new FolderPack(subDir.getName(), jarFiles.get(0), libJars);
				packs.add(folderPack);
			}
		}
		return packs;
	}

}
