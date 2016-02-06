package edu.constant.code.setter.di;

public class SetterDI {

	SetterDIHelper setterHelper;

	public void setSetterDI(SetterDIHelper setterHelper) {
		this.setterHelper = setterHelper;
		System.out.println("Inside Setter method.");
	}

	public void verify() {
		this.setterHelper.verify();
	}
}
