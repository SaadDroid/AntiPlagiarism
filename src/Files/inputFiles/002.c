 #include<stdio.h>
 #include<math.h>

 void cmp (int *a, int *b)
 {
      if (*a<*b)
      {
           int temp = &a ;
           *a = *b ;
           *b = temp ;
      }
 }


 int main()
 {
     int a, b, c ;
     scanf("%d%d%d", &a,&b,&c) ;

     cmp(&a, &b) ;
     cmp(&a, &c) ;
     cmp(&b, &c) ;

     printf("%d %d %d\n", a, b, c) ;
 }