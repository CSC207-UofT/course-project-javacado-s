package meals;

/**
 This interface represents the 'lowest common denominator'
 in which all classes in the meal package shares.
 */

public interface Ingredient {
    float getMealPrice();
    String getMealName();
}
