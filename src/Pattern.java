import java.util.ArrayList;
import java.util.Objects;

public final class Pattern {
    // todo : appears twice!
    private static final int LOG_SENTENCE_FIRST_INDEX = 2;
    private static final String CHANGING_WORD_REPLACEMENT = " ";
    private final String _patternString;
    private final ArrayList<Log> _logs;
    private final int  _changingWordsIndex;

    public Pattern(int changingWordIndex, Log firstLog) {
        // Extracts the string from the log
        // todo : we can't use changingWordIndex because it's global
        var firstLogWords = firstLog.getWords();
        firstLogWords[changingWordIndex - LOG_SENTENCE_FIRST_INDEX] = CHANGING_WORD_REPLACEMENT;
        _patternString = String.join(" ", firstLogWords);
        _logs = new ArrayList<>();
        _logs.add(firstLog);
        _changingWordsIndex = changingWordIndex;
    }

    public void addLog(Log additionalLog) {
        _logs.add(additionalLog);
    }


    public Log getFirstLog(){
        // ArrayList is an ordered collection
        return _logs.get(0);
    }

    public Iterable<Log> getLogs(){
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
        return Objects.equals(_patternString, pattern._patternString);
    }

    @Override
    public int hashCode() {
        return _patternString.hashCode();
    }
}
