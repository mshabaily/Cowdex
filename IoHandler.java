import java.util.*;
import javax.swing.JFrame;
class IoHandler{
    public DebugLogger debugLogger = new DebugLogger();
    private boolean m_gameLoop;

    public void setGameLoop(boolean gameLoop){
        m_gameLoop = gameLoop;
    }

    public boolean getGameLoop(){
        return m_gameLoop;
    }

    public void printMainMenu(){
        
    }

    public String printCowData(List<Cow> cowList, String outString){
        for(int printCowLoop = 0; printCowLoop< cowList.size(); printCowLoop++){
            Cow nextCow = cowList.get(printCowLoop);
            outString += "["+ Integer.toString(nextCow.getId()+1) + "] ";
            outString += (nextCow.getName()) + "\n";
        }
        return outString;
    }

    public void printMenu(List<Cow> cowList, String outString){
        outString = printCowData(cowList, outString);
        System.out.println(outString);
    }

    public void printRandomFact(List<String> facts){
        Random random = new Random();
        int randomInt = random.nextInt(facts.size());
                              
        System.out.println("\n_____________\nDid You Know?\n" + facts.get(randomInt));
    }

    public int selectOption(Scanner scanner) throws SelectionOutOfRangeException{
        System.out.println("Select Option:\n(0 to Exit)");
        int selection = 0;
        try{
            selection = scanner.nextInt();
            scanner.nextLine();
            if(selection == 0){
                this.setGameLoop(false);
            }
        }
        catch(Exception e){
            SelectionOutOfRangeException soore = new SelectionOutOfRangeException("\nInvalid Input\nMust be an integer");
            throw soore;
        }
        return selection;
    }

    public String inputString(Scanner scanner) throws SelectionOutOfRangeException{
        System.out.println("Enter:\n(0 to Exit)");
        String selection = scanner.nextLine();
        debugLogger.log("Selection = " + selection);
        if(selection.equals("0")){
            this.setGameLoop(false);
        }
        return selection;
    }

    public void ioWait(){
        System.out.println("\n[Press Enter to continue]");
        try
        {
            System.in.read();
        }  
        catch(Exception e)
        {}
    }
}