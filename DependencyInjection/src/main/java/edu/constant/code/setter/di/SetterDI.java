package edu.constant.code.setter.di;

public class SetterDI {

	SetterDIHelper setterHelper;

	public void setSetterDI(SetterDIHelper setterHelper) {
		this.setterHelper = setterHelper;
	}

	public void verify() {
		this.setterHelper.verify();
	}
}
