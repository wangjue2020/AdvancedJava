package domain;

public class NoteBook implements Equipment {
	
	private String model;
	private double price;
	
	public NoteBook(String model, double price){
		this.model = model;
		this.price = price;
	}
	
	public String getDescription(){
		return "机器的型号： " + model + "  机器的价格： " + price;
	}
	
	public void setModel(String model){
		this.model = model;
	}
	
	public String getModel(){
		return model;
	}
	
	public void setPrice(double price){
		this.price = price;
	}
	
	public double getPrice(){
		return price;
	}
}
