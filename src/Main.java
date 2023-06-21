import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("movies.txt");
            Scanner scannerFile = new Scanner(file);
            Scanner scannerUser = new Scanner(System.in).useDelimiter("\n");
            ArrayList<String> listFilms = new ArrayList<>();

            while(scannerFile.hasNextLine()) {
                String filmName = scannerFile.nextLine();
                listFilms.add(filmName);
            }
            int randomFilmIndex = getRandomInt(0, listFilms.size());
            String filmRandom = listFilms.get(randomFilmIndex);
            String filmUserGuess = "_".repeat(filmRandom.length());

            System.out.print("You are guessing:");
            System.out.println(filmUserGuess);
            System.out.println("You have guessed (0) wrong letters:");

            int charCountGuessWrong = 0;
            ArrayList<Character> temp = new ArrayList<>(50) ;

            char[] arrayCharFilm = filmUserGuess.toCharArray();

            while(!filmRandom.equals(filmUserGuess)) {
                System.out.print("Guess a letter:");
                char characterFilmGuess = scannerUser.next().charAt(0);


                int indexContainChar = filmRandom.indexOf(characterFilmGuess);
                if (indexContainChar != -1) {
                    while (indexContainChar >= 0) {
                        arrayCharFilm[indexContainChar] = characterFilmGuess;
                        indexContainChar = filmRandom.indexOf(characterFilmGuess, indexContainChar + 1);
                    }

                } else {
                    temp.add(characterFilmGuess);
                    charCountGuessWrong++;
                }
                filmUserGuess = String.valueOf(arrayCharFilm);
                System.out.print("You are guessing:");
                System.out.println(filmUserGuess);
                System.out.printf("You have guessed (%d)  wrong letters:", charCountGuessWrong);
                System.out.println(temp.toString());
                System.out.println();
            }

            System.out.println("You win!");
            System.out.printf("You have guessed '%s' correctly", filmUserGuess);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int getRandomInt(int min, int max) {
        return min + (int)(Math.random() * (max - min + 1) );
    }
}