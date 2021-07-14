package cn.chenyuxian.classloader;

public class loaderTest {
	public static void main(String[] args) throws ClassNotFoundException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		//System.out.println("使用了laodclass执行初始化类");
		//loader.loadClass("cn.chenyuxian.classloader.demo.Test2");
		//System.out.println("使用了forName来初始化类");
		//Class.forName("cn.chenyuxian.classloader.demo.Test2");
		System.out.println("========");
		Class.forName("cn.chenyuxian.classloader.demo.Test2", false, loader);
	}
}


