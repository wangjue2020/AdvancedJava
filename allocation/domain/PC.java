package domain;

public class PC implements Equipment{
	
	private String model;
	private String display;
	
	public PC(String model, String display){
		this.model = model;
		this.display = display;
	}
	
	@Override
	public String getDescription(){
		return "机器型号：	"+model + " 机器名称： " + display;
	}
	
	public void setModel(String model){
		this.model = model;
	}
	
	public String getModel(){
		return model;
	}
	
	public void setDisplay(String display){
		this.display = display;
	}
	
	public String getDisplay(){
		return display;
	}
}
