/**
 * 
 */
package org.oneupfordev.doit.packs.search;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.oneupfordev.doit.ExpressionPack;

/**
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public class PackFinder {

	private FileFilter directoryOnlyFilter = new FileFilter() {
		public boolean accept(File file) {
			return file.isDirectory();
		}
	};

	private FileFilter jarOnlyFilter = new FileFilter() {
		public boolean accept(File file) {
			return file.isFile() && file.getName().toLowerCase().endsWith(".jar");
		}
	};

	public List<FolderPack> lookForPacks(String directory) {
		//TODO: add javadoc with details of this operation.
		File dir = new File(directory);
		if (!dir.exists() || !dir.isDirectory()) {
			throw new RuntimeException("Directory not valid: '" + directory + "'.");
		}
		List<FolderPack> packs = new ArrayList<FolderPack>();
		for (File subDir : dir.listFiles(directoryOnlyFilter)) {
			List<File> jarFiles = Arrays.asList(subDir.listFiles(jarOnlyFilter));
			if (jarFiles.size() == 1) {
				File[] libJars = null;

				File libFolder = new File(subDir, "lib");
				if (libFolder.exists() && libFolder.isDirectory()) {
					libJars = libFolder.listFiles(jarOnlyFilter);
				}

				FolderPack folderPack = new FolderPack(subDir.getName(), jarFiles.get(0), libJars);
				packs.add(folderPack);
			}
		}
		return packs;
	}

	public List<ExpressionPack> instantiatePacks(List<FolderPack> packs) {
		List<ExpressionPack> exprPacks = new ArrayList<ExpressionPack>();
		JarFileLoader jarFileLoader = new JarFileLoader();
		for (FolderPack folderPack : packs) {
			jarFileLoader.addPack(folderPack);

			String packClass = folderPack.getClassName();
			ExpressionPack exprPack = jarFileLoader.load(packClass);

			exprPacks.add(exprPack);
		}
		return exprPacks;
	}

}
