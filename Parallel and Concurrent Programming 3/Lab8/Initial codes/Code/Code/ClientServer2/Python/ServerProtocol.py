class ServerProtocol:

    def processRequest(self,theInput):
        print("Received message from client: " + theInput)
        theOutput = theInput
        print("Send message to client: " + theOutput)
        return theOutput
