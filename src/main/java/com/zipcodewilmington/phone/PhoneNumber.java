package com.zipcodewilmington.phone;

import com.zipcodewilmington.exceptions.InvalidPhoneNumberFormatException;

import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by leon on 5/10/17.
 */
public final class PhoneNumber {
    private final String phoneNumberString;
    private static final Logger LOGGER = Logger.getLogger(PhoneNumber.class.getName());

    // non-default constructor is package-protected
    protected PhoneNumber(String phoneNumber) throws InvalidPhoneNumberFormatException {
        //validate phone number with format `(###)-###-####`
        if (!phoneNumber.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) {
            throw new InvalidPhoneNumberFormatException();
        }
        this.phoneNumberString = phoneNumber;
    }

    public static PhoneNumber createPhoneNumber(String phoneNumber) throws InvalidPhoneNumberFormatException {
        LOGGER.info("Attempting to create a new PhoneNumber object with a value of " + phoneNumber);
        return new PhoneNumber(phoneNumber);
    }

    public static PhoneNumber createPhoneNumberSafely(int areaCode, int centralOfficeCode, int phoneLineCode) {
        String phoneNumber = "(" + areaCode + ")-" + centralOfficeCode + "-" + phoneLineCode;
        try {
            return createPhoneNumber(phoneNumber);
        } catch (InvalidPhoneNumberFormatException e) {
            LOGGER.warning(phoneNumber + " is not a valid phone number");
            return null;
        }
    }

    public static PhoneNumber createRandomPhoneNumber() {
        Random rand = new Random();
        int areaCode = rand.nextInt(900) + 100; // to generate 3 digit number
        int centralOfficeCode = rand.nextInt(900) + 100;
        int phoneLineCode = rand.nextInt(9000) + 1000; // to generate 4 digit number

        return createPhoneNumberSafely(areaCode, centralOfficeCode, phoneLineCode);
    }

    public static PhoneNumber[] createRandomPhoneNumberArray(int count) {
        PhoneNumber[] phoneNumbers = new PhoneNumber[count];
        for (int i = 0; i < count; i++) {
            phoneNumbers[i] = createRandomPhoneNumber();
        }
        return phoneNumbers;
    }

    public String getAreaCode() {
        return toString().substring(1, 4);
    }

    public String getCentralOfficeCode() {
        return toString().substring(6, 9);
    }

    public String getPhoneLineCode() {
        return toString().substring(10, 14);
    }

    @Override
    public String toString() {
        return phoneNumberString;
    }
}
