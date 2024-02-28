import java.util.*;
public class DebugLogger{

    private static boolean m_debugMode;

    public static void setDebugMode(boolean debugMode){
        m_debugMode = debugMode;
    }

    public void log(boolean message){
        if(m_debugMode == true){
            if(message == true){
                System.out.println("//DebugLog// True");
            }
            else{
                System.out.println("//DebugLog// False");
            }
        }
    }

    public void log(String message){
        if(m_debugMode == true){
            System.out.println("//DebugLog//"+ message + "\n");
        }
    }

    public void log(List<String> message){
        if(m_debugMode == true){
            for(int loop = 0; loop < message.size(); loop++){
                System.out.println("//DebugLog//"+ message.get(loop)+"\n");
            }
        }
    }
}