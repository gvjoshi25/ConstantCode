package edu.constant.code.constructor.di;

public class ConstructorDI {

	ConstructorDIHelper helper;

	public ConstructorDI(ConstructorDIHelper helper) {
		this.helper = helper;
	}

	public void verify() {
		this.helper.verify();
	}
}
