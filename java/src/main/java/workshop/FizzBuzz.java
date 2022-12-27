package RefactorPractice;

public class FizzBuzz {
    public String checkNumber(int number) {
        String strReturn = null;

        if (number % 15 == 0) {
            strReturn = "FizzBuzz";
        } else {
            strReturn = threeAndFive(number);
        }

        if (strReturn != null) return strReturn;
        return String.valueOf(number);
    }

     public  String threeAndFive(int number){
            if (number % 3 == 0) return  "Fizz";
            if (number % 5 == 0) return "Buzz";
            return null;
        }
}
