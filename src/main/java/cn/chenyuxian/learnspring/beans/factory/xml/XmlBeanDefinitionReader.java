package cn.chenyuxian.learnspring.beans.factory.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cn.chenyuxian.learnspring.beans.BeansException;
import cn.chenyuxian.learnspring.beans.PropertyValue;
import cn.chenyuxian.learnspring.beans.factory.config.BeanDefinition;
import cn.chenyuxian.learnspring.beans.factory.config.BeanReference;
import cn.chenyuxian.learnspring.beans.factory.support.AbstractBeanDefinitionReader;
import cn.chenyuxian.learnspring.beans.factory.support.BeanDefinitionRegistry;
import cn.chenyuxian.learnspring.core.io.Resource;
import cn.chenyuxian.learnspring.core.io.ResourceLoader;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader{

	public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
		super(registry);
	}
	
	public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
		super(registry, resourceLoader);
	}

	@Override
	public void loadBeanDefinitions(Resource resource) throws BeansException {
		try {
			try(InputStream in = resource.getInputStream()) {
				doLoadBeanDefinitions(in);
			}
		} catch (IOException | ClassNotFoundException e) {
			throw new BeansException("IOException parsing XML document from " + resource, e);
		}
	}

	@Override
	public void loadBeanDefinitions(Resource... resources) throws BeansException {
		for(Resource resource : resources) {
			loadBeanDefinitions(resource);
		}
	}

	@Override
	public void loadBeanDefinitions(String location) throws BeansException {
		ResourceLoader resourceLoader = getResourceLoader();
		Resource resource = resourceLoader.getResource(location);
		loadBeanDefinitions(resource);
	}
	
	@Override
	public void loadBeanDefinitions(String... locations) throws BeansException {
		for(String location : locations) {
			loadBeanDefinitions(location);
		}
	}

	protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
		Document document = XmlUtil.readXML(inputStream);
		Element root = document.getDocumentElement();
		NodeList childNodes = root.getChildNodes();
		
		for (int i = 0; i < childNodes.getLength(); i++) {
			
			// 判断元素
			if(!(childNodes.item(i) instanceof Element)) continue;
			
			// 判断对象
			if(!"bean".equals(childNodes.item(i).getNodeName())) continue;
			
			// 解析标签
			Element bean = (Element) childNodes.item(i);
			String id = bean.getAttribute("id");
			String name = bean.getAttribute("name");
			String className = bean.getAttribute("class");
			String initMethod = bean.getAttribute("init-method");
			String destroyMethod = bean.getAttribute("destroy-method");
			String beanScope = bean.getAttribute("scope");
			
			// 获取class，方便获取类中的名称
			Class<?> clazz = Class.forName(className);
			// 优先级 id > name
			String beanName = StrUtil.isNotEmpty(id) ? id : name;
			if(StrUtil.isEmpty(beanName)) {
				beanName = StrUtil.lowerFirst(clazz.getSimpleName());
			}
			
			// 定义Bean
			BeanDefinition beanDefinition = new BeanDefinition(clazz);
			beanDefinition.setInitMethodName(initMethod);
			beanDefinition.setDestroyMethodName(destroyMethod);
			
			if(StrUtil.isNotEmpty(beanScope)) {
				beanDefinition.setScope(beanScope);
			}
			
			// 读取属性并填充
			for(int j = 0; j < bean.getChildNodes().getLength(); j++) {
				if(!(bean.getChildNodes().item(j) instanceof Element)) continue;
				if(!"property".equals(bean.getChildNodes().item(j).getNodeName())) continue;
				
				// 解析标签：property
				Element property = (Element) bean.getChildNodes().item(j);
				String attrName = property.getAttribute("name");
				String attrValue = property.getAttribute("value");
				String attrRef = property.getAttribute("ref");
				
				// 获取属性值：引入对象、值对象
				Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
				
				// 创建属性信息
				PropertyValue propertyValue = new PropertyValue(attrName, value);
				beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
			}
			if(getRegistry().containsBeanDefinition(beanName)) {
				throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
			}
			
			// 注册BeanDefinition
			getRegistry().registerBeanDefinition(beanName, beanDefinition);
		}
	}

}
