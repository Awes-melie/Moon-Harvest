import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Player {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    private int water;
    private int fertiliser;
    private int energy;

    public int getEnergy() {return energy;}
    public void setEnergy(int energy) {this.energy = energy;}
    public int getWater() { return water; }
    public void setWater(int water) { this.water = water; }
    public int getFertiliser() { return fertiliser; }
    public void setFertiliser(int fertiliser) { this.fertiliser = fertiliser; }

    public void playerActions() {
        System.out.println("PLAYERACTIONS");
        while (energy > 0) {
            String command = getInput();
            if (command!=null) {
                if (parseCommand(command)){
                    energy -= 1;
                } else {
                    System.out.println("ERROR");
                }
            } else {
                System.out.println("ERROR");
            }
        }
    }

    public boolean parseCommand(String command) {
        String[] sections = command.split(" ");
        String order = sections[0].toLowerCase();
        if (sections.length > 1){
            String details = sections[1].toLowerCase();

            if (order.equals("water") || order.equals("w")){
                try {
                    int index = Integer.valueOf(details);
                    Plant.waterPlant(index);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
        } else {
            if (order.equals("exit") || order.equals("e")){
                MoonHarvest.endGame();
                return true;
            }
        }
        return false;
    }


    public String getInput () {
        try {
            String command = input.readLine();
            return command;
        } catch (Exception e){
            return null;
        }
    }
}
