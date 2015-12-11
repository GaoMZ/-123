package com.njupt.kangaroo.dao;

public class Relationship {

	private String childname;
	private String parentname;
	
	public String getChildname() {
		return childname;
	}

	public void setChildname(String childname) {
		this.childname = childname;
	}

	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}

	public Relationship() {
		// TODO Auto-generated constructor stub
	}
	public Relationship(String childname,String parentname) {
		this.childname=childname;
		this.parentname=parentname;
	}

	@Override
	public String toString() {
		return "Relationship [childname=" + childname + ", parentname="
				+ parentname + "]";
	}
	
}
