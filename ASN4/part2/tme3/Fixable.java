package tme3;

/**
 * Interface for Fixable of the greenhouse.
 * 
 * @author: Junesung Lee
 * @date : Aug 23, 2023
 */
public interface Fixable {
    /**
     * Fix the issue presents in the greenhouse.
     */
    void fix();

    /**
     * logs the detail of the fix to the text file.
     */
    void log();
}