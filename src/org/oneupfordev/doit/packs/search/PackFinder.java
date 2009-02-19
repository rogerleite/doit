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
 * @author Roger Leite
 */
public class PackFinder {

	private static FileFilter directoryOnlyFilter = new FileFilter() {
		public boolean accept(File file) {
			return file.isDirectory();
		}
	};

	private static FileFilter jarOnlyFilter = new FileFilter() {
		public boolean accept(File file) {
			return file.isFile() && file.getName().toLowerCase().endsWith(".jar");
		}
	};

	/**
	 * <p>This method uses the following rules to look for expression packs:
	 * <ul>
	 * <li>First, list all folders inside the directory parameter.</li>
	 * <li>Second, for each folder, inside should be only <b>one</b> jar file.
	 * If inside contains more then one jar, pack will be ignored.</li>
	 * <li>Third, and <u>optional</u>, if pack has dependencies, pack finder will
	 * read the lib folder (inside the pack folder).</li>
	 * </p>
	 * For more details in this operation, please see the online documentation.
	 * @param directory to look for new expression packs.
	 * @return list of {@link FolderPack} found in directory parameter.
	 */
	public List<FolderPack> lookForPacks(String directory) {
		//TODO: add javadoc details for exception cases.
		File dir = new File(directory);
		if (!dir.exists() || !dir.isDirectory()) {
			String m = String.format("Not valid directory '%s'.", directory);
			throw new RuntimeException(m);
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
		//TODO add javadoc with possible exceptions
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
