package package1;


public class Example {
    public int ReadNumber(String number) throws NumberFormatException {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Number is wrong!!!");
        }
    }
}