package cn.chenyuxian.spring.beans.factory.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cn.chenyuxian.spring.beans.BeansException;
import cn.chenyuxian.spring.beans.PropertyValue;
import cn.chenyuxian.spring.beans.factory.config.BeanDefinition;
import cn.chenyuxian.spring.beans.factory.config.BeanReference;
import cn.chenyuxian.spring.beans.factory.support.AbstractBeanDefinitionReader;
import cn.chenyuxian.spring.beans.factory.support.BeanDefinitionRegistry;
import cn.chenyuxian.spring.core.io.Resource;
import cn.chenyuxian.spring.core.io.ResourceLoader;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader{

	protected XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
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

	protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
		Document document = XmlUtil.readXML(inputStream);
		Element root = document.getDocumentElement();
		NodeList childNodes = root.getChildNodes();
		
		for (int i = 0; i < childNodes.getLength(); i++) {
			if(!(childNodes.item(i) instanceof Element)) continue;
			if(!"bean".equals(childNodes.item(i).getNodeName())) continue;
			Element bean = (Element) childNodes.item(i);
			String id = bean.getAttribute("id");
			String name = bean.getAttribute("name");
			String className = bean.getAttribute("class");
			Class<?> clazz = Class.forName(className);
			String beanName = StrUtil.isNotEmpty(id) ? id : name;
			if(StrUtil.isEmpty(beanName)) {
				beanName = StrUtil.lowerFirst(clazz.getSimpleName());
			}
			BeanDefinition beanDefinition = new BeanDefinition(clazz);
			for(int j = 0; j < bean.getChildNodes().getLength(); j++) {
				if(!(bean.getChildNodes().item(j) instanceof Element)) continue;
				if(!"property".equals(bean.getChildNodes().item(j).getNodeName())) continue;
				Element property = (Element) bean.getChildNodes().item(j);
				String attrName = property.getAttribute("name");
				String attrValue = property.getAttribute("value");
				String attrRef = property.getAttribute("ref");
				Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
				PropertyValue propertyValue = new PropertyValue(attrName, value);
				beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
			}
			if(getRegistry().containsBeanDefinition(beanName)) {
				throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
			}
			getRegistry().registerBeanDefinition(beanName, beanDefinition);
		}
	}
}
