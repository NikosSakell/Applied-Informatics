#include <stdio.h>
#include <simpio.h>
#include "genlib.h"
#include <unistd.h>

double froutakia(double xrhmata,int pol, double pontarisma);
int main(){
int age,ans2,ans1;
double B, bet, a;
printf("PLEASE INSERT YOUR AGE: ");
while (TRUE){
age=GetInteger();
if (age>=21)break;
else printf("\nYOU ARE NOT ALLOWED TO PLAY ");
}
printf("\nSOTSIATA SLOTS\nWELCOME \nPLEASE DEPOSIT MONEY(ex. 150.00) : ");
B=GetReal();
while (TRUE){
printf("\n\nBALANCE: %g\n\n", B);
printf(" |*********************| \n");
printf("~~~~~~~~~ SLOTS ~~~~~~~~~   O\n");
printf("|   x   |   x   |   x   |   |\n",219);
printf("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c  |\n", 219, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 219,219);
printf("%c   x   |   x   |   x    %c~\\|\n",219 ,219,219);
printf("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c~~| \n", 219, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 219,222);
printf("|   x   |   x   |   x   | ~/ \n");
printf("~~~~~~~~~~~~~~~~~~~~~~~~~\n");
printf(" |                     |\n");
printf(" |                     |\n");
printf(" |                     |\n");
printf(" |                     |\n");
printf(" |                     |\n");
printf(" |                     |\n");
printf(" |                     |\n");
printf(" /+++++++++++++++++++++\\ \n\n");
printf("\n\n\aPLEASE PLACE YOUR BET(1.00 - 1000.00) : ");
while (TRUE){
bet=GetReal();
if (bet>=0.50 && bet<=1000.00 && bet<=B)break;
printf("\n\aPlease insert a valid bet ");}
printf("\nHOW MANY TIMES DO YOU WANT TO BET IT ? ");
while (TRUE){
ans1=GetInteger();
if ((ans1*bet)<=B)break;
printf("\nERROR You do not have enough money ");
}
a=froutakia(B,ans1,bet);
B=a;
if (B<=0){
printf("\nNO MONEY!");
break;}
printf("\nBET AGAIN ? (1 for YES 0 for NO) ");
ans2=GetInteger();
if (ans2==0)break;
}
printf("\nTOTAL BALANCE: %d ",B);


return 0;
}

