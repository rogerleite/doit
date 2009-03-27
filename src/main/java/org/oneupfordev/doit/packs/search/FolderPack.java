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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.oneupfordev.doit.ExpressionPack;

/**
 * @author Roger Leite
 */
public class FolderPack {

	private static final String MANIFEST_KEY_PACK_CLASS = "DoIt-PackClass";

	private final String folderName;
	private final File jarFile;
	private List<File> libFiles;

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
		final StringBuilder result = new StringBuilder("Folder: ").append(folderName);
		result.append(" Jar: ").append(jarFile.getName());
		if (libFiles != null && !libFiles.isEmpty()) {
			result.append(" Lib: ");
			for (final File f : libFiles) {
				result.append(f.getName()).append("; ");
			}
		}
		return result.toString();
	}

	/**
	 * Gets the class name of the {@link ExpressionPack} of this jar.
	 * 
	 * @return className specified at manifest attribute 'DoIt-PackClass'.
	 * @throws RuntimeException
	 *             if manifest or attribute 'DoIt-PackClass' not found.
	 */
	String getClassName() {
		JarFile jar = null;
		try {
			jar = new JarFile(jarFile);
			final Manifest manifest = jar.getManifest();

			if (manifest == null) {
				final String m = String.format("Manifest not found at jar '%s'.", jarFile);
				throw new RuntimeException(m);
			}

			final String packClass = manifest.getMainAttributes().getValue(MANIFEST_KEY_PACK_CLASS);

			if (packClass == null) {
				final String m = String.format("Attribute '%s' not found at manifest file of jar '%s'.",
						MANIFEST_KEY_PACK_CLASS, jarFile);
				throw new RuntimeException(m);
			}

			return packClass;
		} catch (final IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	File getJarFile() {
		return jarFile;
	}

	List<File> getLibFiles() {
		return libFiles;
	}

}
