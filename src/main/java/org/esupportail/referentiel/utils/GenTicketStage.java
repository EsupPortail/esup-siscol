package org.esupportail.referentiel.utils;

/**
 * @author dhouillo
 *
 */

/**
 * Generate a random alphanumeric string for TicketStage - cvthèque.
 *
 */ 
public class GenTicketStage {

	/**
     * The code chars.
     */
    private static String validChars = "123456789";

    /**
     * The code length.
     */
    private int codeLength;

    /**
     * Constructor.
     * @param codLen
     */
    public GenTicketStage(final int codLen) {
        codeLength = codLen;
    }

    /**
     * Generate the random code.
     * @return resultCode
     *
     */
    public String generate() {
        StringBuffer resultCode = new StringBuffer();
        int maxIndex = validChars.length();
        java.util.Random rnd = new java.util.Random();

        for (int i = 0; i < codeLength; i++) {
            int rndPos = Math.abs(rnd.nextInt() % maxIndex);
            resultCode.append(validChars.charAt(rndPos));
        }

        return resultCode.toString();
    }

} 

