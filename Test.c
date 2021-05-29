#include <stdio.h>
#include <stdlib.h>
typedef struct{
     int maze[5][5];
     int visited[5][5];
     int checker;
}examp_struct;
int trace(int row, int col, int endrow, int endcol, examp_struct * a){
   printf("row: %d col: %d,visited: %d, maze: %d\n",row, col,a->visited[row][col],a->maze[row][col]);
   if(row== -1 || col==-1 || row==5 || col==5){
        return 0;
    }
    if(a->checker==3)
    {
    printf("CHECKIGN");
    return 1;
    }
    if(row == endrow && col == endcol){
        printf("FOUND\n");
        a->checker=3;
        return 1;
    }
    if(a->visited[row][col]!=0){
        return 0;
     
    }
    if(a->maze[row][col]==0){
        return 0;
    }
    a->visited[row][col] = 1;
    trace(row+1, col,endrow,endcol,a);
    trace(row-1, col,endrow,endcol,a);
    trace(row,col+1,endrow,endcol,a);
    trace(row, col-1,endrow,endcol,a);
    return 0;
}
int main(){
    examp_struct* c = malloc(sizeof(c));
    int row = 0;
    int col = 0;
    int temp [5][5] = {
          1,1,0,1,0,
          0,1,1,1,1,
          1,1,1,0,0,
          0,1,1,1,1,
          0,1,0,0,1
     }; 
    for(int i = 0;i<5;i++){
        for(int j = 0; j<5;j++){
            c->maze[i][j] = temp[i][j];
        }
    }
    for(int i = 0;i<5;i++){
        for(int j = 0; j<5;j++){
            c->visited[i][j] = 0;
        }   
    } 
    c->checker = 0;
   trace(row,col,1,4,c);
   if(c->checker==0){
    printf("NOT FOUND");
   
   }
    free(c);
}
