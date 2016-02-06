package edu.constant.code.constructor.di.verify;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import edu.constant.code.constructor.di.ConstructorDI;

@SuppressWarnings("deprecation")
public class VerifyConstructorDI {

	public static void main(String[] args) {
		Resource r = new ClassPathResource("applicationContext.xml");
		BeanFactory factory = new XmlBeanFactory(r);

		ConstructorDI cdi = (ConstructorDI) factory.getBean("ConstructorDI");
		cdi.verify();
	}
}
