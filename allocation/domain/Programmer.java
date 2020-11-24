package domain;

public class Programmer extends Employee{
	
	private int memberId;
	private Equipment equipment;
	private Status status = Status.FREE;
	
	public Programmer(int id, String name, int age, double salary, Equipment equipment){
		super(id, name, age, salary);
		this.equipment = equipment;
	}
	
	public String getDesc(){
		return super.getDesc() + ", Equipment=[" + equipment.getDescription() + "]";
	}
	
	public void setMemberId(int memberId){
		this.memberId = memberId;
	}
	
	public int getMemberId(){
		return memberId;
	}
	
	public void setEquipment(Equipment e){
		this.equipment = e;
	}
	
	public Equipment getEquipment(){
		return equipment;
	}
	
	public Status getStatus(){
		return status;
	}
	
	public void setStatus(Status s){
		this.status = s;
	}
}
