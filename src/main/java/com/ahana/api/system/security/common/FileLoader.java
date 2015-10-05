package com.ahana.api.system.security.common;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public final class FileLoader {

	private FileLoader() {
	}

	private static final String SYSPROP_SEARCH_FILESYSTEM_FIRST = FileLoader.class.getName()+ ".FilesystemFirstMode";

	private static final boolean SEARCH_FILESYSTEM_FIRST = Boolean.getBoolean(SYSPROP_SEARCH_FILESYSTEM_FIRST);

	@SuppressWarnings("deprecation")
	public static File getResourceAsFile(final String name)throws FileNotFoundException {
		URL url = null;
		System.out.println("SEARCH_FILESYSTEM_FIRST  : "+SEARCH_FILESYSTEM_FIRST);
		if (!SEARCH_FILESYSTEM_FIRST) {
			ClassLoader loader = ClassLoaderResolver.getClassLoader(1);
			if (loader != null) {
				url = loader.getResource(name);
			} else {
				url = ClassLoader.getSystemResource(name);
			}
			if (url == null) {
				File f = new File(name);
				if (f.exists()) {
					try {
						url = f.toURL();
					} catch (MalformedURLException ex) {
						ex.printStackTrace();
					}
				}
			}
		} else {
			File f = new File(name);
			if (f.exists()) {
				try {
					url = f.toURL();
				} catch (MalformedURLException ex) {
					ex.printStackTrace();
				}
			}
			// search classpath
			if (url == null) {
				ClassLoader loader = ClassLoaderResolver.getClassLoader(1);
				if (loader != null) {
					url = loader.getResource(name);
				} else {
					url = ClassLoader.getSystemResource(name);
				}
			}
		}
		if (url == null) {
			throw new FileNotFoundException("File " + name + " not found");
		}
		return new File(url.getFile());
	}

	public static File findFileInClassPath(String file) {
		URL url;
		ClassLoader loader = ClassLoaderResolver.getClassLoader(1);
		if (loader != null) {
			url = loader.getResource(file);
		} else {
			url = ClassLoader.getSystemResource(file);
		}
		if (url == null) {
			return null;
		}
		file = url.getFile();
		File f = new File(file);
		if (f.exists()) {
			return f;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public static URL getResourceAsUrl(final String name) {
		URL url = null;
		if (!SEARCH_FILESYSTEM_FIRST) {
			// search thread context_ classpath
			ClassLoader loader = ClassLoaderResolver.getClassLoader(1);
			if (loader != null) {
				url = loader.getResource(name);
			} else {
				url = ClassLoader.getSystemResource(name);
			}

			// search filesystem next
			if (url == null) {
				File f = new File(name);
				if (f.exists()) {
					try {
						url = f.toURL();
					} catch (MalformedURLException ex) {
						// f.exists() check should prevent this exception
						ex.printStackTrace();
					}
				}
			}
		} else {
			// search filesystem
			File f = new File(name);
			if (f.exists()) {
				try {
					url = f.toURL();
				} catch (MalformedURLException ex) {
					// f.exists() check should prevent this exception
					ex.printStackTrace();
				}
			}
			// search classpath
			if (url == null) {
				ClassLoader loader = ClassLoaderResolver.getClassLoader(1);
				if (loader != null) {
					url = loader.getResource(name);
				} else {
					url = ClassLoader.getSystemResource(name);
				}
			}
		}
		return url;
	}

	public static InputStream getResourceAsInputStream(final String name) {
		InputStream is = null;
		if (!SEARCH_FILESYSTEM_FIRST) {
			// search classpath
			ClassLoader loader = ClassLoaderResolver.getClassLoader(1);
			if (loader != null) {
				is = loader.getResourceAsStream(name);
			} else {
				is = ClassLoader.getSystemResourceAsStream(name);
			}
			if (is == null) {
				File f = new File(name);
				if (f.exists()) {
					try {
						is = new FileInputStream(f);
					} catch (FileNotFoundException ex) {
						// f.exists() check should prevent this exception
						ex.printStackTrace();
					}
				}
			}
		} else {
			// search filesystem
			File f = new File(name);
			if (f.exists()) {
				try {
					is = new FileInputStream(f);
				} catch (FileNotFoundException ex) {
					// f.exists() check should prevent this exception
					ex.printStackTrace();
				}
			}
			// search classpath
			if (is == null) {
				ClassLoader loader = ClassLoaderResolver.getClassLoader(1);
				if (loader != null) {
					is = loader.getResourceAsStream(name);
				} else {
					is = ClassLoader.getSystemResourceAsStream(name);
				}
			}
		}
		return is;
	}
	
	public static byte[] getBytesFromInputstream(InputStream in){
		byte[] bytest = null;
		try {
			BufferedInputStream bis = new BufferedInputStream(in);			
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buffer = new byte[512];
			int i = 0;
			while((i=bis.read(buffer))!=-1){
				bos.write(buffer, 0, i);
			}
			bytest=bos.toByteArray();
			bis.close();
			in.close();
			bos.close();
		} catch (Exception e) {
		}
		return bytest;
	}	

}
