package domain;

public class Printer implements Equipment{
	
	private String name;
	private String type;
	
	public Printer(String name, String type){
		this.name = name;
		this.type = type;
	}
	
	public String getDescription(){
		return "机器的类型： " + type + "  机器的型号：" + name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getType(){
		return type;
	}
}
