import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Cowdex{

    public DebugLogger debugLogger = new DebugLogger();
    
    private List<Cow> m_cowList;

    public Cowdex(Path filePath) throws IOException{
        int newCowId = 0;
        String newCowName = "";
        List<String> newCowFacts = new ArrayList<String>();
        List<Cow> newCowList = new ArrayList<Cow>();
        try{
            BufferedReader br = Files.newBufferedReader(filePath);
            int line = -1;
            String str = "";
            while((str = br.readLine()) != null){
                line++;
                if(line == 0){
                    newCowId = Integer.parseInt(str);
                }
                if(line == 1){
                    newCowName = str;
                }
                boolean isStrEnd = false;
                if(line > 1 && (isStrEnd = str.equals("END")) != true){
                    newCowFacts.add(str);
                }
                if(str.equals("END")){
                    Cow newCow = new Cow(newCowId, newCowName, newCowFacts);
                    newCowFacts = new ArrayList<String>();
                    newCowList.add(newCow);
                    line = -1;
                }
            }
        }
        catch(IOException ioe){
            throw ioe;
        }
        m_cowList = new ArrayList<Cow>(newCowList);
    }

    public static void save(Path filePath, List<Cow> updatedCowList) throws IOException{
        try{
            BufferedWriter bw = Files.newBufferedWriter(filePath);
            for(int loop = 0; loop < updatedCowList.size(); loop++){
                Cow cow = updatedCowList.get(loop);
                String cowId = Integer.toString(loop);
                String cowName = cow.getName();
                List<String> cowFactsList = cow.getFacts();
                if(loop > 0){
                    bw.newLine();
                }
                bw.write(cowId);
                bw.newLine();
                bw.write(cowName);
                for(int writeLoop = 0; writeLoop < cowFactsList.size(); writeLoop++){
                    bw.newLine();
                    bw.write(cowFactsList.get(writeLoop));
                }
                bw.newLine();
                bw.write("END");
            }
            bw.flush();
        }
        catch(IOException ioe){
            throw ioe;
        }
    }

    public List<Cow> getCowList(){
        return m_cowList;
    }

    public void adjustIds(int deletedCowPos){
        for(int adjustmentPos = deletedCowPos; adjustmentPos < m_cowList.size(); adjustmentPos++){
            Cow nextCow = m_cowList.get(adjustmentPos);
            int id = 0;
            nextCow.adjustId(id = m_cowList.indexOf(nextCow));
        }
    }
}