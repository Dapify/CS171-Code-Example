import java.util.Random;
import java.util.Scanner;

public class Lab10 {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Q1();
        Q2();
        Q3();
        Q4();
        scan.close();
    }

    public static void Q1() {
        double a, b, r;
        String Cval = "";
        String Aval = "";

        while (true) {
            System.out.println("Pick a shape: square, rectangle, circle (or 'q' to quit)");
            String input = scan.nextLine();

            if (input.equals("q")) {
                return;
            }

            if (input.equals("square")) {
                System.out.println("Enter the length of side a: ");
                a = Double.parseDouble(scan.nextLine());
                Cval = "square is: " + a * 4;
                Aval = "square is: " + a * a;

            } else if (input.equals("rectangle")) {
                
                System.out.println("Enter the length of side a: ");
                a = Double.parseDouble(scan.nextLine());
                System.out.println("Enter the length of side b: ");
                b = Double.parseDouble(scan.nextLine());
                Cval = "rectangle is: " + (2 * a + 2 * b);
                Aval = "rectangle is: " + (a * b);

            } else if (input.equals("circle")) {
                System.out.println("Enter the radius: ");
                r = Double.parseDouble(scan.nextLine());
                Cval = "circle is: " + (Math.PI * r * 2);
                Aval = "circle is: " + (Math.PI * r * r);
            }

            System.out.println("The circumference of the "+Cval);
            System.out.println("The area of the "+Aval);
        }
    }

    public static void Q2() {
        System.out.println("Q2: Enter the current day (1-31): ");
        int num = Integer.parseInt(scan.nextLine());
        System.out.println("Enter the current month: (1-12)");
        int num2 = Integer.parseInt(scan.nextLine());
        String months[] = {"January","February","March","April","May","June","July","August","September","October","November","December"};

        if (num >= 1 && num <= 31)
        {
            if(num==1||num==31)
            {
                System.out.print("You selected "+num+"st of ");
            }
            else if(num==2)
            {
                System.out.print("You selected "+num+"nd of ");
            }
            else if(num==3)
            {
                System.out.print("You selected "+num+"rd of ");
            }
            else if(num>=4&&num<=30)
            {
                System.out.print("You selected "+num+"th of ");
            }
        }
        else
        {
            System.out.println("Invalid day");
        }

        if((num2 >= 1) && (num2 <= 12)) {
            System.out.println(months[num2-1]);
        } else {
            System.out.println("Invalid month");
        }
    }

public static void Q3() {
    System.out.println("Q3: Enter how many numbers you want to check for primality: ");
    int n = Integer.parseInt(scan.nextLine());

    int counter = 0;

    for (int i = 0; i < n; i++) 
    {
        if (i < 2) 
        continue;

        boolean check = true;

        for (int j = 2; j * j <= i; j++) 
        {
            

            if (i % j == 0) 
            {
                check = false;
                break;
            } 
            
        }

        if (check == true) 
            {
                counter++;
            } 
    }

        System.out.println("There are: " + counter + " primes between 0 and " + n);
    }

    public static void Q4() {
        Random rng = new Random();

        String playerInput;
        System.out.println("Q4: Let's play a game. Type \"A\" to attack, \"B\" to buff your next attack. Kill the enemy to win!");
        System.out.println("Q4: You must roll higher than the enemy armor class (12) to hit. Roll 20 for a critical hit!");
        System.out.println("Q4: Your damage is 2-16 (2d8)");

        int enemyHP = 100;
        int turns = 0;
        int damage = 0;
        int weapon = 0;
        int buffValue = 5;
        int critValue = 19;

        boolean isBuff = false;
        while (true) {

            boolean doAttack = false;
            boolean doBuff = false;
            while (!doBuff) {
                playerInput = scan.nextLine();
                doBuff = true;
                switch (playerInput) {
                    case "A", "a":
                        doAttack = true;
                        break;
                    case "B", "b":
                        isBuff = true;
                        System.out.println("Buffing! +5 to your next attack roll and damage");
                        break;
                    default:
                        System.out.println("Invalid input");
                        doBuff = false;
                }
            }

            if (doAttack) {
                turns++;
                int attackRoll = rng.nextInt(20) + 1;
                System.out.print("You rolled: " + attackRoll);
                if(isBuff) {
                    attackRoll += buffValue;
                    System.out.print(" + "+buffValue+" (buff active)\n");
                } else {
                    System.out.println();
                }
                if (attackRoll >= 12) {
                    damage = rng.nextInt(8) + 1;
                    damage += rng.nextInt(8) + 1;
                    if(isBuff) {
                        damage += buffValue;
                    }
                    if (attackRoll >= critValue + weapon || (isBuff && attackRoll >= critValue + buffValue + weapon)) {
                        damage *= 2;
                        System.out.print("Critical hit! ");
                    }
                    System.out.print("You dealt " + damage + weapon + " damage");
                    if(isBuff) {
                        System.out.print(" (buffed attack)");
                    }
                    enemyHP -= damage;
                    System.out.println("\nEnemy HP: " + Math.max(0, enemyHP));

                } else {
                    System.out.println("Miss");
                }

                isBuff = false;
                if (enemyHP <= 0) {
                    System.out.println("Enemy died in " + turns + " turns");
                    scan.close();
                    return;
                }
            }

        }
    }
}
