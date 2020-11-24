package service;

import domain.*;
import org.testng.annotations.Test;

public class NameListService {
	
	private Employee[] employees;
	
	public NameListService(){
		employees = new Employee[Data.EMPLOYEES.length];
		setUpEmployees();
	}
	
	public void setUpEmployees(){
		
		for (int i = 0; i < employees.length; i++){
			String[] curEmployee = Data.EMPLOYEES[i];
			String[] curEquip = Data.EQUIPMENTS[i];
			Employee employee = (Employee) createEmployee(curEmployee, curEquip);
			if (employee instanceof Architect){
				employees[i] = (Architect) employee;
			} else if (employee instanceof Designer){
				employees[i] = (Designer) employee;
			} else if (employee instanceof Programmer){
				employees[i] = (Programmer) employee;
			} else{
				employees[i] = (Employee) employee;
			}
//			System.out.println(employee.getDesc());
			
		}
	}
	
	public Object createEmployee(String[] curEmployee, String[] curEquip){
		int empType = Integer.parseInt(curEmployee[0]);
		Object obj = null;
		//13, id, name, age, salary, bonus, stock
		int id = Integer.parseInt(curEmployee[1]);
		String name = curEmployee[2];
		int age = Integer.parseInt(curEmployee[3]);
		double salary = Double.parseDouble(curEmployee[4]);
		PC curPC = null;
		NoteBook curNoteBook = null;
		Printer curPrinter = null;
		double bonus = 0.0;
		int stock = 0;
		
		switch(empType){
		case Data.EMPLOYEE:
			//employee
			obj = new Employee(id,name, age, salary);
			break;
		case Data.PROGRAMMER:
			//programmer
			if (curEquip[0].equals("21")){
				curPC = new PC(curEquip[1], curEquip[2]);
				obj = new Programmer(id, name, age, salary,curPC);
			}else if (curEquip[0].equals("22")){
				curNoteBook = new NoteBook(curEquip[1], Double.valueOf(curEquip[2]));
				obj = new Programmer(id, name, age, salary,curNoteBook);
			}else{
				curPrinter = new Printer(curEquip[1], curEquip[2]);
				obj = new Programmer(id, name, age, salary,curPrinter);
			}
			break;
		case Data.DESIGNER:
			//designer
			bonus = Double.valueOf(curEmployee[5]);
			if (Integer.parseInt(curEquip[0]) == Data.PC){
				curPC = new PC(curEquip[1], curEquip[2]);
				obj = new Designer(id, name, age, salary,curPC,bonus);
			}else if (Integer.parseInt(curEquip[0]) == Data.NOTEBOOK){
				curNoteBook = new NoteBook(curEquip[1], Double.valueOf(curEquip[2]));
				obj = new Designer(id, name, age, salary,curNoteBook, bonus);
			}else{
				curPrinter = new Printer(curEquip[1], curEquip[2]);
				obj = new Designer(id, name, age, salary,curPrinter, bonus);
			}
			break;
		case Data.ARCHITECT:
			//architect
			bonus = Double.valueOf(curEmployee[5]);
			stock = Integer.parseInt(curEmployee[6]);
			if (curEquip[0].equals("21")){
				curPC = new PC(curEquip[1], curEquip[2]);
				obj = new Architect(id, name, age, salary,curPC,bonus, stock);
			}else if (curEquip[0].equals("22")){
				curNoteBook = new NoteBook(curEquip[1], Double.valueOf(curEquip[2]));
				obj = new Architect(id, name, age, salary,curNoteBook, bonus, stock);
			}else{
				curPrinter = new Printer(curEquip[1], curEquip[2]);
				obj = new Architect(id, name, age, salary,curPrinter, bonus, stock);
			}
			break;
			
		}
		return obj;
	}
	
	public Employee getEmployee(int id){
		for ( Employee e : employees){
			if (e.getId() == id){
				return e;
			}
		}
		throw new TeamException("找不到指定员工");
	}
	
	public Employee[] getAllEmployee(){
		return employees;
	}
	/*
	 * NameListService JUnit testing
	 */
	@Test
	public void  main(){
		NameListService nls = new NameListService();

		System.out.println("===");
		nls.setUpEmployees();
		Employee[] emps = nls.getAllEmployee();
		for (int i = 0; i < emps.length; i++){
			System.out.println("===");
			System.out.println(emps[i].getDesc());
		}
		System.out.println(nls.getEmployee(4).getDesc());
		//nls.getEmployee(15);
		//nls.getEmployee(-1);
	}
}
