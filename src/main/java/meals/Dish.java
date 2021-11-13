package meals;

public class Dish implements Ingredient {
        private float price;
        private String Ig_name;
        private String meal_name;

        public Dish(String meal_name, String Ig_name, float price){
                this.meal_name = meal_name;
                this.Ig_name = Ig_name;
                this.price = price;
        }

        @Override
        public float getMealPrice() {
                return this.price;
        }

        @Override
        public String getMealName(){
                return this.meal_name;
        }

        public String getIgName(){
                return this.Ig_name;
        }
}