double froutakia(double xrhmata,int pol,double pontarisma){
int k,i,j,l;
double w,b,num[3][3];
char Symbols[3][3];
b=xrhmata-pol*pontarisma;
srand(time(0));
for (k=0;k<pol;k++){
    for(i=0;i<3;i++){
        for(j=0;j<3;j++){
            num[i][j]=rand()%100/10;
            if(num[i][j]==1)
            {Symbols[i][j]=55;}
            if(num[i][j]==2)
            {Symbols[i][j]=36;}
            if(num[i][j]==3)
            {Symbols[i][j]=75;}
            if(num[i][j]==4)
            {Symbols[i][j]=74;}
            if(num[i][j]==5)
            {Symbols[i][j]=65;}
            if(num[i][j]==6)
            {Symbols[i][j]=64;}
            if(num[i][j]==7)
            {Symbols[i][j]=90;}
            if(num[i][j]==8)
            {Symbols[i][j]=81;}
            if(num[i][j]==9)
            {Symbols[i][j]=35;}
            if(num[i][j]==0)
            {Symbols[i][j]=48;}
        }}
    system("cls");
    printf("\nBALANCE: %g", b);
    sleep(1);
    printf("\n\n |*********************|   \n");
    printf("~~~~~~~~~ SLOTS ~~~~~~~~~   O\n");
    printf("|   %c   |   %c   |   %c   |   |\n",Symbols[0][0],Symbols[0][1],Symbols[0][2],219);
    printf("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c  |\n", 219, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 223, 219, 219);
    printf("%c   %c   |   %c   |   %c    %c~\\| \n",219,Symbols[1][0],Symbols[1][1],Symbols[1][2],219,219);
    printf("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c~~| \n", 219, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 220, 219,222);
    printf("|   %c   |   %c   |   %c   | ~/ \n",Symbols[2][0],Symbols[2][1],Symbols[2][2],220);
    printf("~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    printf(" |                     |\n");
    printf(" |                     |\n");
    printf(" |                     |\n");
    printf(" |                     |\n");
    printf(" |                     |\n");
    printf(" |                     |\n");
    printf(" |                     |\n");
    printf(" /+++++++++++++++++++++\\ \n\n");
    if (Symbols[1][0]=='7' && Symbols[1][1]=='7' && Symbols[1][2]=='7'){
        w=pontarisma*1000;
        printf("\n\aMEGA WIN  X1000!!!!! \n", w);
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[1][0]=='$' && Symbols[1][1]=='$' && Symbols[1][2]=='$'){
        w=pontarisma*500;
        printf("\n\aULTRA WIN X500!!!!! \n", w);
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[1][0]=='K' && Symbols[1][1]=='K' && Symbols[1][2]=='K'){
        w=pontarisma*200;
        printf("\n\aBIG WIN X200!!!!! \n", w);
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[1][0]=='@' && Symbols[1][1]=='@' && Symbols[1][2]=='@'){
        w=pontarisma;
        printf("\n\aYOU WON YOUR BET BACK !!!\n");//MH SOU TYXEI XAXAXA
        b=b+w;
    }
    if (Symbols[1][0]=='J' && Symbols[1][1]=='J' && Symbols[1][2]=='J'){
        w=pontarisma*100;
        printf("\n\aBIG WIN X100!!!!! \n", w);
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[1][0]=='A' && Symbols[1][1]=='A' && Symbols[1][2]=='A'){
        w=pontarisma*100;
        printf("\n\aBIG WIN X100!!!!! \n", w);
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[1][0]=='Z' && Symbols[1][1]=='Z' && Symbols[1][2]=='Z'){
        w=pontarisma*100;
        printf("\n\aBIG WIN X100!!!!! \n", w);
        for (l=0;l<w+pontarisma;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[1][0]=='Q' && Symbols[1][1]=='Q' && Symbols[1][2]=='Q'){
        w=pontarisma*50;
        printf("\n\aBIG WIN X50!!!!! \n", w);
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[1][0]=='#' && Symbols[1][1]=='#' && Symbols[1][2]=='#'){
        w=pontarisma*100;
        printf("\n\aBIG WIN X100!!!!! \n", w);
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[1][0]=='0' && Symbols[1][1]=='0' && Symbols[1][2]=='0'){
        w=pontarisma*100;
        printf("\n\aBIG WIN X100!!!!! \n", w);
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][0]=='7' && Symbols[0][1]=='7' && Symbols[0][2]=='7'){
        w=pontarisma*10;
        printf("\n\aBIG WIN X10!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][0]=='$' && Symbols[0][1]=='$' && Symbols[0][2]=='$'){
        w=pontarisma*10;
        printf("\n\aBIG WIN X10!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][0]=='K' && Symbols[0][1]=='K' && Symbols[0][2]=='K'){
        w=pontarisma*10;
        printf("\n\aBIG WIN X10!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][0]=='J' && Symbols[0][1]=='J' && Symbols[0][2]=='J'){
        w=pontarisma*10;
        printf("\n\aBIG WIN X10!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][0]=='A' && Symbols[0][1]=='A' && Symbols[0][2]=='A'){
        w=pontarisma*10;
        printf("\n\aBIG WIN X10!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][0]=='@' && Symbols[0][1]=='@' && Symbols[0][2]=='@'){
        w=pontarisma*10;
        printf("\n\aBIG WIN X10!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][0]=='Z' && Symbols[0][1]=='Z' && Symbols[0][2]=='Z'){
        w=pontarisma*10;
        printf("\n\aBIG WIN X10!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][0]=='Q' && Symbols[0][1]=='Q' && Symbols[0][2]=='Q'){
        w=pontarisma*10;
        printf("\n\aBIG WIN X10!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][0]=='#' && Symbols[0][1]=='#' && Symbols[0][2]=='#'){
        w=pontarisma*10;
        printf("\n\aBIG WIN X10!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(1000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][0]=='0' && Symbols[0][1]=='0' && Symbols[0][2]=='0'){
        w=pontarisma*10;
        printf("\n\aBIG WIN X10!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
        if (Symbols[2][0]=='7' && Symbols[2][1]=='7' && Symbols[2][2]=='7'){
        w=pontarisma*10;
        printf("\n\aBIG WIN X10!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[2][0]=='$' && Symbols[2][1]=='$' && Symbols[2][2]=='$'){
        w=pontarisma*10;
        printf("\n\aBIG WIN X10!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[2][0]=='K' && Symbols[2][1]=='K' && Symbols[2][2]=='K'){
        w=pontarisma*10;
        printf("\n\aBIG WIN X10!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[2][0]=='J' && Symbols[2][1]=='J' && Symbols[2][2]=='J'){
        w=pontarisma*10;
        printf("\n\aBIG WIN X10!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[2][0]=='A' && Symbols[2][1]=='A' && Symbols[2][2]=='A'){
        w=pontarisma*10;
        printf("\n\aBIG WIN X10!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[2][0]=='@' && Symbols[2][1]=='@' && Symbols[2][2]=='@'){
        w=pontarisma*10;
        printf("\n\aBIG WIN X10!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[2][0]=='Z' && Symbols[2][1]=='Z' && Symbols[2][2]=='Z'){
        w=pontarisma*10;
        printf("\n\aBIG WIN X10!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(1000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[2][0]=='Q' && Symbols[2][1]=='Q' && Symbols[2][2]=='Q'){
        w=pontarisma*10;
        printf("\n\aBIG WIN X10!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[2][0]=='#' && Symbols[2][1]=='#' && Symbols[2][2]=='#'){
        w=pontarisma*10;
        printf("\n\aBIG WIN X10!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[2][0]=='0' && Symbols[2][1]=='0' && Symbols[2][2]=='0'){
        w=pontarisma*10;
        printf("\n\aBIG WIN X10!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
        if (Symbols[0][0]=='7' && Symbols[1][0]=='7' && Symbols[2][0]=='7'){
        w=pontarisma*5;
        printf("\n\aBIG WIN X5!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][0]=='$' && Symbols[1][0]=='$' && Symbols[2][0]=='$'){
        w=pontarisma*5;
        printf("\n\aBIG WIN X5!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][0]=='K' && Symbols[1][0]=='K' && Symbols[2][0]=='K'){
        w=pontarisma*5;
        printf("\n\aBIG WIN X5!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][0]=='J' && Symbols[1][0]=='J' && Symbols[2][0]=='J'){
        w=pontarisma*5;
        printf("\n\aBIG WIN X5!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][0]=='A' && Symbols[1][0]=='A' && Symbols[2][0]=='A'){
        w=pontarisma*5;
        printf("\n\aBIG WIN X5!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][0]=='@' && Symbols[1][0]=='@' && Symbols[2][0]=='@'){
        w=pontarisma*5;
        printf("\n\aBIG WIN X5!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][0]=='Z' && Symbols[1][0]=='Z' && Symbols[2][0]=='Z'){
        w=pontarisma*5;
        printf("\n\aBIG WIN X5!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(1000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][0]=='Q' && Symbols[1][0]=='Q' && Symbols[2][0]=='Q'){
        w=pontarisma*5;
        printf("\n\aBIG WIN X5!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][0]=='#' && Symbols[1][0]=='#' && Symbols[2][0]=='#'){
        w=pontarisma*5;
        printf("\n\aBIG WIN X5!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][0]=='0' && Symbols[1][0]=='0' && Symbols[2][0]=='0'){
        w=pontarisma*10;
        printf("\n\aBIG WIN X10!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][1]=='7' && Symbols[1][1]=='7' && Symbols[2][1]=='7'){
        w=pontarisma*5;
        printf("\n\aBIG WIN X5!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][1]=='$' && Symbols[1][1]=='$' && Symbols[2][1]=='$'){
        w=pontarisma*5;
        printf("\n\aBIG WIN X5!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][1]=='K' && Symbols[1][1]=='K' && Symbols[2][1]=='K'){
        w=pontarisma*5;
        printf("\n\aBIG WIN X5!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][1]=='J' && Symbols[1][1]=='J' && Symbols[2][1]=='J'){
        w=pontarisma*5;
        printf("\n\aBIG WIN X5!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][1]=='A' && Symbols[1][1]=='A' && Symbols[2][1]=='A'){
        w=pontarisma*5;
        printf("\n\aBIG WIN X5!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][1]=='@' && Symbols[1][1]=='@' && Symbols[2][1]=='@'){
        w=pontarisma*5;
        printf("\n\aBIG WIN X5!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][1]=='Z' && Symbols[1][1]=='Z' && Symbols[2][1]=='Z'){
        w=pontarisma*5;
        printf("\n\aBIG WIN X5!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(1000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][1]=='Q' && Symbols[1][1]=='Q' && Symbols[2][1]=='Q'){
        w=pontarisma*5;
        printf("\n\aBIG WIN X5!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][1]=='#' && Symbols[1][1]=='#' && Symbols[2][1]=='#'){
        w=pontarisma*5;
        printf("\n\aBIG WIN X5!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][1]=='0' && Symbols[1][1]=='0' && Symbols[2][1]=='0'){
        w=pontarisma*10;
        printf("\n\aBIG WIN X10!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][2]=='7' && Symbols[1][2]=='7' && Symbols[2][2]=='7'){
        w=pontarisma*5;
        printf("\n\aBIG WIN X5!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][2]=='$' && Symbols[1][2]=='$' && Symbols[2][2]=='$'){
        w=pontarisma*5;
        printf("\n\aBIG WIN X5!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][2]=='K' && Symbols[1][2]=='K' && Symbols[2][2]=='K'){
        w=pontarisma*5;
        printf("\n\aBIG WIN X5!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][2]=='J' && Symbols[1][2]=='J' && Symbols[2][2]=='J'){
        w=pontarisma*5;
        printf("\n\aBIG WIN X5!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][2]=='A' && Symbols[1][2]=='A' && Symbols[2][2]=='A'){
        w=pontarisma*5;
        printf("\n\aBIG WIN X5!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][2]=='@' && Symbols[1][2]=='@' && Symbols[2][2]=='@'){
        w=pontarisma*5;
        printf("\n\aBIG WIN X5!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][2]=='Z' && Symbols[1][2]=='Z' && Symbols[2][2]=='Z'){
        w=pontarisma*5;
        printf("\n\aBIG WIN X5!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(1000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][2]=='Q' && Symbols[1][2]=='Q' && Symbols[2][2]=='Q'){
        w=pontarisma*5;
        printf("\n\aBIG WIN X5!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][2]=='#' && Symbols[1][2]=='#' && Symbols[2][2]=='#'){
        w=pontarisma*5;
        printf("\n\aBIG WIN X5!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
    if (Symbols[0][2]=='0' && Symbols[1][2]=='0' && Symbols[2][2]=='0'){
        w=pontarisma*10;
        printf("\n\aBIG WIN X10!!!!! \n");
        for (l=0;l<w+pontarisma/10;l=l+pontarisma){
            usleep(2000);
            printf("%d\r",l);
        }
        b=b+w;
    }
sleep(1.2);
system("cls");
}
return b;
}


