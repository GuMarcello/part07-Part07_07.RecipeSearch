import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.naming.directory.SearchControls;

public class UserInterface {
    private Scanner scanner;
    private ArrayList<Recipe> recipes;


    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
        this.recipes = new ArrayList<>();
    }

    public void start() {

        System.out.print("File to read: ");
        String file = scanner.nextLine();

        try (Scanner scanner = new Scanner(Paths.get(file))) {
            int count = 0;
            ArrayList<String> ingredients = new ArrayList<>();
            String name = "";
            int cookingTime = 0;
            Recipe current = new Recipe();
            recipes.add(current);


            while (scanner.hasNextLine()) {

                if (count == 0) {
                    name = scanner.nextLine();
                    current.setName(name);
                    count++;
                } else if (count == 1) {
                    cookingTime = Integer.valueOf(scanner.nextLine());
                    current.setCookingTime(cookingTime);
                    count++;
                } else if (count > 1) {
                    String ingredient = scanner.nextLine();
                    if (ingredient.isEmpty()) {
                        current = new Recipe();
                        recipes.add(current);
                        count = 0;
                        continue;
                    } else {
                        current.addIngredient(ingredient);
                        count++;
                    }

                }
                
            }

        } catch (Exception e) {
            System.out.println("Couldn't read file: " + e);
        }

        while (true) {
            System.out.println();
            System.out.println("Commands: ");
            System.out.println("list - lists the recipes");
            System.out.println("stop - stops the program");
            System.out.println("find name - searches recipes by name");
            System.out.println("find cooking time - searches recipes by cooking time");
            System.out.println("find ingredient - searches recipes by ingredient");
            System.out.println();
            System.out.print("Enter command: ");

            String command = scanner.nextLine();

            if (command.equals("list")) {
                list();
            } else if (command.equals("stop")) {
                break;
            } else if (command.equals("find name")) {
                System.out.print("Searched word: ");
                String search = scanner.nextLine();
                findName(search);
            } else if (command.equals("find cooking time")) {
                System.out.print("Max cooking time: ");
                int maxTime = (Integer.valueOf(scanner.nextLine()));
                searchByTime(maxTime);
            } else if (command.equals("find ingredient")) {
                System.out.print("Ingredient: ");
                String ingredient = scanner.nextLine();
                searchIngredient(ingredient);
            }

        }

    }

    public void searchIngredient(String ingredient) {
        ArrayList<Recipe> found = new ArrayList<>();

        for (Recipe i: this.recipes) {
            if (i.searchByIngredient(ingredient)) {
                found.add(i);
            }
        }

        System.out.println();
        System.out.println("Recipes: ");
        for (Recipe i: found) {
            System.out.println(i);
        }

    }
    
    public void list() {
        System.out.println();
        for (Recipe i: this.recipes) {
            System.out.println(i);
        }
        System.out.println();
    }

    public void findName(String search) {
        Recipe novo = new Recipe();
        novo.setName(search);
        ArrayList<Recipe> found = new ArrayList<>();

        for (Recipe receita: this.recipes) {
            if (receita.equals(novo)) {
                found.add(receita);
            }
        }

        System.out.println();
        System.out.println("Recipes: ");
        for (Recipe i: found) {
            System.out.println(i);
        }
        
    }

    public void searchByTime(int maxTime) {
        ArrayList<Recipe> found = new ArrayList<>();

        for (Recipe i: this.recipes) {
            if (i.searchByCookingTime(maxTime)) {
                found.add(i);
            }
        }

        System.out.println();
        System.out.println("Recipes: ");
        for (Recipe i: found) {
            System.out.println(i);
        }

    }

}
