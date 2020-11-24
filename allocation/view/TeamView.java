package view;

import domain.*;
import service.NameListService;
import service.TeamService;

public class TeamView {
	
	private NameListService listSvc = new NameListService();
	private TeamService teamSvc = new TeamService();
	
	public void enterMainMenu(){
		listSvc.setUpEmployees();
		boolean quit = false;
		while(!quit){
			System.out.println("-----------------------开发团队调度软件-----------------------");
			System.out.println("ID\t姓名\t年龄\t工资\t职位\t状态\t奖金\t股票\t领用设备");
			Employee[] emps = listSvc.getAllEmployee();
			listAllEmployees(emps); 
			System.out.println("--------------------------------------------------------------");
			System.out.println("1-团队列表\t2-添加团队成员\t3-删除团队成员\t4-退出 \t 请选择（1-4）：__");
			char option = TSUtility.readMenuSelection();
			switch(option){
			case '1': 
				getTeam();
				break;
			case '2':
				addMember();
				break;
			case '3':
				deleteMember();
				break;
			case '4':
				quit = true;
				break;
			}
			
		}
		
	}
	
	private String generateEqInfo(Equipment e){
		String eqInfo = "";
		if(e instanceof PC){
			eqInfo = ((PC)e).getModel() + "(" + ((PC)e).getDisplay() + ")";
		}else if (e instanceof NoteBook){
			eqInfo = ((NoteBook)e).getModel() + "(" + ((NoteBook)e).getPrice() + ")";
		}else{
			eqInfo = ((Printer)e).getName() + "(" + ((Printer)e).getType() + ")";
		}
		return eqInfo;
	}
	
	private void getTeam(){
		Programmer[] team = teamSvc.getTeam();
		System.out.println("-------------------------团队成员列表-------------------------");
		System.out.println("TID/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票\t");
		for (Programmer p : team){
			String eqInfo = "";
			String nameInfo = "";
			if(p instanceof Architect){
				Architect tmp = (Architect) p;
				eqInfo = generateEqInfo(tmp.getEquipment());
				nameInfo = p.getMemberId()+"/"+p.getId() + '\t' + p.getName() + '\t' + 
						p.getAge() + '\t' + p.getSalary() +  '\t' + "架构师" +  '\t' + tmp.getBonus() + '\t' + tmp.getStock();
			}else if (p instanceof Designer){
				Designer tmp = (Designer) p;
				eqInfo = generateEqInfo(tmp.getEquipment());
				nameInfo = p.getMemberId()+"/"+p.getId() + '\t' + p.getName() + '\t' + 
						p.getAge() + '\t' + p.getSalary() +  '\t' + "设计师" +  '\t'+tmp.getBonus() ;
			}else if (p instanceof Programmer){
				Programmer tmp = (Programmer) p;
				eqInfo = generateEqInfo(tmp.getEquipment());
				nameInfo = p.getMemberId()+"/"+p.getId() + '\t' + p.getName() + '\t' + 
						p.getAge() + '\t' + p.getSalary() +  '\t' + "程序员";
			}
			System.out.println(nameInfo);
		}
	}
	
	private void listAllEmployees(Employee[] emps){
		for (Employee emp : emps){
			String eqInfo = "";
			String nameInfo = "";
			if(emp instanceof Architect){
				Architect tmp = (Architect) emp;
				eqInfo = generateEqInfo(tmp.getEquipment());
				nameInfo = emp.getId() + "\t" + emp.getName() + '\t' + 
						emp.getAge() + '\t' + emp.getSalary() +  '\t' + "架构师" +  '\t' +
						tmp.getStatus() + '\t' + tmp.getBonus() + '\t' + tmp.getStock();
			}else if (emp instanceof Designer){
				Designer tmp = (Designer) emp;
				eqInfo = generateEqInfo(tmp.getEquipment());
				nameInfo = emp.getId() + "\t" + emp.getName() + '\t' + 
						emp.getAge() + '\t' + emp.getSalary() +  '\t' + "设计师" +  '\t' +
						tmp.getStatus()+ '\t' + tmp.getBonus() + '\t' ;
			}else if (emp instanceof Programmer){
				Programmer tmp = (Programmer) emp;
				eqInfo = generateEqInfo(tmp.getEquipment());
				nameInfo = emp.getId() + "\t" + emp.getName() + '\t' + 
						emp.getAge() + '\t' + emp.getSalary() +  '\t' + "程序员" +  '\t' +
						tmp.getStatus()+ '\t' + '\t' ;
			}else{
				nameInfo = emp.getId() + "\t" + emp.getName() + '\t' + 
						emp.getAge() + '\t' + emp.getSalary() +  '\t' +  '\t' +
						'\t' + '\t' + '\t' + '\t' ;
			}
			System.out.println(nameInfo + '\t' + eqInfo);
		}
	}
	

	
	private void addMember(){
		System.out.println("-------------------------添加成员-------------------------");
		System.out.println("请输入要添加的员工ID：");
		int crew = TSUtility.readInt();
		try{
			teamSvc.addMember(listSvc.getEmployee(crew));
			System.out.println("添加成功");
		} catch(Exception e){
			System.out.println("添加失败，原因： "+e.getMessage());
		}
		System.out.println("按回车键继续...");
		TSUtility.readReturn();
	}
	
	private void deleteMember(){
		System.out.println("-------------------------删除成员-------------------------");
		System.out.println("请输入要删除的员工TID：");
		int crew = TSUtility.readInt();
		
		System.out.println("确认是否删除（Y/N）：");
		
		char confirm = TSUtility.readConfirmSelection();
		if (confirm == 'Y'){
			teamSvc.removeMember(crew);
			System.out.println("删除成功");
		}
		System.out.println("按回车键继续...");
		TSUtility.readReturn();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TeamView t = new TeamView();
		t.enterMainMenu();
	}

}
