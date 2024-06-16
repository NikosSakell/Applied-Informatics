public class ServerProtocol {
    public String processRequest(String theInput) {
        System.out.println("Received message from client: " + theInput);

        // Split the message, case choice, and offset
        String[] parts = theInput.split(",");
        String message = parts[0]; //Get the message from the client
        int caseChoice = Integer.parseInt(parts[1]); //Get the function number
        

        String theOutput = "";

		//1.1
        if (caseChoice == 1) {
            theOutput = message.toUpperCase();
        //1.2
		} else if (caseChoice == 2) {
            theOutput = message.toLowerCase();
        //1.3
		} else if (caseChoice == 3) {
			int offset = Integer.parseInt(parts[2]);  // Get the offset for Caesar's Cipher
            // Caesar's Cipher implementation
            StringBuilder result = new StringBuilder();
            for (char character : message.toCharArray()) {
                if (Character.isLetter(character)) { // Check for letter
                    char base = Character.isLowerCase(character) ? 'a' : 'A';
                    int originalAlphabetPosition = character - base;
                    int newAlphabetPosition = (originalAlphabetPosition + offset) % 26;
                    char newCharacter = (char) (base + newAlphabetPosition);
                    result.append(newCharacter);
                } else {
                    result.append(character); // Non-letters remain unchanged
                }
            }
            theOutput = result.toString();
		//1.4
        } else if (caseChoice == 4) {
			int offset = Integer.parseInt(parts[2]);  // Get the offset for Caesar's Cipher

            // Caesar's Cipher decryption logic (reverse the encryption process)
            StringBuilder result = new StringBuilder();
            for (char character : message.toCharArray()) {
                if (Character.isLetter(character)) {
                    char base = Character.isLowerCase(character) ? 'a' : 'A';
                    int originalAlphabetPosition = character - base;
                    int newAlphabetPosition = (originalAlphabetPosition - offset + 26) % 26; // Subtract offset
                    char newCharacter = (char) (base + newAlphabetPosition);
                    result.append(newCharacter);
                } else {
                    result.append(character);
                }
            }
            theOutput = result.toString();
        } else {
            theOutput = "Invalid case choice";
        }

        System.out.println("Send message to client: " + theOutput);
        return theOutput;
    }
}

