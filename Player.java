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
        Output.actions(energy);
        while (energy > 0) {
            String command = getInput();
            if (command!=null) {
                if (parseCommand(command)){
                    Plant.listPlants();
                    energy -= 1;
                } else {
                    Output.commandUnrecognised();
                }
            } else {
                Output.inputError();
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
                    Plant.waterPlant(index-1);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
        } else {
            if (order.equals("exit") || order.equals("e")){
                MoonHarvest.endGame();
                energy = 0;
                return true;
            } if (order.equals("pass") || order.equals("p")){
                energy -= 1;
                return true;
            } if (order.equals("help") || order.equals("h")){
                Output.instructions();
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
