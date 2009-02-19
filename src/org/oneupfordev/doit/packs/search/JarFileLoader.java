/**
 * 
 */
package org.oneupfordev.doit.packs.search;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.oneupfordev.doit.ExpressionPack;

/**
 * <p>{@link ClassLoader} that can add Jar files into classpath at runtime.<br>
 * This is class is based on this version (http://snippets.dzone.com/posts/show/3574), available at net.</p>
 * @author Roger Leite
 */
public class JarFileLoader extends URLClassLoader {

	public JarFileLoader() {
		super(getUrlsFromDefaultClassLoader());
	}

	public JarFileLoader(final URL[] urls) {
		super(urls);
	}

	private static URL[] getUrlsFromDefaultClassLoader() {
		ClassLoader cl = JarFileLoader.class.getClassLoader();
		if (!(cl instanceof URLClassLoader)) {
			throw new RuntimeException("Cannot get URLs from Default Class Loader "
					+ "because it is not an instance of URLClassLoader.");
		}
		URLClassLoader urlCl = (URLClassLoader) cl;
		return urlCl.getURLs();
	}

	/**
	 * Add jar file to classpath of this ClassLoader.
	 * @param jarFile a valid JAR file.
	 * @throws RuntimeException if jarPath is invalid.
	 */
	private void addFile(final File jarFile) {
		String jarPath = jarFile.getAbsolutePath();
		String urlPath = "jar:file://" + jarPath + "!/";
		URL url = null;
		try {
			url = new URL(urlPath);
		} catch (MalformedURLException e) {
			throw new RuntimeException("Invalid path. Please check '" + jarPath + "'.", e);
		}
		addURL(url);
	}

	/**
	 * Instantiate an "external" {@link ExpressionPack}, using the "required" jars loaded at runtime.
	 * TODO: include docs of runtime exceptions.
	 * @param className of {@link ExpressionPack} to be loaded.
	 * @return instance of {@link ExpressionPack}.
	 * @throws RuntimeException for any reasons, see at this javadoc body.
	 */
	public ExpressionPack load(final String className) {

		try {
			Class<?> clazz = loadClass(className);
			Object instance = clazz.newInstance();
			if (!(instance instanceof ExpressionPack)) {
				String msg = String.format("Class '%s' not implements ExpressionPack.", clazz);
				throw new RuntimeException(msg);
			}
			return (ExpressionPack) instance;
		} catch (ClassNotFoundException e) {
			String msg = String.format("Class '%s' not found. Please, "
					+ "check if the name is correct.\nClassLoader URLs: %s", className, getURLsAsString());
			throw new RuntimeException(msg, e);
		} catch (InstantiationException e) {
			String msg = String.format("Not possible to instantiate class '%s'. "
					+ "Please, check if contains the default constructor.", className);
			throw new RuntimeException(msg, e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	private String getURLsAsString() {
		StringBuilder urls = new StringBuilder("urls:");
		for (URL url : getURLs()) {
			urls.append(" ").append(url.getFile()).append(" ;");
		}
		return urls.toString();
	}

	public void addPack(final FolderPack folderPack) {
		addFile(folderPack.getJarFile());
		for (File libFile : folderPack.getLibFiles()) {
			addFile(libFile);
		}
	}

}
