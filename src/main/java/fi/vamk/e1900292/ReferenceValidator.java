package fi.vamk.e1900292;

public class ReferenceValidator {
    
    public static String calculateReferenceNumber(String invoiceNumber) {
        if (invoiceNumber == null || invoiceNumber.length() == 0 || invoiceNumber.length() > 18) {
            throw new IllegalArgumentException("Invalid invoice number");
        }

        // Multiplier series
        int[] multiplierSeries = {7, 3, 1, 7, 3, 1, 7, 3, 1, 7, 3, 1, 7, 3, 1, 7, 3, 1};

        int checkNumber = 0;

        // Calculate check number
        for (int i = 0; i < invoiceNumber.length(); i++) {
            int digit = Character.getNumericValue(invoiceNumber.charAt(invoiceNumber.length() - 1 - i));
            checkNumber += digit * multiplierSeries[i];
        }

        checkNumber = 10 - (checkNumber % 10);
        if (checkNumber == 10) {
            checkNumber = 0;
        }

        // Concatenate invoice number and check number
        String referenceNumber = invoiceNumber + checkNumber;

        // Group numbers in sets of five from right
        StringBuilder groupedReferenceNumber = new StringBuilder();
        int count = 0;
        for (int i = referenceNumber.length() - 1; i >= 0; i--) {
            groupedReferenceNumber.insert(0, referenceNumber.charAt(i));
            count++;
            if (count == 5 && i > 0) {
                groupedReferenceNumber.insert(0, " ");
                count = 0;
            }
        }

        return groupedReferenceNumber.toString();
    }
}