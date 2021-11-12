package meals;

public class Dish implements Ingredient {
        private float price;
        private String name;
        public Dish(String name, float price){
                this.name = name;
                this.price = price;
        }

        @Override
        public float getMealPrice() {
                return this.price;
        }
}
