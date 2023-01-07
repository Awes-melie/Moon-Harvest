import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Player {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    private int water = 5;
    private int fertiliser = 5;
    private int energy;

    public int getEnergy() {return energy;}
    public void setEnergy(int energy) {this.energy = energy;}
    public int getWater() { return water; }
    public void setWater(int water) { this.water = water; }
    public int getFertiliser() { return fertiliser; }
    public void setFertiliser(int fertiliser) { this.fertiliser = fertiliser; }

    public void playerActions() {        
        while (energy > 0) {
            Output.dailyUpdate(MoonHarvest.getCurrentWeather(),MoonHarvest.getCurrentEnergy());
            Plant.listPlants();
            Output.actions(energy);
            Output.resources(water, fertiliser);
            String command = getInput();
            if (command!=null) {
                if (!parseCommand(command)){
                    Output.commandUnrecognised();
                }
            } else {
                Output.inputError();
            }
            Output.lineBreak(energy,water,fertiliser);
        }
    }

    public boolean parseCommand(String command) {
        String[] sections = command.split(" ");
        String order = sections[0].toLowerCase();
        if (sections.length > 1){

            String details = sections[1].toLowerCase();

            if (order.equals("water") || order.equals("w")){
                if (water <= 0){
                    Output.notEnoughWater();
                    return true;
                }
                try {
                    int index = Integer.valueOf(details);
                    Plant.waterPlant(index-1);
                    energy --;
                    water --;
                    return true;
                } catch (Exception e) {
                    return false;
                }
            } else if (order.equals("fertilise") || order.equals("f")){
                if (fertiliser <= 0){
                    Output.notEnoughFertiliser();
                    return true;
                }
                try {
                    int index = Integer.valueOf(details);
                    Plant.fertilisePlant(index-1);
                    energy --;
                    fertiliser --;
                    return true;
                } catch (Exception e) {
                    return false;
                }
            } else if (order.equals("harvest") || order.equals("h")){
                
                try {
                    int index = Integer.valueOf(details);
                    if(Plant.isBlooming(index-1)){
                        Plant.harvest(index-1);
                        energy --;
                    } else {
                        Output.notInBloom();
                    }
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
                energy --;
                return true;
            } if (order.equals("help") || order.equals("h")){
                Output.instructions();
                return true;
            } if (order.equals("help") || order.equals("h")){
                Output.instructions();
                return true;
            } if (order.equals("resources") || order.equals("r")){
                Output.resources(water, fertiliser);
                return true;
            } if (order.equals("gather") || order.equals("g")){
                if (MoonHarvest.getCurrentWeather() == Weather.RAINY){  
                    water += (int) (Math.floor(Math.random()*3.0) + 3);
                } else {
                    Output.notRaining();
                    return true;
                }
                energy --;
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
