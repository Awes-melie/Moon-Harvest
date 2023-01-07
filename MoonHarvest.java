
public class MoonHarvest {

    private static boolean gameRunning;
    
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
            player.setEnergy(1);
            player.playerActions();
            Plant.plantUpdate();
            Plant.listPlants();
        }

        exit();
    }

    public static void entry(){
        System.out.println("ENTRY");
    }

    public static void exit(){
        System.out.println("EXIT");
    }
}