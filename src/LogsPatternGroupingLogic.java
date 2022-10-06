import java.util.*;

public class LogsPatternGroupingLogic {
    private final HashSet<Pattern> _potentialPatterns;
    private final HashSet<Pattern> _activePatterns;

    public LogsPatternGroupingLogic() {
        _potentialPatterns = new HashSet<>();
        _activePatterns = new HashSet<>();
    }

    public HashSet<Pattern> getActivePatterns() {
        return _activePatterns;
    }

    public void findAllPatterns(HashMap<Integer, ArrayList<Log>> logsGroupedByLength) {
        /*
         Divides the logos into groups according to the amount of spaces in the sentence and checks common patterns between them.
         It's based on the assumption that identical patterns will have the same amount of spaces.
        */
        for (ArrayList<Log> logs : logsGroupedByLength.values()) {
            for (Log log : logs) {
                findAllPatternsInGroup(log);
            }
            _potentialPatterns.clear();
        }
    }

    private void findAllPatternsInGroup(Log currentLog) {
        if (currentLog.getExtractedPatterns().stream().noneMatch(currentPattern -> (matchPatterns(currentPattern,
                _potentialPatterns, true) || matchPatterns(currentPattern, _activePatterns, false)))) {
            //when there is no match to the existing patterns, the new patterns are added to the potential patterns set.
            _potentialPatterns.addAll(currentLog.getExtractedPatterns());
        }
    }

    private boolean matchPatterns(Pattern currentPattern, HashSet<Pattern> patterns, boolean isPotential) {
        for (Pattern pattern : patterns) {
            if (currentPattern.equals(pattern)) {
                pattern.addLog(currentPattern.getFirstLog());
                if (isPotential) {
                    _activePatterns.add(pattern);
                    // removes all the extracted patterns from the log to which a match was found.
                    (pattern.getFirstLog()).getExtractedPatterns().forEach(_potentialPatterns::remove);
                }
                return true;
            }
        }
        return false;
    }
}
