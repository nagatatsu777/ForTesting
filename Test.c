#include <stdio.h>
#include <stdlib.h>
typedef struct{
    int exampid;
}examp_struct;
int main(){
    examp_struct s;
    s.exampid = 9;
    printf("YAY%d\n", s.exampid);

}
