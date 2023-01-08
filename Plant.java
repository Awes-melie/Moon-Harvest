import java.io.File;
import java.util.Scanner;

public class Plant {

    private static final int MAX_PLANT_NAME_LENGTH = 8;
    private static final double TALK_PROBABILITY = 0.5;
    private static final double SUN_GROWTH_RATE = 0.7;
    private static final int PLANT_NO = 10;
    private static Plant[] allPlants = new Plant[PLANT_NO];
    
    private static final int LINE_TYPES = 13;
    private static final int MAX_LINES = 20;

    private String[][] lines;
    private boolean wet;
    private boolean fertilised;
    private int growthLevel;
    private GrowthState state;
    private String name;
    private boolean justGrown;

    public Plant(boolean wet, boolean fertilised, int growthLevel, GrowthState state, String name) {
        this.wet = wet;
        this.fertilised = fertilised;
        this.growthLevel = growthLevel;
        this.state = state;
        this.name = name;
        this.justGrown = false;
    }

    public boolean isFertilised() {return fertilised;}
    public void setFertilised(boolean fertilised) {this.fertilised = fertilised;}
    public GrowthState getState() {return state;}
    public void setState(GrowthState state) {this.state = state;}
    public int getGrowthLevel() {return growthLevel;}
    public void setGrowthLevel(int growthLevel) {this.growthLevel = growthLevel;}
    public boolean isWet() {return wet;}
    public void setWet(boolean wet) {this.wet = wet;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public boolean isJustGrown() {return justGrown;}
    public void setJustGrown(boolean justGrown) {this.justGrown = justGrown;}

    public static int harvestedPlants(){
        int sum = 0;
        for (Plant p : allPlants) {
            if (p.getState() == GrowthState.HARVESTED){
                sum++;
            }
        }
        return sum;
    }

    public static boolean isBlooming (int index){
        return (allPlants[index].getState() == GrowthState.BLOOMING);
    }

    public static boolean isWet (int index){
        return (allPlants[index].isWet());
    }

    public static boolean isFertilised (int index){
        return (allPlants[index].isFertilised());
    }

    public static void harvest (int index){
        allPlants[index].setState(GrowthState.HARVESTED);
    }

    public static boolean gameOver(){
        for (Plant p : allPlants) {
            if (!(p.getState() == GrowthState.HARVESTED || p.getState() == GrowthState.DEAD)){
                return false;
            }
        }
        return true;
    }

    public static void initPlants(){
        for (int i = 0; i < PLANT_NO; i++) {

            String name;
            switch (i){
                case 0 -> name = "Monstera";
                case 1 -> name = "Cactus";
                case 2 -> name = "Orchid";
                case 3 -> name = "Bonsai";
                case 4 -> name = "Lilly";
                case 5 -> name = "Ivy";
                case 6 -> name = "Shamrock";
                case 7 -> name = "Aloe";
                case 8 -> name = "Jade";
                case 9 -> name = "Violet";
                default -> name = "Bob";
            }

            allPlants[i] = new Plant(false, false, 0, GrowthState.GERMINATING, name);
            allPlants[i].lines= new String[LINE_TYPES][]; 
            for (int j = 0; j < LINE_TYPES; j++) {
                try{
                    File file = new File("plant-lines/plant" + String.valueOf(i+1) + "/" + String.valueOf(j) + ".txt");
                    Scanner reader = new Scanner(file);
                    String[] temp = new String[MAX_LINES];
                    int totalLines = 0;
                    while(reader.hasNextLine()){
                        temp[totalLines] = reader.nextLine();
                        totalLines++;
                    }
                    allPlants[i].lines[j] = new String[totalLines];
                    for (int k = 0; k < temp.length; k++) {
                        allPlants[i].lines[j][k] = temp[k];
                    }
                    reader.close();
                    if(totalLines == 0){
                        System.err.println("NO LINES IN PLANT " + i);
                    }
                } catch (Exception e){
                    System.out.println("Error loading dialogue files.");
                }
            }
        }
    }

    public static int idFromName (String name){
        return switch (name.toLowerCase()){
            case "monstera" -> 0;
            case "cactus" -> 1;
            case "orchid" -> 2;
            case "bonsai" -> 3;
            case "lilly" -> 4;
            case "ivy" -> 5;
            case "shamrock" -> 6;
            case "aloe" -> 7;
            case "jade" -> 8;
            case "violet" -> 9;
            default -> -1;
        };
    }

    public static void printDialogue(int plant, int dialogueType, int line){
        System.out.println(Output.fill(allPlants[plant].getName(),MAX_PLANT_NAME_LENGTH) + ": " + allPlants[plant].lines[dialogueType][line]);
    }

    public static void printDialogue(int plant, int dialogueType){
        int line = (int) Math.floor(Math.random()*allPlants[plant].lines[dialogueType].length);
        System.out.println(Output.fill(allPlants[plant].getName(),MAX_PLANT_NAME_LENGTH) + ": " + allPlants[plant].lines[dialogueType][line]);
    }

    public static void morningPlantsTalk(){
        boolean noPlantsTalk = true;
        int i = 0;
        System.out.println("It is the morning");
        for (Plant p : allPlants) {
            if(!(p.getState() == GrowthState.GERMINATING || p.getState() == GrowthState.HARVESTED || p.getState() == GrowthState.DEAD)){
                if(p.getState() == GrowthState.BLOOMING){
                    printDialogue(i, 1);
                    noPlantsTalk = false;
                    i++;
                    continue;
                }
                if(Math.random() > TALK_PROBABILITY){
                    switch(p.getState()){
                        case FLOURISHING -> printDialogue(i, 2);
                        case GROWING -> printDialogue(i, 3);
                        case WILTING -> printDialogue(i, 5);
                        case DYING -> printDialogue(i, 6);
                        default -> printDialogue(i, 4);
                    }
                    noPlantsTalk = false;
                }
            }
            i++;
        }
        if (noPlantsTalk){
            System.out.println("You hear a low rumbling coming from each of your plant pots...");
        } 
    }

    public static void eveningPlantsTalk(){
        System.out.println("It is the evening");
        boolean noPlantsTalk = true;
        int i = 0;
        for (Plant p : allPlants) {
            if(p.isJustGrown()){
                p.setJustGrown(false);
                printDialogue(i, 0);
                noPlantsTalk = false;
                i++;
                continue;
            } else if (!(p.getState() == GrowthState.GERMINATING || p.getState() == GrowthState.HARVESTED || p.getState() == GrowthState.DEAD)){
                if(p.getGrowthLevel()>=10){
                    printDialogue(i, 8);
                    noPlantsTalk = false;
                    i++;
                    continue;
                } else if (p.getGrowthLevel()<=4) {
                    printDialogue(i, 7);
                    noPlantsTalk = false;
                    i++;
                    continue;
                } else if(Math.random() > TALK_PROBABILITY){
                    int status = (p.isWet() ? 1 : 0) + (p.isFertilised() ? 2 : 0);
                    switch(status){
                        case 0 -> printDialogue(i, 11);
                        case 1 -> printDialogue(i, 9);
                        case 2 -> printDialogue(i, 12);
                        case 3 -> printDialogue(i, 10);
                    }
                    noPlantsTalk = false;
                }
            }
            i++;
        }
        if (noPlantsTalk){
            System.out.println("You hear a low rumbling coming from each of your plant pots...");
        } 
    }

    public static void waterPlant (int index) {
        allPlants[index].setWet(true);
        if (allPlants[index].getState() == GrowthState.GERMINATING){
            allPlants[index].setJustGrown(true);
        }
    }
    
    public static void fertilisePlant(int index) {
        allPlants[index].setFertilised(true);
    }

    public static void weatherEffects(Weather weather){
        switch (weather){
            case CLOUDY:
                break;
            case RAINY:
                break;
            case SNOWING:
                editRandom(-1);
                break;
            case SUNNY:
                editRandom(1);
                break;
            default:
                break;
        }
    }

    public static void editRandom(int rate){
        for (Plant p : allPlants){
            if(p.getState() == GrowthState.FLOURISHING || p.getState() == GrowthState.GROWING || p.getState() == GrowthState.DORMANT){
                if(Math.random() > SUN_GROWTH_RATE){
                    p.setGrowthLevel(p.getGrowthLevel()+rate);
                }
            }
        }
    }

    public static void plantUpdate(){
        for (Plant p : allPlants){
            
            GrowthState currentState = p.getState();

            //Update state based on conditions

            if(currentState == GrowthState.DEAD || currentState == GrowthState.HARVESTED){
                continue;
            }
            if(p.isWet()){
                if(currentState == GrowthState.GROWING && p.isFertilised()){
                    p.setState(GrowthState.FLOURISHING);
                } else if(currentState == GrowthState.DYING || currentState == GrowthState.WILTING || currentState == GrowthState.DORMANT) {
                    p.setState(GrowthState.GROWING);
                } else if (currentState == GrowthState.GERMINATING){
                    p.setGrowthLevel(5);
                    p.setState(GrowthState.DORMANT);
                }
            } else {
                switch (currentState){
                    case WILTING -> p.setState(GrowthState.DYING);
                    case DORMANT -> p.setState(GrowthState.WILTING);
                    case GROWING -> p.setState(GrowthState.DORMANT);
                    case FLOURISHING -> p.setState(GrowthState.GROWING);
                    case BLOOMING -> p.setState(GrowthState.DYING);
                    default -> {}
                }
            }

            // Update growth based on state
            
            switch (currentState){
                case GROWING -> p.setGrowthLevel(p.getGrowthLevel()+1);
                case FLOURISHING -> p.setGrowthLevel(p.getGrowthLevel()+2);
                case WILTING -> p.setGrowthLevel(p.getGrowthLevel()-1);
                case DYING -> p.setGrowthLevel(p.getGrowthLevel()-2);
                default -> {}
            }

            p.setWet(false);
            p.setFertilised(false);

            //Update state based on growth

            if (p.getGrowthLevel() <= 0 && p.state != GrowthState.GERMINATING){
                p.setGrowthLevel(0);
                p.setState(GrowthState.DEAD);
            } else if (p.getGrowthLevel() >= 15){
                p.setGrowthLevel(15);
                p.setState(GrowthState.BLOOMING);
            }
        }
    }

    public static void listPlants(){
        int index = 1;
        for(Plant p : allPlants){
            System.out.println(
                Output.fill(String.valueOf(index),2) + "|" +
                Output.fill(p.getName(), MAX_PLANT_NAME_LENGTH) + ": " +
                (p.isWet() ? "W:[#]" : "W:[-]") + " " +
                (p.isFertilised() ? "F:[#]" : "F:[-]") + " " + 
                Output.fill(p.getState().toString(), 12) + " Growth level:" +
                p.getGrowthLevel()
            ); 
            index ++;
        }
    }
}
