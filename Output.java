public class Output {

    public static void instructions (){
        Output.lineBreak();
        System.out.println("GOAL");
        System.out.println("Your goal is to grow and harvest as many plants as you can.");
        System.out.println("Each plant needs daily watering or it will start dying.");
        System.out.println("All the plants start germinating and can't die.");
        System.out.println("They will remain so until you water them for the first time.");
        System.out.println("The weather may effect the growth of your plants.");
        System.out.println("You have a certain amount of actions per day determined by your energy level.");
        System.out.println("You have 27 days until the full moon rises again.");
        Output.lineBreak();
        System.out.println("CONTROLS");
        System.out.println("Plants can be reffered to by name or by number, multiple can also be listed if space-seperated.");
        System.out.println("Some actions can be repeated a number of times.");
        System.out.println("water|w (plants): Sets the plant to watered if you have any water left.");
        System.out.println("fertilise|f (plants): Sets the plant to fertilised if you have any fertiliser left.");
        System.out.println("harvest|h (plants): Harvests the plant if it is blooming.");
        System.out.println("gather|g (amount): If it is raining or snowing, collect a large amount of rainwater per action.");
        System.out.println("tap|t (amount): Collect a small amount tapwater per action.");
        System.out.println("order|o: Order a batch of 10 fertiliser, it might take a couple days to arrive.");
        System.out.println("pass|p (amount): Skip any number of turns.");
        System.out.println("instructions|i: See this page again.");
        System.out.println("exit|e: Quit the game.");
    }
    public static void actions(int energy){
        System.out.println("You have " + energy + " actions left today.");
    }

    public static void resources(int water, int fertiliser){
        System.out.println("You have " + water + " water and " + fertiliser + " fertilsier.");
    }

    public static void inputError(){
        System.out.println("Input error.");
    }
    public static void intro(){
        System.out.println("'Fuck!' You think to yourself, staring at the broken cauldron sitting in the middle of your apartment.");
        System.out.println("'I was so close! If only--' You stop, the potion had almost worked, but it relied on poor quality ingredients.");
        System.out.println("You look around the apartment seeing your plants, nay your Friends! They could help you complete the potion!");
        System.out.println("Staring up at the full moon outside the window, you realise that you only have a month before the next oppourtunity arises.");
        System.out.println("'This time, it has to work.'");
        Output.instructions();
    }
    public static void outro(int plants){
        System.out.println("YOU COLLECTED " + plants + " PLANT"+(plants==1 ?  "" : "S")+"!");
        if (plants == 0){
            System.out.println("The moon rises after a month of intense growing, and you look down at the nonexistent collection of ingredients you have.");
            System.out.println("Somehow, despite everything, you have no plants fully grown after all this time. You slump down on the chair next to your cauldron with a sigh.");
            System.out.println("You have failed as an alchemist.");
        } else if (plants <= 3){
            System.out.println("The moon rises after a month of intense growing, and you look down at the meager collection of ingredients you have.");
            System.out.println("It isn't as many as you'd like but it might just be enough. Halfheartedly you pour the ingredients into the cauldron and begin stirring.");
            System.out.println("In the last moment you notice it turn just the wrong shade of pink and have just enough time to jump out of the way.");
            System.out.println("Unfortunately, however, your apartment was not spared from the explosion.");
            System.out.println("Looks like you might have to find a new profession.");
        } else if (plants <= 6){
            System.out.println("The moon rises after a month of intense growing, and you look down at the decent collection of ingredients you have.");
            System.out.println("It isn't quite as many as you'd like but it will probably be enough. Nervously you pour the ingredients into the cauldron and begin stirring.");
            System.out.println("The potion begins glowing a deep red, this is a good sign! It looks like it's working!");
            System.out.println("Just as the reaction is about to complete however, the potion lets out a belch and spits a thick blob of congealed substance onto your face.");
            System.out.println("'I was so close!' You yell. But who knows how much strength you have left in you to try again.");
        }  else if (plants <= 8){
            System.out.println("The moon rises after a month of intense growing, and you look down at the excellent collection of ingredients you have.");
            System.out.println("It's more than you'd expected, and it will almost certainly be enough. Excitedly you pour the ingredients into the cauldron and begin stirring.");
            System.out.println("The potion begins glowing a deep red, this is a good sign! It looks like it's working!");
            System.out.println("Next the potion shifts to a deep blue, it's near completion!");
            System.out.println("After what seems like an age, you look down into the cauldron to see the result.");
            System.out.println("'It worked!' You grin.");
        } else {
            System.out.println("The moon rises after a month of intense growing, and you look down at the magnificent collection of ingredients you have.");
            System.out.println("It's significantly more than you'd expected, and it will be more than enough. Confidently you pour the ingredients into the cauldron and begin stirring.");
            System.out.println("The potion begins glowing a deep red, this is a good sign! It looks like it's working!");
            System.out.println("Next the potion shifts to a deep blue, it's near completion!");
            System.out.println("After what seems like an age, you look down into the cauldron to see the result.");
            System.out.println("The potion has turned golden? This is not what you expected, you flick through your notebooks furiously trying to understand what is going on.");
            System.out.println("You turn around just to late to realise what you have created.");
            System.out.println("'It worked.' You mutter somberly.");
        }
        Player.getInput();
    }
    public static void lineBreak(int energy, int water, int fertiliser){
        System.out.println("-=["+Output.fill(String.valueOf(energy),2)+"]=["+Output.fill(String.valueOf(water),2)+"]=["+Output.fill(String.valueOf(fertiliser),2)+"]=====-");
    }

    public static void lineBreak(){
        System.out.println("-====================-");
    }

    public static void bigBreak(int day, int energy, int water, int fertiliser){
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
        System.out.println("-=["+Output.fill(String.valueOf(energy),2)+"]=["+Output.fill(String.valueOf(water),2)+"]=["+Output.fill(String.valueOf(fertiliser),2)+"]=====- DAY "+ day +" -====================-");
    }

    public static void bigBreak(int day){
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
        System.out.println("-====================- DAY "+ day +" -====================-");
    }

    public static void dailyUpdate(Weather currentWeather, int currentEnergy) {
        System.out.println("Today it is " + currentWeather + " and you feel " + (currentEnergy <= 3 ? "exhausted" : ( currentEnergy <= 4 ? "normal" : "energetic" ) ) + ".");
    }
    public static void notEnoughFertiliser() {
        System.out.println("Sorry, there's not enough fertiliser.");
    }

    public static void notInBloom() {
        System.out.println("This plant is not ready to harvest yet.");
    }

    public static String fill(String string, int length){
        if (string.length() == length){
            return string;
        } else if (string.length() < length){
            int diff = length-string.length();
            for (int i = 0; i < diff; i++) {
                string = string + " ";
            }
        } else {
            string = string.substring(0, length);
        }
        return string;
    }
    public static void purchase(int daysTillFertiliser) {
        if(daysTillFertiliser>0){
            System.out.println("Your order of fertiliser will arrive in " + daysTillFertiliser + " day"+ (daysTillFertiliser == 1 ? "" : "s") +".");
        } else if (daysTillFertiliser == 0 ){
            System.out.println("Your order of fertiliser has arrived!");
        }
    }

    public static int randomInRange(int min, int max){
        return (int) Math.floor((Math.random() * (max-min)) + min);
    }
    
}
