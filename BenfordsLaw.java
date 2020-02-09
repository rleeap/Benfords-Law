import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BenfordsLaw {
    

    public static int[] readData(String filename) throws FileNotFoundException {

        int[] digit_sums = {0,0,0,0,0,0,0,0,0,0}; //array to hold the sums of the first digits of each number
        File f = new File(filename);
        Scanner scanner = new Scanner(f);
        //read through csv file and extract number, line by line
        int int_pop = 0;
        while(scanner.hasNextLine()) {
            String population = scanner.nextLine();
            try {
            int_pop = Integer.parseInt(population);
            } catch (NumberFormatException e) {
                population = scanner.nextLine();
            }
            
            //extract first digit of the population
            while(int_pop >= 10) {
                int_pop = int_pop / 10;
            }
            int first_digit = int_pop;
            // increment the respective array element by 1
            digit_sums[first_digit] ++;

        }
        scanner.close();
        return digit_sums;
       
    }

    public static boolean checkBenfordsLaw(int[] leading_digits) {
        int digit_1 = leading_digits[1]; //sum of occurences of a leading digit of 1
        //iterate through leading_digits. If any digit sum is greater than the sum for 1, 
        // the data set does not follow Benford's Law.
        for(int i = 1; i < leading_digits.length; i ++) {
            if(leading_digits[i] > digit_1) {
                return false;
            }
        }
        return true;

    }

    public static void main(String[] args) {
        int[] digits = {};
        //Some print statements and a scanner to allow user input
        Scanner s = new Scanner(System.in);
        System.out.println("Does The Data Reflect Benford's Law?");
        System.out.println("Enter a csv file of your data to find out!: ");
        String input = s.nextLine();
        
        try {
        digits = readData(input);
        } catch(FileNotFoundException e) {
            System.out.println("Error: file not found.");
            System.exit(0);
        }
        if(checkBenfordsLaw(digits)) {
            System.out.println("The data set follows Benford's Law!");
        }
        else {
            System.out.println("The data set does not follw Benford's Law :(.");
        }
        s.close();
        

    }
}