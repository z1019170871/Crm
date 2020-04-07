package com.briup.bean;
/** 
* @author 作者 zjf
* @version 创建时间：2020年4月3日 上午10:35:57 
* 说明 : 额外的工具类
*/
public class CustomerConstitute {
	private String name;
	private Float y;
	private String drilldown;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getY() {
		return y;
	}
	public void setY(Float y) {
		this.y = y;
	}
	public String getDrilldown() {
		return drilldown;
	}
	public void setDrilldown(String drilldown) {
		this.drilldown = drilldown;
	}
	public CustomerConstitute(String name, Float y, String drilldown) {
		super();
		this.name = name;
		this.y = y;
		this.drilldown = drilldown;
	}
	public CustomerConstitute() {
		super();
	}
	@Override
	public String toString() {
		return "CustomerConstitute [name=" + name + ", y=" + y + ", drilldown=" + drilldown + "]";
	}
	
	
}
