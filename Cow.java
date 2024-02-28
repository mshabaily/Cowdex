import java.util.*;

public class Cow{

    private int m_id;
    private String m_name;
    private List<String> m_facts;

    public Cow(int id, String name, List<String> facts){
        m_id = id;
        m_name = name;
        m_facts = facts;
    }

    public int getId(){
        return m_id;
    }

    public String getName(){
        return m_name;
    }

    public List<String> getFacts(){
        return m_facts;
    }

    public void adjustId(int id){
        m_id = id;
    }

}