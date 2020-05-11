package vin.encoder;

/**
 * Exception thrown during validation of VIN
 * in the {@link VinValidatorUtils}
 */
public class InvalidVinException extends Exception {

    private final String wrongVin;

    public InvalidVinException(final String wrongVin) {
        this.wrongVin = wrongVin;
    }

    public InvalidVinException(final String wrongVin,
                               final String message) {
        super(message);

        this.wrongVin = wrongVin;
    }

    public InvalidVinException(final String wrongVin,
                               final String message,
                               final Throwable cause) {
        super(message, cause);

        this.wrongVin = wrongVin;
    }

    public InvalidVinException(final String wrongVin,
                               final Throwable cause) {
        super(cause);

        this.wrongVin = wrongVin;
    }

    public String getWrongVin() {
        return this.wrongVin;
    }
}
