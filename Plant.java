public class Plant {
    
    private static final int PLANT_NO = 10;
    private static Plant[] allPlants = new Plant[PLANT_NO];
    

    private boolean wet;
    private int growthState;

    public int getGrowthState() {return growthState;}
    public void setGrowthState(int growthState) {this.growthState = growthState;}
    public boolean isWet() {return wet;}
    public void setWet(boolean wet) {this.wet = wet;}

    public static void initPlants(){

        System.out.println("INITPLANTS");
        for (int i = 0; i < PLANT_NO; i++) {
            allPlants[i] = new Plant(false, 0);
        }
    }

    public Plant(boolean wet, int growthState) {
        this.wet = wet;
        this.growthState = growthState;
    }

    public static void waterPlant (int index) {
        allPlants[index].setWet(true);
    }

    public static void plantUpdate(){

        System.out.println("PLANTUPDATE");

        for (Plant p : allPlants){
            if(p.isWet()){
                p.setGrowthState(p.getGrowthState()+1);
                p.setWet(false);
            }
        }
    }

    public static void listPlants(){
        System.out.println("LISTPLANTS");

        String plantDisplay = "";
        for(Plant p : allPlants){
            plantDisplay = plantDisplay + p.getGrowthState();
        }
        System.out.println(plantDisplay);
    }
}
