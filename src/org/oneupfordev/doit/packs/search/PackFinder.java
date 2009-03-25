/*
 * This file is part of DoIt.
 * 
 * DoIt is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * DoIt is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public License
 * along with DoIt.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Copyright 2009 Roger Leite
 */

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
		public boolean accept(final File file) {
			return file.isDirectory();
		}
	};

	private static FileFilter jarOnlyFilter = new FileFilter() {
		public boolean accept(final File file) {
			return file.isFile() && file.getName().toLowerCase().endsWith(".jar");
		}
	};

	/**
	 * <p>
	 * This method uses the following rules to look for expression packs:
	 * <ul>
	 * <li>First, list all folders inside the directory parameter.</li>
	 * <li>Second, for each folder, inside should be only <b>one</b> jar file.
	 * If inside contains more then one jar, pack will be ignored.</li>
	 * <li>Third, and <u>optional</u>, if pack has dependencies, pack finder
	 * will read the lib folder (inside the pack folder).</li>
	 * </p>
	 * For more details in this operation, please see the online documentation.
	 * 
	 * @param directory
	 *            to look for new expression packs.
	 * @return list of {@link FolderPack} found in directory parameter.
	 */
	public List<FolderPack> lookForPacks(final String directory) {
		// TODO: add javadoc details for exception cases.
		final File dir = new File(directory);
		if (!dir.exists() || !dir.isDirectory()) {
			final String m = String.format("Not valid directory '%s'.", directory);
			throw new RuntimeException(m);
		}
		final List<FolderPack> packs = new ArrayList<FolderPack>();
		for (final File subDir : dir.listFiles(directoryOnlyFilter)) {
			final List<File> jarFiles = Arrays.asList(subDir.listFiles(jarOnlyFilter));
			if (jarFiles.size() == 1) {
				File[] libJars = null;

				final File libFolder = new File(subDir, "lib");
				if (libFolder.exists() && libFolder.isDirectory()) {
					libJars = libFolder.listFiles(jarOnlyFilter);
				}

				final FolderPack folderPack = new FolderPack(subDir.getName(), jarFiles.get(0), libJars);
				packs.add(folderPack);
			}
		}
		return packs;
	}

	public List<ExpressionPack> instantiatePacks(final List<FolderPack> packs) {
		// TODO add javadoc with possible exceptions
		final List<ExpressionPack> exprPacks = new ArrayList<ExpressionPack>();
		final JarFileLoader jarFileLoader = new JarFileLoader();
		for (final FolderPack folderPack : packs) {
			jarFileLoader.addPack(folderPack);

			final String packClass = folderPack.getClassName();
			final ExpressionPack exprPack = jarFileLoader.load(packClass);

			exprPacks.add(exprPack);
		}
		return exprPacks;
	}

}
