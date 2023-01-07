public class Output {

    public static void instructions (){
        System.out.println("INSTRUCTIONS");
    }
    public static void actions(int energy){
        System.out.println("You have " + energy + " actions left today.");
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
    public static void lineBreak(){
        System.out.println("====================");
    }
    public static void dailyUpdate(Weather currentWeather, int currentEnergy) {
        System.out.println("Today it is " + currentWeather + " and you feel " + currentEnergy + ".");
    }
    
}
