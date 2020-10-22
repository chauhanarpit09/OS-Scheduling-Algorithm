package os;

import java.util.*;

public class Srtf {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Number of process : " );
		int n = sc.nextInt(),bt,j=0,currt=0,maxct=-1,sumbt=0;
		float total_tat = 0,total_wt = 0,total_rt =0,total_it = 0;
		int[] iscompleted = new int[n];
		int[] bt2 = new int[n];
		Proces[] p = new Proces[n];
	
		
		for(int i=0;i<n;i++) {
			System.out.print("Enter Burst And Arrival Time : ");
			
			bt = sc.nextInt();
			bt2[i] = bt;
			sumbt+=bt;
			p[i] = new Proces(sc.nextInt(),bt);
		}
		
		

		System.out.println("\n**********************************************************************************");
		System.out.println("\t\t\t\tGantt chart");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		while(j!=n) {
			int idx =-1,m=100000;
			for(int i=0;i<n;i++) {
				if(p[i].at<=currt && iscompleted[i] == 0) {
					if(bt2[i]<m) {
						m = bt2[i];
						idx = i;
					}
					if(bt2[i] == m) {
						if(p[i].at < p[idx].at) {
							m = bt2[i];
							idx = i;
						}
					}
				}
			}
			if(idx!=-1) {
				
				if(bt2[idx] == p[idx].bt) {
					p[idx].st = currt;
				}
				System.out.print(currt + " p"+(idx+1));
				bt2[idx]-=1;
				currt+=1;
				System.out.print("  "+currt + " | ");
				if(bt2[idx] <= 0) {
					p[idx].ct = currt;
					p[idx].tat = p[idx].ct - p[idx].at;
		            p[idx].wt = p[idx].tat - p[idx].bt;
		            p[idx].rt = p[idx].st - p[idx].at;
		            
		            total_tat += p[idx].tat;
		            total_wt += p[idx].wt;
		            total_rt += p[idx].rt;          
		            iscompleted[idx] = 1;
		            j+=1;
		            
		            if(p[idx].ct>maxct) {
		            	maxct = p[idx].ct;
		            }
		            
				}
				
				
			}
			else {
				currt+=1;
			}	
		}
        System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------\n");
		
		System.out.println("**********************************************************************************");
		System.out.println("Process \t AT \t BT \t ST \t CT \tTAT \t WT \t RT");
		System.out.println("----------------------------------------------------------------------------------");
		for(j = 0;j<n;j++) {
			System.out.println(j+1 +"\t\t" +p[j].at + "\t"+ p[j].bt+"\t" + p[j].st+"\t"+ p[j].ct + "\t" + p[j].tat + "\t" +p[j].wt +  "\t" +p[j].rt);
		}
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t" + total_tat + "\t" + total_wt + "\t" + total_rt);
		System.out.println("------------------------------------------------------------------------------------");
		
		System.out.println("Average Turn Around Time = "+total_tat/n + "ms");
		System.out.println("Average Waiting Time = "+total_wt/n + "ms");
		System.out.println("Average Response Time = "+total_rt/n + "ms");
		System.out.println("Cpu Utilization = "+(double)sumbt/maxct);
		System.out.println("Through put = "+(double)n/maxct);
		
		System.out.println("Press 0 for exit");
		int exit = sc.nextInt();
	}

}
