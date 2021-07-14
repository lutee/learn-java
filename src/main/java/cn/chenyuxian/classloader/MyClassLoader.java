package cn.chenyuxian.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {

	private String root;

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] classData = loadClassData(name);
		if (classData == null) {
			throw new ClassNotFoundException();
		} else {
			return defineClass(name, classData, 0, classData.length);
		}
	}

	private byte[] loadClassData(String className) {
		String fileName = root + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
		try {
			InputStream in = new FileInputStream(fileName);
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];
			int length = 0;
			while ((length = in.read(buffer)) != -1) {
				bout.write(buffer, 0, length);
			}
			return bout.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getRoot() {
		return root;

	}

	public void setRoot(String root) {
		this.root = root;
	}

	public static void main(String[] args) {
		MyClassLoader classLoader = new MyClassLoader();
		classLoader.setRoot("E:\\temp");
		Class<?> testClass = null;
		try {
			testClass = classLoader.loadClass("cn.chenyuxian.classloader.demo.Test2");
			Object obj = testClass.newInstance();
			System.out.println(obj.getClass().getClassLoader());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
