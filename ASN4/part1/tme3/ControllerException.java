package tme3;

/**
 * Class that represents ControllerException from the event in the greenhouse.
 * 
 * @author: Junesung Lee
 * @date : Aug 23, 2023
 */
public class ControllerException extends Exception {

    /**
     * Constructor.
     * 
     * @param errorMessage message of the error in String
     */
    public ControllerException(String errorMessage) {
        super(errorMessage);
    }
}