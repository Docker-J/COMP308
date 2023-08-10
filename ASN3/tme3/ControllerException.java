/* Author: Junesung Lee 
 * Student ID: 3643836
 * Date: Jul 21th, 2023
 * 
 * ControllerException is an exception class for Greenhouse Malfunction
 * 
 * Description:
 * ControllerException(String errorMessage): get error message and pass it to Exception class constructor
*/

package tme3;

public class ControllerException extends Exception {
    public ControllerException(String errorMessage) {
        super(errorMessage);
    }
}