#include<stdio.h>
#include<conio.h>

#define max 100
struct Process{
    int at,bt,ct,tat,wt,rt,st;
};

void main(){
    int n,currt=0,i,j=0,maxct=-1,sumbt=0,iscompleted[max]={0},idx,m;
    struct Process p[max];
    float total_tat = 0,total_wt = 0,total_rt =0,total_it=0;
    printf("Enter Number of Process : ");
    scanf("%d",&n);

    printf("\n Enter Burst time Process : ");
    for(i=0;i<n;i++){
        scanf("%d",&p[i].bt);
        sumbt+= p[i].bt;
    }
    printf("\n Enter Arrival time Process : ");
    for(i=0;i<n;i++){
        scanf("%d",&p[i].at);
    }

    printf("\n**********************************************************************************");
    printf("\n\t\t\t\tGantt chart");
    printf("\n------------------------------------------------------------------------------------------\n");
    while(j!=n){
        idx=-1;
        m = 100000;
        for(i=0;i<n;i++){
            if(p[i].at<m && iscompleted[i]!=-1){
                m = p[i].at;
                idx = i;
            }
        }
        if(idx!=-1){
            if(currt>=p[idx].at){
                     p[idx].ct = currt + p[idx].bt;
			         p[idx].tat = p[idx].ct - p[idx].at;
			         p[idx].wt = p[idx].tat - p[idx].bt;
			         p[idx].rt = currt - p[idx].at;

			         total_tat += p[idx].tat;
			         total_wt += p[idx].wt;
			         total_rt += p[idx].rt;

			         iscompleted[idx] = -1;
					 j+=1;
					 printf("%d p%d %d | ",currt,idx,p[idx].ct);
					 currt = p[idx].ct;

					 if(p[idx].ct>maxct){
					     maxct = p[idx].ct;
					 }
            }
            else{
                printf("%d ... %d |",currt,currt+1);
                currt+=1;
            }
        }
        else{
            break;
        }
    }
    printf("\n------------------------------------------------------------------------------------------\n");
	printf("\n\n**********************************************************************************");
	printf("\nProcess \t AT \t BT \t CT \tTAT \t WT \t RT");
	printf("\n------------------------------------------------------------------------------------");
	for(j = 0;j<n;j++) {
		printf("\n %d\t\t%d\t%d\t%d\t%d\t%d\t%d\t",j,p[j].at,p[j].bt,p[j].ct,p[j].tat,p[j].wt,p[j].rt);
	}
	printf("\n------------------------------------------------------------------------------------");
	printf("\n\t\t\t\t\t%.2f\t%.2f\t%.2f\t",total_tat,total_wt,total_rt);
	printf("\n------------------------------------------------------------------------------------");

	printf("\nAverage Turn Around Time = %.2f ms",total_tat/n);
	printf("\nAverage Waiting Time = %.2f ms",total_wt/n);
	printf("\nAverage Response Time = %.2f ms",total_rt/n);
	printf("\nCPU utilization %.2f",(float)sumbt/maxct);
	printf("\nThroughput %.2f",(float)n/maxct);

	getch();
}
