package service;

import domain.Architect;
import domain.Designer;
import domain.Employee;
import domain.Programmer;
import domain.Status;

public class TeamService {
	
	private static int count = 1;
	private int total = 0;
	private final int MAX_MEMBER = 5;
	private Programmer[] team = new Programmer[MAX_MEMBER];
	
	public Programmer[] getTeam(){
		return team;
	}
	
	public void addMember(Employee e){
		if (total == MAX_MEMBER){
			throw new TeamException("成员已满，无法添加");
		} 
		if (!( e instanceof Programmer)){
			throw new TeamException("该成员不是开发人员，无法添加");
		}
		int numArch = 0, numDes = 0, numPro = 0;
		for (int i =0; i < total; i++){
			Programmer p = team[i];
			if (p.getId() == e.getId()){
				throw new TeamException("该员工已在开发团队中");
			}
			if (team[i] instanceof Architect)
				numArch++;
			else if (team[i] instanceof Designer)
				numDes++;
			else
				numPro++;
		}
		//
		Programmer p = (Programmer) e;
		switch(p.getStatus()){
			case BUSY:
				throw new TeamException("该员工已是某团队成员");
			case VACATION:
				throw new TeamException("该员工正在休假，无法参加");
		}

		
		if(e instanceof Architect && numArch >= 1)
			throw new TeamException("团队中至多只有一名架构师");
		if (e instanceof Designer &&numDes >= 2)
			throw new TeamException("团队中至多只能有两名设计师");
		if (e instanceof Programmer && numPro >=3){
			throw new TeamException("团队中至多只能有三名程序员");
		}
		
		team[total] = p;
		total++;
		p.setStatus(Status.BUSY);
		p.setMemberId(count++);
		
	}
	
	public void removeMember(int memberId){
		if (memberId == total){
			team[total-1].setStatus(Status.FREE);
			
			
		}else{
			for ( int i = 0; i < total-1; i++){
				if(team[i].getMemberId() == memberId){
					team[i].setStatus(Status.FREE);
					team[i] = team[i+1];
				}
				if (team[i].getMemberId() > memberId){
					team[i].setMemberId(team[i].getMemberId()-1);
					team[i] = team[i+1];
				}
			}
		}
		team[total-1]=null;
		count--;
		total--;
	}
	
	public int getCount(){
		return count;
	}
	
	public static void setCount(int count){
		TeamService.count = count;
	}
	
	public int getTotal(){
		return total;
	}
	
	public void setTotal(int total){
		this.total = total;
	}
	
	public int getMAX_NUMBER(){
		return MAX_MEMBER;
	}
	
}
