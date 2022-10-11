import java.util.*;

public class LogsPatternGroupingLogic {
    private final HashMap<Pattern, Pattern> _potentialPatterns;
    private final HashMap<Pattern, Pattern> _activePatterns;

    public LogsPatternGroupingLogic() {
        _potentialPatterns = new HashMap<>();
        _activePatterns = new HashMap<>();
    }

    public Set<Pattern> getActivePatterns() {
        return _activePatterns.keySet();
    }

    public void findAllPatterns(HashMap<Integer, ArrayList<Log>> logsGroupedByLength) {
         //Divides the logos into groups according to the amount of spaces in the sentence and checks common
        // patterns between them. It's based on the assumption that identical patterns will have the same
        // amount of spaces.
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
            // when there is no match to the existing patterns, the new patterns are added to the potential patterns set.
            var extractedPatterns = currentLog.getExtractedPatterns();
            extractedPatterns.forEach(pattern -> _potentialPatterns.put(pattern, pattern));
            //todo: talk about it
            //_potentialPatterns.addAll(currentLog.getExtractedPatterns());
        }
    }

    private boolean matchPatterns(Pattern currentPattern, HashMap<Pattern, Pattern> patterns, boolean isPotential) {
        //todo: talk about it
        var pattern = patterns.getOrDefault(currentPattern, null);
        if (pattern != null) {
            pattern.addLog(currentPattern.getFirstLog());
            if (isPotential) {
                _activePatterns.put(pattern, pattern);
                pattern.getFirstLog().getExtractedPatterns().forEach(_potentialPatterns::remove);
                return true;
            }
        }
        return false;
    }
}
