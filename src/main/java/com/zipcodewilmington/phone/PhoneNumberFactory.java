package com.zipcodewilmington.phone;

import com.zipcodewilmington.exceptions.InvalidPhoneNumberFormatException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by leon on 5/1/17.
 */
public final class PhoneNumberFactory {
    private static final Logger logger = Logger.getGlobal();

    private PhoneNumberFactory() {
        /** This constructor is private
         *  This class is uninstantiable */
    }

    /**
     * @param phoneNumberCount - number of PhoneNumber objects to instantiate
     * @return array of randomly generated PhoneNumber objects
     */ //TODO - Implement logic
    public static PhoneNumber[] createRandomPhoneNumberArray(int phoneNumberCount) {
        PhoneNumber[] arr = new PhoneNumber[phoneNumberCount];
        for (int i = 0; i < phoneNumberCount; i++) {
            arr[i] = createRandomPhoneNumber();
        }
        return arr;
    }

    /**
     * @return an instance of PhoneNumber with randomly generated phone number value
     */ //TODO - Implement logic
    public static PhoneNumber createRandomPhoneNumber() {
        int areaCode = (int)Math.floor(Math.random()*(999-200+1)+200);
        int centralOfficeCode = (int)Math.floor(Math.random()*(999-200+1)+200);
        int phoneLineCode = (int)Math.floor(Math.random()*(9999+1));
        return createPhoneNumberSafely(areaCode, centralOfficeCode, phoneLineCode);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(String.format("%04d", (int)Math.floor(Math.random()*(9999+1))));
        }
    }


    /**
     * @param areaCode          - 3 digit code
     * @param centralOfficeCode - 3 digit code
     * @param phoneLineCode     - 4 digit code
     * @return a new phone number object
     */ //TODO - if input is valid, return respective PhoneNumber object, else return null
    public static PhoneNumber createPhoneNumberSafely(int areaCode, int centralOfficeCode, int phoneLineCode) {
        String plc = String.format("%04d", phoneLineCode);
        try {
            return createPhoneNumber("(" + areaCode + ")-" + centralOfficeCode + "-" + plc);
        } catch (InvalidPhoneNumberFormatException e) {
            logger.log(Level.WARNING, "(" + areaCode + ")-" + centralOfficeCode + "-" + plc + " is not a valid phone number");
            return null;
        }
    }

    /**
     * @param phoneNumberString - some String corresponding to a phone number whose format is `(###)-###-####`
     * @return a new phone number object
     * @throws InvalidPhoneNumberFormatException - thrown if phoneNumberString does not match acceptable format
     */ // TODO - Add throws statement to method signature
    public static PhoneNumber createPhoneNumber(String phoneNumberString) throws InvalidPhoneNumberFormatException {
        logger.log(Level.INFO,"Attempting to create a new PhoneNumber object with a value of " + phoneNumberString);
        return new PhoneNumber(phoneNumberString);
    }
}
