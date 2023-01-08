import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Player {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    private int water = 5;
    private int fertiliser = 5;
    private int energy;
    private int daysTillFertiliser = -1;
    
    public void reduceFertiliserTimer() {
        this.daysTillFertiliser --;
    }
    public int getEnergy() {return energy;}
    public void setEnergy(int energy) {this.energy = energy;}
    public int getWater() { return water; }
    public void setWater(int water) { this.water = water; }
    public int getFertiliser() { return fertiliser; }
    public void setFertiliser(int fertiliser) { this.fertiliser = fertiliser; }

    public void playerActions() {
        if(daysTillFertiliser == 0){
            fertiliser += 10;
        }
        boolean reprint = true;
        while (energy > 0) {
            if(reprint){
                Output.dailyUpdate(MoonHarvest.getCurrentWeather(),MoonHarvest.getCurrentEnergy());
                Plant.listPlants();
                Output.purchase(daysTillFertiliser);
                Output.actions(energy);
                Output.resources(water, fertiliser);
            }
            String command = getInput();
            if (command!=null) {
                String outcome = parseCommand(command);
                if (outcome == null){
                    reprint = true;
                    Output.lineBreak(energy,water,fertiliser);
                } else {
                    reprint = false;
                    System.out.println(outcome);
                }
            } else {
                Output.inputError();
            }

        }
    }

    public String parseCommand(String command) {
        String[] sections = command.split(" ");
        String order = sections[0].toLowerCase();
        if (sections.length > 1){
            for (int i = 1; i < sections.length; i++) {
                if(energy > 0){
                    String details = sections[i].toLowerCase();
                    if (order.equals("water") || order.equals("w")){
                        String output = water(details);
                        if(output != null){
                            System.out.println(output);
                        }
                    } else if (order.equals("fertilise") || order.equals("f")){
                        String output = fertilise(details);
                        if(output != null){
                            System.out.println(output);
                        }
                    } else if (order.equals("harvest") || order.equals("h")){
                        String output = harvest (details);
                        if(output != null){
                            System.out.println(output);
                        }
                    } else if (order.equals("pass") || order.equals("p")){
                        try {
                            int amount = Integer.valueOf(details);
                            energy -= amount;
                        } catch (Exception e) {
                            return "Sorry, that command wasn't recognised.";
                        }
                    } else if (order.equals("gather") || order.equals("g")){
                        if (MoonHarvest.getCurrentWeather() == Weather.RAINY || MoonHarvest.getCurrentWeather() == Weather.SNOWING){  
                            try {
                                int times = Integer.valueOf(details);
                                while (energy > 0 && times > 0){
                                    water += Output.randomInRange(8, 12);
                                    energy --;
                                    times --;
                                }
                            } catch (Exception e) {
                                return "Sorry, that command wasn't recognised.";
                            }
                        } else {
                            return "It isn't raining or snowing currently.";
                        }
                    } else if (order.equals("tap") || order.equals("t")){
                        try {
                            int times = Integer.valueOf(details);
                            while (energy > 0 && times > 0){
                                water += Output.randomInRange(3, 5);
                                energy --;
                                times --;
                            }
                        } catch (Exception e) {
                            return "Sorry, that command wasn't recognised.";
                        }
                    }
                    else {
                        return "Sorry, that command wasn't recognised.";
                    }
                } else {
                    return "Sorry, you don't have enough energy to do that today.";
                }
            }
            return null;
        } else {
            if (order.equals("exit") || order.equals("e")){
                MoonHarvest.endGame();
                energy = 0;
            } else if (order.equals("pass") || order.equals("p")){
                energy --;
            } else if (order.equals("help") || order.equals("h")){
                Output.instructions();
                return "";
            } else if (order.equals("gather") || order.equals("g")){
                if (MoonHarvest.getCurrentWeather() == Weather.RAINY || MoonHarvest.getCurrentWeather() == Weather.SNOWING){  
                    water += Output.randomInRange(8, 12);
                } else {
                    return "It isn't raining or snowing currently.";
                }
                energy --;
            } else if (order.equals("tap") || order.equals("t")){
                water += Output.randomInRange(3, 5);
                energy --;
            } else if (order.equals("order") || order.equals("o")){
                if(daysTillFertiliser < 1){
                    daysTillFertiliser =  Output.randomInRange(2,5);
                    energy --;
                } else {
                    return "You already have fertiliser on the way.";
                }
                
            } else {
                return "Sorry, that command wasn't recognised.";
            }
            return null;
        }
        
    }

    public String water (String details){
        if (water <= 0){
            return "There is not enough water.";
        }
        try {
            int index = Integer.valueOf(details);
            if(!Plant.isWet(index-1)){
                Plant.waterPlant(index-1);
                energy --;
                water --;
                return null;
            } else {
                return "That plant is already watered.";
            }
        } catch (Exception e) {
            int index = Plant.idFromName(details);
            if(index != -1){
                if(!Plant.isWet(index)){
                    Plant.waterPlant(index);
                    energy --;
                    water --;
                    return null;
                } else {
                    return "That plant is already watered.";
                }
            }
            return "Sorry, that command wasn't recognised.";
        }
    }

    public String fertilise (String details){
        if (fertiliser <= 0){
            return "There is not enough fertiliser.";
        }
        try {
            int index = Integer.valueOf(details);
            if(!Plant.isFertilised(index-1)){
                Plant.fertilisePlant(index-1);
                energy --;
                fertiliser --;
                return null;
            } else {
                return "That plant is already fertilised.";
            }
        } catch (Exception e) {
            int index = Plant.idFromName(details);
            if(index != -1){
                if(!Plant.isFertilised(index)){
                    Plant.fertilisePlant(index);
                    energy --;
                    fertiliser --;
                    return null;
                } else {
                    return "That plant is already fertiliseed.";
                }
            }
            return "Sorry, that command wasn't recognised.";
        }
    }

    public String harvest (String details){
        try {
            int index = Integer.valueOf(details);
            if(Plant.isBlooming(index-1)){
                Plant.harvest(index-1);
                energy --;
                return null;
            } else {
                return "That plant is not ready for harvest.";
            }
        } catch (Exception e) {
            int index = Plant.idFromName(details);
            if(index != -1){
                if(Plant.isBlooming(index)){
                    Plant.harvest(index);
                    energy --;
                    return null;
                } else {
                    return "That plant is not ready for harvest.";
                }
            }
            return "Sorry, that command wasn't recognised.";
        }
    }

    public static String getInput () {
        try {
            String command = input.readLine();
            return command;
        } catch (Exception e){
            return null;
        }
    }
}
