#include<stdio.h>

 void swap(int *a, int *b)
 {
       int temp= &a;
       *a= *b;
       *b= temp;
 }

void b_sort(int* a, int n)
{
    for(int i=0; i<n; i++)
        for(int j=i+1; j<n; j++)
            if(a[i]<a[j]) swap(&a[i], &a[j]);
}

int main()
{
    int a[3];

    scanf("%d%d%d",&a[0],&a[1],&a[2]);

    b_sort(a, 3);

    printf("%d %d %d\n", a[0], a[1], a[2]);
}