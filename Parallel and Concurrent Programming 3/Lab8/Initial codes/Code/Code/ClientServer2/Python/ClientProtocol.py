class ClientProtocol:

    def prepareRequest(self):
        theOutput = input("Enter message to send to server: ")
        return theOutput

    def processReply(self,theInput):
        print("Message received from server: " + theInput)
