package os;

import java.util.*;

public class FcfsIO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Number of process : " );
		int n = sc.nextInt(),bt,j=0,currt=0,maxct=-1,sumbt=0,a;
		float total_tat = 0,total_wt = 0,total_rt =0,total_it = 0;
		int[] iscompleted = new int[n];
		int[] at2 = new int[n];
		int[] bt2 = new int[n];
		
		process[] p = new process[n];
	
		//arrival
		System.out.print("Enter Arrival Time : " );
		for(int i=0;i<n;i++) {
			
			p[i] = new process();
			a = sc.nextInt();
			at2[i] = a;
			p[i].at = a;
		}
		//burst 1
		System.out.print("Enter CPU Burst Time before IO  Burst: " );
		for(int i=0;i<n;i++) {
			a = sc.nextInt();
			bt2[i] = a;
			sumbt += a;
			p[i].bt1 = a;
		}
		//IO
		System.out.print("Enter Io Burst time : " );
		for(int i=0;i<n;i++) {
			p[i].io = sc.nextInt();
		}	
		//burst 2
		System.out.print("Enter CPU Burst time after IO Burst : " );
		for(int i=0;i<n;i++) {
			p[i].bt2 = sc.nextInt();
			sumbt += p[i].bt2;
		}

		
		System.out.println("\n**********************************************************************************");
		System.out.println("\t\t\t\tGantt chart");
		System.out.println("------------------------------------------------------------------------------------------");
		while(j!=n) {
			int idx=-1;
			int m = 100000;
			for(int i=0;i<n;i++) {
				if(at2[i]<m && iscompleted[i]!=-1) {
					m = at2[i];
					idx = i;
				}
			}
			if(idx!=-1) {
				 if(currt>=at2[idx]) {
					 
					 if(iscompleted[idx] == 0) {
						 System.out.print(currt + " p"+(idx+1)+"  ");
						 p[idx].st = currt;
						 currt += bt2[idx];
						 at2[idx] = currt + p[idx].io;
						 bt2[idx] = p[idx].bt2;
						 iscompleted[idx] = 1; 
						 System.out.print(currt+" | ");
					 }
					 else {
						 
						 p[idx].ct = currt + bt2[idx];
				         p[idx].tat = p[idx].ct - p[idx].at;
				         p[idx].wt = p[idx].tat - (p[idx].bt1+p[idx].io+p[idx].bt2);
				         p[idx].rt = p[idx].st - p[idx].at;
				            
				         total_tat += p[idx].tat;
				         total_wt += p[idx].wt;
				         total_rt += p[idx].rt;
				            
				         iscompleted[idx] = -1;
						 j+=1; 
						 System.out.print(currt + " p"+(idx+1)+"  "+p[idx].ct+" | ");
						 currt = p[idx].ct;
						 
						 
						 if(p[idx].ct>maxct) {
				            	maxct = p[idx].ct;
				            }
					 }
				 }
				 else {
					 currt+=1;
				 }
			}
			else {
				break;
			}
			
		}
		System.out.println("\n------------------------------------------------------------------------------------------\n");
		
		
		
		
		
		System.out.println("**********************************************************************************");
		System.out.println("Process \t AT \t BT1 \t IO \t BT2 \tCT \tTAT \t WT \t RT");
		System.out.println("----------------------------------------------------------------------------------");
		for(j = 0;j<n;j++) {
			System.out.println(j+1 +"\t\t" +p[j].at + "\t"+ p[j].bt1+"\t" + p[j].io+"\t"+ p[j].bt2 + "\t"+p[j].ct + "\t" + p[j].tat + "\t" +p[j].wt +  "\t" +p[j].rt);
		}
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t\t" + total_tat + "\t" + total_wt + "\t" + total_rt);
		System.out.println("**********************************************************************************\n");
		
		System.out.println("Average Turn Around Time = "+total_tat/n + "ms");
		System.out.println("Average Waiting Time = "+total_wt/n + "ms");
		System.out.println("Average Response Time = "+total_rt/n + "ms");
		System.out.println("Cpu Utilization = "+(double)sumbt/maxct);
		System.out.println("Through put = "+(double)n/maxct);
		
		System.out.println("Press 0 for exit");
		int exit = sc.nextInt();
	}

}


class process {
	int at,bt1,bt2,io,tat,wt,rt,ct,st;
}
