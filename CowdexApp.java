import java.nio.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CowdexApp{

    public static void main(String[] args) throws IOException, ImproperArgumentException, SelectionOutOfRangeException, CowdexEmptyException{
        if(args.length < 1){
            ImproperArgumentException iae = new ImproperArgumentException("\nAn argument must be given\nproper use:\njava CowApp release (runs in release mode)\njava CowApp debug (runs in debug mode)");
            throw iae;
        }
        if(args.length > 1){
            ImproperArgumentException iae = new ImproperArgumentException("\nMultiple arguments cannot be given\nproper use:\njava CowApp release (runs in release mode)\njava CowApp debug (runs in debug mode)\n");
            throw iae;
        }
        DebugLogger debugLogger = new DebugLogger();
        if(args[0].equals("debug")){
            debugLogger.setDebugMode(true);
        }
        else if(args[0].equals("release")){
            debugLogger.setDebugMode(false);
        }
        else{
            ImproperArgumentException iae = new ImproperArgumentException("\nInvalid argument\nproper use:\njava CowApp release (runs in release mode)\njava CowApp debug (runs in debug mode)");
            throw iae;
        }

        File cowdexDatabase = new File("cowdexDatabase.txt");
        Cowdex cowdex = new Cowdex(cowdexDatabase.toPath());
        List<Cow> cowList = new ArrayList<Cow>();
        cowList = cowdex.getCowList();
        IoHandler io = new IoHandler();
        Scanner scanner = new Scanner(System.in);
        boolean gameLoop = false;
        io.setGameLoop(true);
        while((gameLoop = io.getGameLoop()) == true){
            io.printMainMenu(); //("________________________\n|------COWDEX 2.0------|\n````````````````````````\n[1] Cow Facts\n[2] Edit Cowdex\n");
            int menuSelection = io.selectOption(scanner);
            if(menuSelection == 1){
                if(cowList.size() == 0){
                    CowdexEmptyException cee = new CowdexEmptyException("\nNo cows in cowdex\nSelect \"Edit Cowdex\" to create a cow");
                    throw cee;
                }
                io.printMenu(cowList, "________________________\n|------Cow Facts-------|\n````````````````````````\n");
                int factSelection = io.selectOption(scanner);
                int cowAmount = cowList.size();
                if(factSelection <= cowAmount+1 && factSelection > 0){
                    Cow selectedCow = cowList.get(factSelection-1);
                    List<String> selectedCowFacts = new ArrayList<String>();
                    selectedCowFacts = selectedCow.getFacts();
                    io.printRandomFact(selectedCowFacts);
                    io.ioWait();
                }
                else if (gameLoop = io.getGameLoop() == true){
                    String outString = "\nInvalid Selection\nproper use:\nBetween: ";
                    outString += "1 and " + (cowAmount + 1);
                    SelectionOutOfRangeException soore = new SelectionOutOfRangeException(outString);
                    throw soore;
                }
            }
            else if(menuSelection == 2){
                System.out.println("________________________\n|------Edit Cowdex-----|\n[1] Add Cow\n[2] Delete Cow");
                int editSelection = io.selectOption(scanner);
                if(editSelection == 1){
                    io.printMenu(cowList, "________________________\n|-------Add Cow--------|\n````````````````````````\n");
                    System.out.println("Enter name");
                    String newCowName = io.inputString(scanner);
                    System.out.println("How many facts would you like to add?\n");
                    int factNumber = io.selectOption(scanner);
                    List<String> newFactsList = new ArrayList<String>();
                    for(int factListLoop = 0; factListLoop < factNumber; factListLoop++){
                        System.out.println("Enter fact: " + (factListLoop+1));
                        String fact = io.inputString(scanner);
                        newFactsList.add(fact);
                    }
                    Cow newCow = new Cow(cowList.size(), newCowName, newFactsList);
                    cowList.add(newCow);
                    cowdex.save(cowdexDatabase.toPath(), cowList);
                    cowList = cowdex.getCowList();
                }
                else if(editSelection == 2){
                    io.printMenu(cowList, "________________________\n|------Delete Cow------|\n````````````````````````\n");
                    int deleteSelection = io.selectOption(scanner);
                    if(deleteSelection > 0 && deleteSelection <= cowList.size()){
                        cowList.remove(deleteSelection-1);
                        cowdex.adjustIds(deleteSelection-1);
                        cowdex.save(cowdexDatabase.toPath(), cowList);
                    }
                }
                else if (gameLoop = io.getGameLoop() == true){
                    SelectionOutOfRangeException soore = new SelectionOutOfRangeException("\nInvalid Selection\nproper use:\nBetween 1 and 2");
                    throw soore;

                }
            }
            else if (gameLoop = io.getGameLoop() == true){
                SelectionOutOfRangeException soore = new SelectionOutOfRangeException("\nInvalid Selection\nproper use:\nBetween 1 and 3");
                throw soore;
            }
        }
    }
}