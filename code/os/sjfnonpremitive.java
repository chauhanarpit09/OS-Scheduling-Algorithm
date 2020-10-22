package os;

import java.util.*;

public class sjfnonpremitive {
	public static void main(String[] args) {
		
		int currt = 0,j = 0,maxct=-1,sumbt=0;
	    float total_tat=0,total_wt=0,total_rt=0;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the Number of Process : ");
		int n = sc.nextInt();
		
		Proces[] p = new Proces[n];
		int[] iscompleted = new int[n];
		
		
		for(int i=0;i<n;i++) {
			int a,b;
			System.out.print("Enter the Arrival and burst Time of process p" +(i+1)+" : ");
			a = sc.nextInt();
			b = sc.nextInt();
			sumbt+=b;
			p[i] = new Proces(a,b);
		}
		
		
		System.out.println("\n**********************************************************************************");
		System.out.println("\t\t\t\tGantt chart");
		System.out.println("------------------------------------------------------------------------------------------");
	    while(j != n) {
	        int idx = -1,m = 100000;
	        for(int i = 0; i < n; i++) {
	            if(p[i].at <= currt && iscompleted[i] == 0) {
	                if(p[i].bt < m) {
	                    m = p[i].bt;
	                    idx = i;
	                }
	                if(p[i].bt == m) {
	                    if(p[i].at < p[idx].at) {
	                        m = p[i].bt;
	                        idx = i;
	                    }
	                }
	            }
	        }
	        if(idx != -1) {
	           
	            p[idx].ct = currt + p[idx].bt;
	            p[idx].tat = p[idx].ct - p[idx].at;
	            p[idx].wt = p[idx].tat - p[idx].bt;
	            p[idx].rt = currt - p[idx].at;
	            
	            total_tat += p[idx].tat;
	            total_wt += p[idx].wt;
	            total_rt += p[idx].rt;
	            

	            iscompleted[idx] = 1;
	            j++;
	            System.out.print(currt + " p"+(idx+1)+"  "+p[idx].ct+" | ");
	            currt = p[idx].ct;
	            
	            if(p[idx].ct>maxct) {
	            	maxct = p[idx].ct;
	            }
	            
	        }
	        else {
	        	System.out.print(currt + "..."+(currt+1)+" | ");
	        	currt++;
	        }
	        
	    }
	    System.out.println("\n------------------------------------------------------------------------------------------\n");
		
		System.out.println("**********************************************************************************");
		System.out.println("Process \t AT \t BT \t CT \tTAT \t WT \t RT");
		System.out.println("------------------------------------------------------------------------------------");
		for(j = 0;j<n;j++) {
			System.out.println(j+1 +"\t\t" +p[j].at + "\t"+ p[j].bt+"\t" + p[j].ct + "\t" + p[j].tat + "\t" +p[j].wt +  "\t" +p[j].rt);
		}
		System.out.println("------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t" + total_tat + "\t" + total_wt + "\t" + total_rt);
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
