package edu.constant.code.setter.di.verify;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import edu.constant.code.setter.di.SetterDI;

@SuppressWarnings("deprecation")
public class VerifySetterDI {

	public static void main(String[] args) {
		Resource r = new ClassPathResource("applicationContext.xml");
		BeanFactory factory = new XmlBeanFactory(r);

		SetterDI cdi = (SetterDI) factory.getBean("setterDI");
		cdi.verify();
	}
}
