import java.util.Random;
import java.util.Scanner;

public class HealthKiosk {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        System.out.println("WELCOME TO ASHESI HEALTH KIOSK");

        char input;
        // Task One - Service Router
        System.out.print("Enter service code(P/L/T/C): ");
        input = sc.next().charAt(0);

        // Converting to uppercase
        input = Character.toUpperCase(input);

        //Switch for selection
        String serviceName;
        switch (input){
            case 'P'->{
                serviceName = "PHARMACY";
                System.out.println("Go to: Pharmacy Desk");
            }
            case 'L'-> {
                serviceName ="LAB";
                System.out.println("Go to: Lab Desk");
            }
            case 'T'->{
                serviceName = "TRIAGE";
                System.out.println("Go to: Triage Desk");
            }
            case 'C'-> {
                serviceName = "COUNSELING";
                System.out.println("Go to: Counseling Desk");
            }
            default ->{
                serviceName ="INVALID";
                System.out.println("Invalid service code");
            }

        }

        //Task two - Mini Health Metric
        double weight =0;
        double height =0;
        double bmi;
        double degrees = 0;
        int input_two = 0;
        int tablets =0;
        double round_bmi = 0;
        double sin_rounded =0;


        if (serviceName.equals("TRIAGE")){
            System.out.println("1 = BMI\n2 = Dosage \n3 = Trig");
            System.out.print("Enter health metric option: ");
            input_two = sc.nextInt();

            switch(input_two){
                case 1 -> {
                    System.out.print("Enter your weight(kg): ");
                    weight= sc.nextDouble();

                    System.out.print("Enter your height(m): ");
                    height = sc.nextDouble();

                    bmi =  weight/Math.pow(height,2);
                    round_bmi = Math.round(bmi*10)/10.0;

                    String category;
                    if(round_bmi < 18.5) {
                        category = "underweight";
                    }
                    else if(round_bmi < 25.0) {
                        category ="Normal";
                    }
                    else if(round_bmi < 30){
                        category="Over Weight";
                    }
                    else{
                        category ="Obese";
                    }

                    System.out.printf("BMI: %.1f",round_bmi);
                    System.out.printf("\nCategory: %s",category);

                }

                case 2 ->{
                    double dosage;

                    System.out.print("Enter the required dosage(mg): ");
                    dosage = sc.nextDouble();

                    if (dosage >= 250) {
                        tablets = (int) Math.ceil(dosage / 250.0);
                        System.out.printf("The number of tablets is %d\n", tablets);
                    } else {
                        System.out.println("Dosage must be at least 250 mg to dispense ");
                    }
                }



                case 3 -> {
                    System.out.print("Enter an angle in degrees: ");
                    degrees = sc.nextDouble();

                    double radians = Math.toRadians(degrees);
                    double sin = Math.sin(radians);
                    double cos = Math.cos(radians);

                    sin_rounded = Math.round(sin * 1000) / 1000.0;
                    double cos_rounded = Math.round(cos * 1000) / 1000.0;

                    System.out.printf("Sin: %.2f", sin_rounded);
                    System.out.printf("\nCos: %.2f", cos_rounded);

                }
                default -> System.out.println("Invalid option");


            }
        }
        // Task three - ID Sanity Check
        Random rand = new Random();

        char letter;
        int n1,n2,n3,n4;
        String shortID;

        letter = (char) ('A' + rand.nextInt(26));

        n1 = rand.nextInt(7)+3;
        n2 =  rand.nextInt(7)+3;
        n3 =  rand.nextInt(7)+3;
        n4 =  rand.nextInt(7)+3;

        shortID = ""+letter + n1 + n2 + n3 + n4;
        System.out.printf("\nGenerated ID: %s",shortID);

        if(shortID.length() != 5){
            System.out.println("\nLength must be 5");
        }
        else if(!Character.isLetter(shortID.charAt(0))){
            System.out.println("First character must be a letter");
        }
        else if (!(Character.isDigit(shortID.charAt(1)) &&
                Character.isDigit(shortID.charAt(2)) &&
                Character.isDigit(shortID.charAt(3)) &&
                Character.isDigit(shortID.charAt(4)))) {
            System.out.println("Invalid: last 4 must be digits");
        }
        else {
            System.out.println("\nID OK");
        }

        // Task 4 - Secure Display Code
        System.out.print("Enter your first name: ");
        String name = sc.next();

        char base = Character.toUpperCase(name.charAt(0));
        System.out.println("Code = " + base);

        char shifted = (char) ('A' + (base - 'A' + 2) % 26);
        System.out.println("Shifted letter of code = " + shifted);

        String last_two = shortID.substring(shortID.length()-2);
        System.out.printf("Last two characters for ID: %s",last_two);

        int metric_value =0;
        if(input_two == 1){
            metric_value =(int) Math.round(weight / Math.pow(height,2));
        }
        else if(input_two == 2){
            metric_value = tablets;
        }
        else if(input_two == 3){
            metric_value = (int) Math.round(Math.sin(Math.toRadians(degrees)) * 100);
        }

        String code = shifted +last_two + "-" + metric_value;

        System.out.printf("\nDisplay Code: %s",code);

        // Task five - Service Summary
        String summary;
        if (serviceName.equals("TRIAGE")) {
            if (input_two == 1) {
                summary = serviceName + " | ID=" + shortID + " | BMI=" + round_bmi + " | Code=" + code;
            } else if (input_two == 2) {
                summary = serviceName + " | ID=" + shortID + " | Tablets=" + tablets + " | Code=" + code;
            } else if (input_two == 3) {
                summary = serviceName + " | ID=" + shortID + " | TrigVal="  + sin_rounded +" | Code=" + code;
            } else {
                summary = serviceName + " | ID=" + shortID + " | Code=" + code;
            }
        } else {
            summary = serviceName + " | ID=" + shortID + " | Code=" + code;
        }

        System.out.println("\nSummary: " + summary);












































    }
}
