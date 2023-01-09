enum Weather {
    SUNNY,RAINY,CLOUDY,SNOWING;

    public String toString(){
        return switch (this){
            case SUNNY -> "sunny";
            case CLOUDY -> "cloudy";
            case RAINY -> "rainy";
            case SNOWING -> "snowing";
            default -> throw new IllegalArgumentException("Unexpected value: " + this);
        };
    }
}
