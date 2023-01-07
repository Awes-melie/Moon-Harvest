public class Plant {
    
    /**
     *
     */
    private static final double SUN_GROWTH_RATE = 0.7;
    private static final int PLANT_NO = 10;
    private static Plant[] allPlants = new Plant[PLANT_NO];
    
    private boolean wet;
    private boolean fertilised;
    private int growthLevel;
    private GrowthState state;
    private String name;

    public Plant(boolean wet, boolean fertilised, int growthLevel, GrowthState state, String name) {
        this.wet = wet;
        this.fertilised = fertilised;
        this.growthLevel = growthLevel;
        this.state = state;
        this.name = name;
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
            allPlants[i] = new Plant(false, false, 0, GrowthState.GERMINATING, "Bob");
        }
    }

    public static void waterPlant (int index) {
        allPlants[index].setWet(true);
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
        for(Plant p : allPlants){
            System.out.println(
                Output.fill(p.getName(), 10) + ": " +
                (p.isWet() ? "W:[#]" : "W:[-]") + " " +
                (p.isFertilised() ? "F:[#]" : "F:[-]") + " " + 
                Output.fill(p.getState().toString(), 12) + " Growth level:" +
                p.getGrowthLevel()
            );    
        }
    }
}
