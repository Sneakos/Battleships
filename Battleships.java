
/**
 * Write a description of class Battleships here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Battleships
{
       static Scan scan = new Scan();
       static int count1 = 0;
       static int count2 = 0;
       static int[][] player1 = new int[10][10];
       static int[][] player2 = new int[10][10];
    public static void main(String[] args)
    {
        boolean done = false;
        String ans = "";
        show(player1);
        pos1(5, "carrier");
        pos1(4, "battleship");
        pos1(3, "submarine");
        pos1(3, "destroyer");
        pos1(2, "tugboat");
        System.out.println("Press enter to continue");
        ans = scan.scan();
        clear();
        show(player2);
        pos2(5, "carrier");
        pos2(4, "battleship");
        pos2(3, "submarine");
        pos2(3, "destroyer");
        pos2(2, "tugboat");
        System.out.println("Press enter to continue");
        ans = scan.scan();
        clear();
        
        while(!done)
        {
        System.out.println("Player 1's turn...");
        ans = scan.scan();
        clear();
        System.out.println("State of the board");
        show2(player2);
        attack(2);
        System.out.println("State of the board:");
        show2(player2);
        if(count2 >= 17)
        {
            clear();
            System.out.println("YOU WIN!!!!!!");
            break;
        }
        System.out.println("Press enter to continue");
        ans = scan.scan();
        clear();
        System.out.println("Player 2's turn...");
        ans = scan.scan();
        clear();
        System.out.println("State of the board");
        show2(player1);
        attack(1);
        System.out.println("State of the board");
        show2(player1);
        if(count1 >= 17)
        {
            clear();
            System.out.println("YOU WIN!!!!!!");
            break;
        }
        System.out.println("Press enter to continue");
        ans = scan.scan();
        clear();
        }
    }
    
    public static void attack(int player)
    {
        int num1 = 0;
        int num2 = 0;
        int attack = 0;
        String ans = "";
        String[] parts;
        boolean done = false;
        while(!done)
            {
                done = true;
                System.out.println("Enter coordiantes to attack (ex. a1)");
            ans = scan.scan();
            parts = ans.split("");
            num1 = swap(parts[0]);
            if(parts.length > 2)
                num2 = 9;
            else {
                num2 = Integer.parseInt(parts[1]);
                num2 -= 1;
            }
            if(parts.length > 3 || num1 == -1)
            {
                System.out.println("Invalid input");
                done = false;
            }
            attack = checkAttack(num1, num2, player);
            if(attack == 2)
            {
                System.out.println("You have already attacked there.");
                done = false;
            }
            }
        if(attack == 1)
        {
            System.out.println("Hit!");
        } else {
            System.out.println("Miss!");
        }
   }
    
    public static int checkAttack(int num1, int num2, int player)
    {
        if(player == 1)
        {
            if(player1[num1][num2] == 1)
            {
                player1[num1][num2] = 2;
                count1++;
                return 1;
            }
            else if(player1[num1][num2] >= 2)
                return 2;
            else {
                player1[num1][num2] = 3;
                return 3;
            }
        } else {
            if(player2[num1][num2] == 1)
            {
                player2[num1][num2] = 2;
                count2++;
                return 1;
            }
            else if(player2[num1][num2] >= 2)
                return 2;
            else {
                player2[num1][num2] = 3;
                return 3;
            }
        }
    }
    
    public static void pos1(int length, String ship)
    {
        String answer;
        boolean done = false;
        boolean temp = false;
        int num1 = 0;
        int num2 = 0;
        String[] parts;
        while(!done)
        {
            while(!temp)
            {
                temp = true;
            System.out.println("Choose a starting position for your " + ship + " (ex: a1)");
            answer = scan.scan();
            parts = answer.split("");
            num1 = swap(parts[0]);
            if(parts.length > 2)
            {
                num2 = 9;
            } else if(parts.length > 1){
                num2 = Integer.parseInt(parts[1]);
                num2 -= 1;
            }
            if(parts.length > 3 || num1 == -1 || parts.length <= 1)
            {
                System.out.println("Invalid input");
                temp = false;
            }
            }
            temp = false;    
            
            System.out.println("Up down left or right? (u d l r)");
            answer = scan.scan();
            switch(answer)
            {
            case "u" : 
            if(num1 < (length - 1) )
            {
                System.out.println("Invalid direction");
            } else if(check(player1, num1, num2, length, 1)){
                for(int i = 1; i < length; i++)
                {
                    player1[num1 - i][num2] = 1;
                }
                done = true;
            } else 
                System.out.println("Invalid direction.");
            break;
            
            case "d" : 
            if(num1 > (10 - length))
            {
                System.out.println("Invalid direction");
            } else if(check(player1, num1, num2, length, 2)){
                for(int i = 1; i < length; i++)
                {
                    player1[num1 + i][num2] = 1;
                }
                done = true;
            } else 
                System.out.println("Invalid direction.");
            break;
            
            case "l" : 
            if(num2 < (length - 1))
            {
                System.out.println("Invalid direction");
            } else if(check(player1, num1, num2, length, 3)){
                for(int i = 1; i < length; i++)
                {
                    player1[num1][num2 - i] = 1;
                }
                done = true;
            } else 
                System.out.println("Invalid direction.");
            break;
            
            case "r" : 
            if(num2 > (10 - length))
            {
                System.out.println("Invalid direction");
            } else if(check(player1, num1, num2, length, 4)){
                for(int i = 1; i < length; i++)
                {
                    player1[num1][num2 + i] = 1;
                }
                done = true;
            } else 
                System.out.println("Invalid direction.");
            break;
            }
        }
        
        player1[num1][num2] = 1;
        show(player1);
    }
    
    public static void pos2(int length, String ship)
    {
        String answer;
        boolean done = false;
        int num1 = 0;
        int num2 = 0;
        String[] parts;
        while(!done)
        {
            System.out.println("Choose a starting position for your " + ship + " (ex: a1)");
            answer = scan.scan();
            parts = answer.split("");
            num1 = swap(parts[0]);
            if(parts.length > 2)
            {
                num2 = 9;
            } else {
                num2 = Integer.parseInt(parts[1]);
                num2 -= 1;
            }
            if(parts.length > 3 || num1 == -1)
            {
                System.out.println("Invalid input");
            }
                
            
            System.out.println("Up down left or right? (u d l r)");
            answer = scan.scan();
            switch(answer)
            {
            case "u" : 
            if(num1 < (length - 1) )
            {
                System.out.println("Invalid direction");
            } else if(check(player2, num1, num2, length, 1)){
                for(int i = 1; i < length; i++)
                {
                    player2[num1 - i][num2] = 1;
                }
                done = true;
            } else 
                System.out.println("Invalid direction.");
            break;
            
            case "d" : 
            if(num1 > (length + 1))
            {
                System.out.println("Invalid direction");
            } else if(check(player2, num1, num2, length, 2)){
                for(int i = 1; i < length; i++)
                {
                    player2[num1 + i][num2] = 1;
                }
                done = true;
            } else 
                System.out.println("Invalid direction.");
            break;
            
            case "l" : 
            if(num2 < (length - 1))
            {
                System.out.println("Invalid direction");
            } else if(check(player2, num1, num2, length, 3)){
                for(int i = 1; i < length; i++)
                {
                    player2[num1][num2 - i] = 1;
                }
                done = true;
            } else 
                System.out.println("Invalid direction.");
            break;
            
            case "r" : 
            if(num2 > (10 - length))
            {
                System.out.println("Invalid direction");
            } else if(check(player2, num1, num2, length, 4)){
                for(int i = 1; i < length; i++)
                {
                    player2[num1][num2 + i] = 1;
                }
                done = true;
            } else 
                System.out.println("Invalid direction.");
            break;
            }
        }
        
        player2[num1][num2] = 1;
        show(player2);
    }
    
    
    public static void show(int[][] field)
    {
        System.out.println("    1     2     3     4     5     6     7     8     9    10");
        for(int i = 0; i < 10; i++)
        {
            switch(i)
            {
                case 0 : System.out.print("a"); break;
                case 1 : System.out.print("b"); break;
                case 2 : System.out.print("c"); break;
                case 3 : System.out.print("d"); break;
                case 4 : System.out.print("e"); break;
                case 5 : System.out.print("f"); break;
                case 6 : System.out.print("g"); break;
                case 7 : System.out.print("h"); break;
                case 8 : System.out.print("i"); break;
                case 9 : System.out.print("j"); break;
            }
            for(int j = 0; j < 10; j++)
            {
                if(field[i][j] == 0 || field[i][j] == 3)
                    System.out.print("[    ]");
                    else if(field[i][j] == 1)
                        System.out.print("[ {} ]");
                        else if(field[i][j] == 2)
                            System.out.print("[ XX ]");
            }
            System.out.println();
        }
    }
    
    public static void show2(int[][] field)
    {
        System.out.println("    1     2     3     4     5     6     7     8     9    10");
        for(int i = 0; i < 10; i++)
        {
            switch(i)
            {
                case 0 : System.out.print("a"); break;
                case 1 : System.out.print("b"); break;
                case 2 : System.out.print("c"); break;
                case 3 : System.out.print("d"); break;
                case 4 : System.out.print("e"); break;
                case 5 : System.out.print("f"); break;
                case 6 : System.out.print("g"); break;
                case 7 : System.out.print("h"); break;
                case 8 : System.out.print("i"); break;
                case 9 : System.out.print("j"); break;
            }
            for(int j = 0; j < 10; j++)
            {
                if(field[i][j] == 3)
                    System.out.print("[ oo ]");
                    else if(field[i][j] == 2)
                    {
                        System.out.print("[ XX ]");
                    }
                    else {
                        System.out.print("[    ]");
                    }
            }
            System.out.println();
        }
    }
    
    public static int swap(String letter)
    {
        int num;
        
        switch(letter)
        {
            case "a" : return 0;
            case "b" : return 1;
            case "c" : return 2;
            case "d" : return 3;
            case "e" : return 4;
            case "f" : return 5;
            case "g" : return 6;
            case "h" : return 7;
            case "i" : return 8;
            case "j" : return 9;
        }
        return -1;
    }
    
    public static boolean check(int[][] player, int num1, int num2, int length, int dir)
    {
        if(player[num1][num2] == 1)
            return false;
        
        switch(dir)
        {
            case 1:
            for(int i = 1; i < length; i++)
                {
                    if(player[num1 - i][num2] == 1)
                        return false;
                }
                break;
            case 2:
            for(int i = 1; i < length; i++)
                {
                    if(player[num1 + i][num2] == 1)
                        return false;
                }
                break;
            case 3:
            for(int i = 1; i < length; i++)
                {
                    if(player[num1][num2 - i] == 1)
                        return false;
                }
                break;
            case 4:
            for(int i = 1; i < length; i++)
                {
                    if(player[num1][num2 + i] == 1)
                        return false;
                }
                break;
        }
        
        return true;
    }
    
    public static void clear()
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}