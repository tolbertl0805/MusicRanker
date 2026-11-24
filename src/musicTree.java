import java.util.Scanner;

public class musicTree {
    String[] songBracket;
    int width;

    public musicTree(int length){
        int size=0;
        while (twoToThe(size)<length){
            size++;
        }
        songBracket = new String[twoToThe(size+1)-1];
        width=size;
    }

    public void add(String[] songs){
        String[] ordered = order(songs, width);
        if (twoToThe(width) >= 0) System.arraycopy(ordered, 0, songBracket, twoToThe(width) - 1, twoToThe(width));
    }

    public static String[] order(String[] songs, int width){
        if (width==0){
            return songs;
        }
        int topSize = twoToThe(width-1);
        String[] topHalf = new String[topSize];
        System.arraycopy(songs, 0, topHalf, 0, topSize);
        String[] botHalf = new String[topSize];
        System.arraycopy(songs, topSize, botHalf, 0, songs.length - topSize);
        String[] top = order(topHalf, width-1);
        String[] bot = order(botHalf, width-1);
        String[] combo = new String[topSize*2];
        for (int i = 0; i < topSize; i++) {
            combo[i*2]=top[i];
            combo[i*2+1]=bot[i];
        }
        return combo;
    }

    public String getValue(int index, int level){
        if (songBracket[twoToThe(level)-1+index]==null){
            promote(index, level);
            return songBracket[twoToThe(level)-1+index];
        }
        return songBracket[twoToThe(level)-1+index];
    }

    public void promote(int index, int level){
        int arrayIndex = twoToThe(level)-1+index;
        if (level==width){
            songBracket[arrayIndex]=null;
        }
        else {
            String a = getValue(index*2, level+1);
            String b = getValue(index*2+1, level+1);
            if (b==null){
                songBracket[arrayIndex]=a;
                songBracket[arrayIndex*2+1]=null;
            }
            else if (a==null){
                songBracket[arrayIndex]=b;
                songBracket[arrayIndex*2+2]=null;
            }
            else {
                int choice = compare(a, b);
                if (choice==1){
                    songBracket[arrayIndex]=a;
                }
                else {
                    songBracket[arrayIndex]=b;
                }
                songBracket[arrayIndex*2+choice]=null;
            }
        }
    }

    public int compare(String a, String b){
        String response = "n";
        Scanner scan = new Scanner(System.in);
        while (!(response.equals("1")||response.equals("2"))){
            System.out.println("Which is better: "+a+" or "+b+"? (1 for the former, 2 for the latter)");
            response=scan.nextLine();
        }
        return Integer.parseInt(response);
    }

    public String[] top(int length){
        String[] retArray = new String[length];
        Scanner scan = new Scanner(System.in);
        int i=0;
        while (i < length) {
            retArray[i]=getValue(0,0);
            for (int j = 0; j < songBracket.length; j++) {
                if (songBracket[j]!=null&&retArray[i].equals(songBracket[j])){
                    songBracket[j]=null;
                }
            }
            System.out.println((i+1)+" songs ranked. Continue?");
            i++;
            if (scan.nextLine().charAt(0)=='n'){
                break;
            }
        }
        String[] retArray2 = new String[i];
        System.arraycopy(retArray, 0, retArray2, 0, i);
        return retArray2;
    }

    private static int twoToThe(int power){
        int value=1;
        for (int i = 0; i < power; i++) {
            value*=2;
        }
        return value;
    }
}