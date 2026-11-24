import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Enter file name: ");
        Scanner scan = new Scanner(System.in);
        String songList = scan.nextLine();
        Scanner file = new Scanner(new File(songList));
        System.out.println("How many songs are there?");
        int total = scan.nextInt();
        scan.nextLine();
        String[] songs = new String[total];
        for (int i = 0; i < total; i++) {
            songs[i]=file.nextLine();
        }
        System.out.println(Arrays.toString(songs));
        musicTree testBracket = new musicTree(total);
        testBracket.add(songs);
        System.out.println("How many would you like to rank?");
        int ranking = scan.nextInt();
        scan.nextLine();
        String[] sortedTests = testBracket.top(ranking);
        for (int i = 0; i < sortedTests.length; i++) {
            System.out.println((i+1)+". "+sortedTests[i]);
        }
    }
}