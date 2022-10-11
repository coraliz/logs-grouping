import java.util.ArrayList;

public class Log {
    // A word can never be a space, and there's only one space between two words.
    // So this word replacement doesn't create an ambiguous pattern.
    private static final int LOG_SENTENCE_FIRST_INDEX = 2;
    private final String _logLine;
    private final String[] _words;

    public Log(String logLine) {
        _logLine = logLine;
        var logSentence = _logLine.split(" ", LOG_SENTENCE_FIRST_INDEX + 1)[LOG_SENTENCE_FIRST_INDEX];
        _words = logSentence.split(" ");
    }

    public String getLogLine(){
        return _logLine;
    }

    public String[] getWords() { return _words.clone(); } // Clone so caller can modify the array without impact to internal state

    public ArrayList<Pattern> getExtractedPatterns(){
        ArrayList<Pattern> extractedPatterns = new ArrayList<>();
        for (int i = 0; i < _words.length; i++) {
            Pattern pattern = new Pattern(i, this);
            extractedPatterns.add(pattern);
        }
        return extractedPatterns;
    }
}
