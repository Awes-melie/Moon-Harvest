public class MoonHarvest {

    private static boolean gameRunning;
    private static Weather currentWeather;
    private static int currentEnergy;
    private static int date = 0;
    private static int FINAL_DATE = 27;

    public static void endGame(){
        gameRunning = false;
    }

    public static void main(String[] args) {

        Player player = new Player();
        gameRunning = true;

        entry();

        Plant.initPlants();

        Plant.listPlants();

        while (gameRunning){

            //Update Status
            dailyUpdate();

            //Take player actions
            player.setEnergy(currentEnergy);
            player.playerActions();
            Plant.plantUpdate(currentWeather);

            Output.lineBreak();

            Plant.listPlants();

            if (Plant.gameOver() || date == FINAL_DATE){
                gameRunning = false;
            }
        }

        exit();
    }

    public static void entry(){
        Output.intro();
    }

    public static void dailyUpdate(){
        Weather[] values = Weather.values();
        int random = (int) Math.floor(Math.random()*values.length);
        currentWeather = values[random];
        currentEnergy = (int) (Math.floor(Math.random()*5.0) + 1);
        Output.dailyUpdate(currentWeather,currentEnergy);
    }

    public static void exit(){
        Output.outro(Plant.harvestedPlants());
    }
}