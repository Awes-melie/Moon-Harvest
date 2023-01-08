public class Output {

    public static void instructions (){
        Output.lineBreak();
        System.out.println("GOAL");
        System.out.println("Your goal is to grow and harvest as many plants as you can.");
        System.out.println("Each plant needs daily watering or it will start dying.");
        System.out.println("All the plants start germinating and can't die.");
        System.out.println("They will remain so until you water them for the first time.");
        System.out.println("You have a certain amount of actions per day determined by your energy level.");
        System.out.println("You have 27 days until the full moon rises again.");
        Output.lineBreak();
        System.out.println("CONTROLS");
        System.out.println("Plants can be reffered to by name or by number, multiple can also be listed if space-seperated.");
        System.out.println("Some actions can be repeated a number of times.");
        System.out.println("water|w (plants): Sets the plant to watered if you have any water left.");
        System.out.println("fertilise|f (plants): Sets the plant to fertilised if you have any fertiliser left.");
        System.out.println("harvest|h (plants): Harvests the plant if it is blooming.");
        System.out.println("gather|g (amount): If it is raining or snowing, collect 8-12 rainwater per action.");
        System.out.println("tap|t (amount): Collect 3-5 tapwater per action.");
        System.out.println("order|o: Order a batch of 10 fertiliser, it might take a couple days to arrive.");
        System.out.println("pass|p (amount): Skip any number of turns.");
        System.out.println("instructions|i: See this page again.");
        System.out.println("exit|e: Quit the game.");
    }
    public static void actions(int energy){
        System.out.println("You have " + energy + " actions left today.");
    }

    public static void resources(int water, int fertiliser){
        System.out.println("You have " + water + " water and " + fertiliser + " fertilsier.");
    }

    public static void inputError(){
        System.out.println("Input error.");
    }
    public static void intro(){
        Output.instructions();
    }
    public static void outro(int plants){
        
    }
    public static void lineBreak(int energy, int water, int fertiliser){
        System.out.println("-=["+Output.fill(String.valueOf(energy),2)+"]=["+Output.fill(String.valueOf(water),2)+"]=["+Output.fill(String.valueOf(fertiliser),2)+"]=====-");
    }

    public static void lineBreak(){
        System.out.println("-====================-");
    }

    public static void bigBreak(int day, int energy, int water, int fertiliser){
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
        System.out.println("-=["+Output.fill(String.valueOf(energy),2)+"]=["+Output.fill(String.valueOf(water),2)+"]=["+Output.fill(String.valueOf(fertiliser),2)+"]=====- DAY "+ day +" -====================-");
    }

    public static void bigBreak(int day){
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
        System.out.println("-====================- DAY "+ day +" -====================-");
    }

    public static void dailyUpdate(Weather currentWeather, int currentEnergy) {
        System.out.println("Today it is " + currentWeather + " and you feel " + (currentEnergy <= 2 ? "exhausted" : ( currentEnergy <= 4 ? "normal" : "energetic" ) ) + ".");
    }
    public static void notEnoughFertiliser() {
        System.out.println("Sorry, there's not enough fertiliser.");
    }

    public static void notInBloom() {
        System.out.println("This plant is not ready to harvest yet.");
    }

    public static String fill(String string, int length){
        if (string.length() == length){
            return string;
        } else if (string.length() < length){
            int diff = length-string.length();
            for (int i = 0; i < diff; i++) {
                string = string + " ";
            }
        } else {
            string = string.substring(0, length);
        }
        return string;
    }
    public static void purchase(int daysTillFertiliser) {
        if(daysTillFertiliser>0){
            System.out.println("Your order of fertiliser will arrive in " + daysTillFertiliser + " day"+ (daysTillFertiliser == 1 ? "s" : "") +".");
        } else if (daysTillFertiliser == 0 ){
            System.out.println("Your order of fertiliser has arrived!");
        }
    }

    public static int randomInRange(int min, int max){
        return (int) Math.floor((Math.random() * (max-min)) + min);
    }
    
}
