import socket

class ServerProtocol:

    def processRequest(self,theInput):
        print("Received message from client: " + theInput)
        theOutput = theInput
        print("Send message to client: " + theOutput)
        return theOutput


class EchoServerTCP:
    #Server address
    PORT = 1234
    serverAdd = ("localhost",PORT)
    EXIT = "CLOSE"

    def main():
        connectionSocket = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        connectionSocket.bind(EchoServerTCP.serverAdd)
        connectionSocket.listen(10)
        print("Server is listening to port: " + str(EchoServerTCP.PORT))

        conn, add = connectionSocket.accept()
        print("Received request from " + str(add))

        inmsg = conn.recv(1024)

        app = ServerProtocol()
        outmsg = app.processRequest(inmsg.decode())
        while(not(outmsg == EchoServerTCP.EXIT)):
            conn.sendall(outmsg.encode())
            inmsg = conn.recv(1024)
            outmsg = app.processRequest(inmsg.decode())

        conn.close()
        print("Data socket closed")

if __name__ == '__main__':
    EchoServerTCP.main()
