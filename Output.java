public class Output {

    public static void instructions (){
        System.out.println("INSTRUCTIONS");
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
    public static void commandUnrecognised(){
        System.out.println("Sorry, that command wasn't recognised.");
    }
    public static void intro(){
        
    }
    public static void outro(int plants){
        
    }
    public static void lineBreak(int energy, int water, int fertiliser){
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
        System.out.println("-=["+Output.fill(String.valueOf(energy),2)+"]=["+Output.fill(String.valueOf(water),2)+"]=["+Output.fill(String.valueOf(fertiliser),2)+"]=====-");
    }

    public static void lineBreak(){
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
        System.out.println("-====================-");
    }

    public static void bigBreak(int day, int energy, int water, int fertiliser){
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
        System.out.println("-=["+Output.fill(String.valueOf(energy),2)+"]=["+Output.fill(String.valueOf(water),2)+"]=["+Output.fill(String.valueOf(fertiliser),2)+"]=====- DAY "+ day +" -====================-");
    }

    public static void dailyUpdate(Weather currentWeather, int currentEnergy) {
        System.out.println("Today it is " + currentWeather + " and you feel " + currentEnergy + ".");
    }
    public static void notEnoughWater() {
        System.out.println("Sorry, there's not enough water.");
    }
    public static void notEnoughFertiliser() {
        System.out.println("Sorry, there's not enough fertiliser.");
    }
    public static void notRaining() {
        System.out.println("It isn't raining currently.");
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
    
}
