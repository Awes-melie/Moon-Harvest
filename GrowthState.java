enum GrowthState {
    HARVESTED, BLOOMING, FLOURISHING, GROWING, DORMANT, GERMINATING, WILTING, DYING, DEAD;
    public String toString(){
        return switch (this){
            case HARVESTED -> Output.fill("Harvested",12) + "[#]";
            case BLOOMING -> Output.fill("Blooming",12) + "[*]";
            case DEAD -> Output.fill("Dead",12) + "[_]";
            case DORMANT -> Output.fill("Dormant",12) + "[0]";
            case DYING -> Output.fill("Dying",12) + "[!]";
            case FLOURISHING -> Output.fill("Flourishing",12) + "[|]";
            case GERMINATING -> Output.fill("Germinating",12) + "[.]";
            case GROWING -> Output.fill("Growing",12) + "[/]";
            case WILTING -> Output.fill("Wilting",12) + "[?]";
            default -> throw new IllegalArgumentException("Unexpected value: " + this);
            
        };
    }
}
