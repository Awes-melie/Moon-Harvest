public class MoonHarvest {

    private static final int MAX_ENERGY = 6;
    private static final int MIN_ENERGY = 2;

    private static boolean gameRunning;
    private static Weather currentWeather;
    
    public static Weather getCurrentWeather() {
        return currentWeather;
    }

    private static int currentEnergy;

    public static int getCurrentEnergy() {
        return currentEnergy;
    }

    private static int date = 1;

    public static int getDate() {
        return date;
    }

    private static int FINAL_DATE = 27;

    public static void endGame(){
        gameRunning = false;
    }

    public static void main(String[] args) {

        Player player = new Player();
        gameRunning = true;

        entry();
        Player.getInput();

        Plant.initPlants();

        while (gameRunning){

            //Update status
            dailyUpdate();
            
            //Plants talk
            Output.bigBreak(date);
            if (date == FINAL_DATE){
                System.out.println("FINAL DAY");
            }
            Plant.morningPlantsTalk();
            Player.getInput();

            //Take player actions
            Output.lineBreak(currentEnergy, player.getWater(), player.getFertiliser());
            player.setEnergy(currentEnergy);
            player.playerActions();
            
            Plant.listPlants();
            
            //Plants talk again

            Output.lineBreak();
            Plant.eveningPlantsTalk();
            Player.getInput();

            //Update plants
            Plant.weatherEffects(currentWeather);
            Plant.plantUpdate();

            player.reduceFertiliserTimer();
            date ++;

            if (Plant.gameOver() || date > FINAL_DATE){
                gameRunning = false;
            }
        }

        Output.lineBreak();
        exit();
    }

    public static void entry(){
        Output.intro();
    }

    public static void dailyUpdate(){
        Weather[] values = Weather.values();
        int random = (int) Math.floor(Math.random()*values.length);
        currentWeather = values[random];
        currentEnergy = Output.randomInRange(MIN_ENERGY, MAX_ENERGY);
    }

    public static void exit(){
        Output.outro(Plant.harvestedPlants());
    }
}