package com.flz.myspring.core.support;

import com.flz.myspring.core.io.Resource;
import com.flz.myspring.core.io.ResourceLoader;
import com.flz.myspring.ioc.beans.basic.BeanDefinition;
import com.flz.myspring.ioc.beans.basic.BeanDefinitionRegistry;
import com.flz.myspring.ioc.beans.basic.BeanReference;
import com.flz.myspring.ioc.beans.basic.PropertyValue;
import com.flz.myspring.ioc.beans.basic.PropertyValues;
import com.flz.myspring.ioc.beans.exception.BeansException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 解析xml并注册bean
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        super(beanDefinitionRegistry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry, ResourceLoader resourceLoader) {
        super(beanDefinitionRegistry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try (InputStream inputStream = resource.getInputStream()) {
            doLoadBeanDefinition(inputStream);
        } catch (Exception e) {
            throw new BeansException(e.getMessage(), e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        Resource resource = getResourceLoader().loadResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location : locations) {
            this.loadBeanDefinitions(location);
        }
    }

    private void doLoadBeanDefinition(InputStream inputStream) throws DocumentException, ClassNotFoundException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputStream);
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements("bean");
        if (CollectionUtils.isEmpty(elements)) {
            throw new BeansException("no bean tag element can be parsed from xml");
        }

        for (Element e : elements) {
            String beanName = e.attributeValue("id");
            String beanClassName = e.attributeValue("class");
            Class<?> beanClass = Class.forName(beanClassName);
            BeanDefinition beanDefinition = new BeanDefinition(beanClass);
            List<Element> properties = e.elements("property");
            PropertyValues propertyValues = processPropertyValues(properties);
            beanDefinition.setPropertyValues(propertyValues);
            getRegistry().registryBeanDefinition(beanName, beanDefinition);
        }

    }

    private PropertyValues processPropertyValues(List<Element> properties) {
        if (CollectionUtils.isEmpty(properties)) {
            return new PropertyValues();
        }

        List<PropertyValue> propertyValues = properties.stream()
                .map((p) -> {
                    String name = p.attributeValue("name");
                    String value = p.attributeValue("value");
                    String ref = p.attributeValue("ref");
                    Object valueObj = value;

                    // property标签中，value和ref不能同时出现
                    if (StringUtils.isNotEmpty(value) && StringUtils.isNotEmpty(ref)) {
                        throw new BeansException("property tag does not allow both 'value' and 'ref' property present at same time");
                    }

                    if (StringUtils.isNotEmpty(ref)) {
                        valueObj = new BeanReference(ref.trim());
                    }

                    return new PropertyValue(name, valueObj);
                })
                .collect(Collectors.toList());
        return new PropertyValues() {{
            addPropertyValues(propertyValues);
        }};
    }
}
