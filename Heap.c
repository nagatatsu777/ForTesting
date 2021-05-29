//Author: Tatusnori Nagashima
#include <stdio.h>
#include <stdlib.h>
typedef struct{
    int key;//To indicate the position
    int val;//Actual value
}ipq_t;
typedef struct{
    ipq_t *s;
    int size;//size
    int memory;//memory allocated
}heap_data;
void recurse(int node, heap_data*h){//recursieve method for sorting elements
    if(node==0){
    return;
    }
    if(node%2==0&&node!=2){//If the node number is even
        if(h->s[node/2-1].val<h->s[node].val){//Check for the parent node and the child node
            int temp = h->s[node].val;//simple swapping
            h->s[node].val=h->s[node/2-1].val;
            h->s[node/2-1].val=temp;
            recurse(node/2-1,h);//recurse it
        }
        return;
    }
    else{//If the inserted node is odd
        if(h->s[node/2].val<h->s[node].val){//Check for the parent node and the child node
            int temp = h->s[node].val;//simple swapping
            h->s[node].val=h->s[node/2].val;
            h->s[node/2].val=temp;
            recurse(node/2,h);//recurse it
        }
        return;
    }

}
void ad(int a,heap_data * h){//adding element
    if((h->size)>=h->memory){//run when the element added will exceed the maximum memory
        h->memory = h->memory*2;
    if(h->size!=0){//run when there is at least one element
        h->s[h->size].val=a;
        h->s[h->size].key=h->size;
        h->size++;//increment the size
        recurse(h->size-1,h);//recurse it
    }
        ipq_t * new_pointer=realloc(h->s,sizeof(ipq_t)*h->memory);//Resize the array
        if (new_pointer == NULL){//just for checking, may be deleted
            printf("Resize Error\n");
            return;
        }
        else{
            h->s = new_pointer;//assigning to the new pointer
        }
        h->s[h->size].val=a;
        h->s[h->size].key=h->size;
        h->size++;//increment the size
        recurse(h->size-1,h);//recurse it
    }
    else  if(h->size!=0){//run when there is at least one element
          h->s[h->size].val=a;
          h->s[h->size].key=h->size;
          h->size++;//increment the size
          recurse(h->size-1,h);//recurse it
     }
    else{
        h->s[0].val = a;//set the initial value
        h->s[0].key=0;
        h->size++;//increment thee size
    }
}
void  rm(int a,heap_data *h){
    if(h->size==0){
        printf("Error\n");       
    }
    else{
        h->s[a].val = 0;//since this is heap, no memory deletion
        //move the element to the end
        for(int i = a+1; i<h->size;i++){
            int temp = h->s[i].val;//swap the value one after the value to put to the end
            h->s[i].val=h->s[i-1].val;
            h->s[i-1].val = temp;
        }
        h->size--;//decrement the size

        recurse(h->size-1,h);//reorder
    }
}
int main(){
    heap_data * h;
    h=malloc(sizeof(h));
    h->s = calloc(sizeof(ipq_t),4);

   // h->pos = calloc(sizeof(int),12);, not needed for now:w
   
    h->size = 0;
    h->memory = 4;
    int input;
    while(1){
        printf("Choose the operation:\n1.Add\n2.Remove\n3.View\n");
        scanf("%d",&input);
        if(input==1){//Add an element
            int add;
            printf("Type in the numbers to add\n");
            scanf("%d",&add);
            ad(add,h);
        }
        else if(input==2){//Remove an element
            int rem;
            printf("Type in the position number to remove\n");
            scanf("%d",&rem);
            rm(rem,h);
        }
        else if(input==3){//View elements
            for(int i=0;i<h->memory;i++){
                printf("|%d|  %d\n",h->s[i].val,h->s[i].key);
            }
        }
        else{//Handles an error
            printf("ERROR");
        }
    }
}
