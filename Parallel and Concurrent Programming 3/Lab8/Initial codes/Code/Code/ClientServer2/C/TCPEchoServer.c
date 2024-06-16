#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>
#include <unistd.h>

#define MAXLINE 4096 /*max text line length*/
#define SERV_PORT 3000 /*port*/
#define LISTENQ 5 /*maximum number of waiting client connections */
#define EXITMESSAGE "EXIT"

int main (int argc, char **argv)
{
 int listenfd, connfd, n;
 socklen_t clilen;
 char buf[MAXLINE];
 struct sockaddr_in cliaddr, servaddr;


 listenfd = socket (AF_INET, SOCK_STREAM, 0);
 
 servaddr.sin_family = AF_INET;
 servaddr.sin_addr.s_addr = htonl(INADDR_ANY);
 servaddr.sin_port = htons(SERV_PORT);

 bind (listenfd, (struct sockaddr *) &servaddr, sizeof(servaddr));
 listen(listenfd, LISTENQ);
 printf("Server running...waiting for connections.\n");

 clilen = sizeof(cliaddr);
 connfd = accept (listenfd, (struct sockaddr *) &cliaddr, &clilen);
 printf("Connection accepted..\n");
  
 while (1)
 {
   //does not work for empty string
   n = recv(connfd, buf, MAXLINE, 0);
   
   buf[n-1] = '\0'; /*remove \n and put \0 instead */
   if  (strcmp(buf, EXITMESSAGE ) == 0) {break;}
   //debugging
   printf("String received from and resent to the client:\n");
   puts(buf);
   
   //process request here
   send(connfd, buf, strlen(buf),0);	
 }   
  
 close(connfd);
 close (listenfd);
}