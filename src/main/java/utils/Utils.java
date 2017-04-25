package utils;


import java.util.ArrayList;
import java.util.List;

public class Utils {
    /**
     * <p>Rappel:
     * <br />["1","2",...,"n"]
     * <br />List< String > list;
     * <br />list.get(0) = ["1"
     * <br />list.get(2) = "2"
     * <br />...
     * <br />list.get(n) = "n"]
     * </p>
     * @param stringList
     * @return
     */
    public static List<Long> getCorrectIdList(List<String> stringList) {
        List<Long> listeFilmId = new ArrayList<Long>();
        if(stringList != null){
            for(String s : stringList){
                s = s.replaceAll("\"", "");
                s = s.replaceAll("\\]", "");
                s = s.replaceAll("\\[", "");
                if(!s.equals(""))
                    listeFilmId.add(Long.valueOf(s));
            }
        }
        return listeFilmId;
    }
}
