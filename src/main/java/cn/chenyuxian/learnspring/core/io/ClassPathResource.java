package cn.chenyuxian.learnspring.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import cn.chenyuxian.learnspring.util.ClassUtils;
import cn.hutool.core.lang.Assert;

public class ClassPathResource implements Resource{

	private final String path;
	
	private ClassLoader classLoader;
	
	public ClassPathResource(String path) {
		this(path, (ClassLoader) null);
	}
	
	public ClassPathResource(String path, ClassLoader classLoader) {
		Assert.notNull(path, "Path must not be null");
		this.path = path;
		this.classLoader = classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader();
	}
	
	@Override
	public InputStream getInputStream() throws IOException {
		InputStream in = classLoader.getResourceAsStream(path);
		if(in == null) {
			throw new FileNotFoundException(path + "cannot be opened because it does not exist");
		}
		return in;
	}

}
