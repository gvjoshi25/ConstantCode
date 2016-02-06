package edu.constant.code.constructor.di;

public class ConstructorDI {

	ConstructorDIHelper helper;

	public ConstructorDI(ConstructorDIHelper helper) {
		this.helper = helper;
		System.out.println("Inside Constructor");
	}

	public void verify() {
		this.helper.verify();
	}
}
