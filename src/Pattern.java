import java.util.ArrayList;
import java.util.Objects;

public class Pattern {
    final private String _logWildcardSentence;
    final private ArrayList<Log> _logs;
    final private int _changingWordsIndex;

    public Pattern(String wildcard, int changingWordIndex, Log firstLog) {
        _logWildcardSentence = wildcard;
        _logs = new ArrayList<>();
        _logs.add(firstLog);
        _changingWordsIndex = changingWordIndex;
    }

    public void addLog(Log additionalLog) {
        _logs.add(additionalLog);
    }

    public String getLogWildcardSentence(){
        return _logWildcardSentence;
    }

    public Log getFirstLog(){
        //ArrayList is an ordered collection
        return _logs.get(0);
    }

    public ArrayList<Log> getLogs(){
        return _logs;
    }

    public int getChangingWordsIndex(){
        return _changingWordsIndex;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pattern pattern = (Pattern) o;
        return Objects.equals(_logWildcardSentence, pattern.getLogWildcardSentence());
    }

    @Override
    public int hashCode() {
        return Objects.hash(_logWildcardSentence);
    }
}
