import java.util.ArrayList;

public class Recipe {
    private String name;
    private int cookingTime;
    private ArrayList<String> ingredients;


    public Recipe() {
        this.name = "";
        this.cookingTime = 0;
        this.ingredients = new ArrayList<>();
    }
    

    public String getName() {
        return this.name;
    }

    public int getCookingTime() {
        return this.cookingTime;
    }

    public ArrayList<String> getIngredients() {
        return this.ingredients;
    }

    public void addIngredient(String ingredient) {
        this.ingredients.add(ingredient);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String toString() {
        return this.name + ", cooking time: " + this.cookingTime;
    }

    public boolean searchByCookingTime(int max) {

        if (this.cookingTime <= max) {
            return true; 
        }

        return false;
    }

    public boolean searchByIngredient(String searched) {
        if (this.ingredients.contains(searched)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        Recipe comparado = (Recipe) obj;
        String compared = comparado.getName().toLowerCase();
        int count = 0;
        String nome = this.name.toLowerCase();

        for (int i = 0; i < nome.length(); i++) {
            if (compared.charAt(count) == nome.charAt(i)) {
                count++;
                if (count == compared.length()) {
                    return true;
                }
            }
        }
        return false;
    }
}
