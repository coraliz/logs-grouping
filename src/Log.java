import java.util.ArrayList;

public class Log {
    private static final String CHANGING_WORD_REPLACEMENT = "?";
    private static final int LOG_SENTENCE_FIRST_INDEX = 2;
    final private String _logLine;

    public Log(String logLine) {
        _logLine = logLine;
    }

    public String getLogLine(){
        return _logLine;
    }

    public ArrayList<Pattern> getExtractedPatterns(){
        ArrayList<Pattern> extractedPatterns = new ArrayList<>();
        var logWords = extractWordsFromLog();
        for (int i = 0; i < logWords.length; i++) {
            var tempLogWords = logWords.clone();
            tempLogWords[i] = CHANGING_WORD_REPLACEMENT;
            var logWildCardSentence = String.join(" ", tempLogWords);
            Pattern pattern = new Pattern(logWildCardSentence, i + LOG_SENTENCE_FIRST_INDEX, this);
            extractedPatterns.add(pattern);
        }
        return extractedPatterns;
    }

    private String[] extractWordsFromLog(){
        var logSentence = _logLine.split(" ", 3)[LOG_SENTENCE_FIRST_INDEX];
        return logSentence.split(" ");
    }
}
