#include<stdio.h>
int main(){
    int x, y, z, temp ;
    scanf("%d%d%d", &x,&y,&z) ;
    if(a<c) { //swapping
        temp= x ;
        x=z ;
        z=temp ;
    }
    if(a<b) { //swapping
        temp=x ;
        x=y ;
        y=temp ;
    }
    if(b<c) {
        temp=y ;
        y=z ;
        z=temp ;
    }
    printf("%d\t%d\t%d\n",x,y,z) ;
    /*very nice code
    isn't it*/
}
