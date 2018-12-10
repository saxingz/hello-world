package org.saxing.delegation;

/**
 * Delegator Class to delegate the implementation of the Printer.
 * This ensures two things:
 *  *  - when the actual implementation of the Printer class changes the delegation will still be operational
 *  *  - the actual benefit is observed when there are more than one implementors and they share a delegation control
 *
 *  @author saxing 2018/12/10 22:21
 */
public class PrinterController implements Printer {

    private final Printer printer;

    public PrinterController(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void print(String message) {
        printer.print(message);
    }
}
