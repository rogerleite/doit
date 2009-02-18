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
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public class FolderPack {

	private static final String MANIFEST_KEY_PACK_CLASS = "DoIt-PackClass";

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

	/**
	 * Gets the class name of the {@link ExpressionPack} of this jar.
	 * @return className specified at manifest attribute 'DoIt-PackClass'.
	 * @throws RuntimeException if manifest or attribute 'DoIt-PackClass' not found.
	 */
	String getClassName() {
		JarFile jar = null;
		try {
			jar = new JarFile(jarFile);
			Manifest manifest = jar.getManifest();

			if (manifest == null) {
				String m = String.format("Manifest not found at jar '%s'.", jarFile);
				throw new RuntimeException(m);
			}

			String packClass = manifest.getMainAttributes().getValue(MANIFEST_KEY_PACK_CLASS);

			if (packClass == null) {
				String m = String.format("Attribute '%s' not found at manifest file of jar '%s'.", MANIFEST_KEY_PACK_CLASS, jarFile);
				throw new RuntimeException(m);
			}

			return packClass;
		} catch (IOException e) {
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
