import java.util.ArrayList;
import java.util.Objects;

public class Pattern {
    private static final String CHANGING_WORD_REPLACEMENT = " ";
    private final String _patternString;
    private final ArrayList<Log> _logs;
    private final int  _changingWordsIndex;

    public Pattern(int changingWordIndex, Log firstLog) {
        // Extracts the string from the log
        var firstLogWords = firstLog.getWords();
        firstLogWords[changingWordIndex] = CHANGING_WORD_REPLACEMENT;
        _patternString = String.join(" ", firstLogWords);
        _logs = new ArrayList<>();
        _logs.add(firstLog);
        _changingWordsIndex = changingWordIndex;
    }

    public void addLog(Log additionalLog) {
        _logs.add(additionalLog);
    }

    public String getPattenString(){
        return _patternString;
    }

    public Log getFirstLog(){
        // ArrayList is an ordered collection
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
        return Objects.equals(_patternString, pattern.getPattenString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(_patternString);
    }
}
