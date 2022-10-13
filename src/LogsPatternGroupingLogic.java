import java.util.*;

public class LogsPatternGroupingLogic {
    private final ExtendedHashSet<Pattern> _potentialPatterns;
    private final ExtendedHashSet<Pattern> _activePatterns;

    public LogsPatternGroupingLogic() {
        _potentialPatterns = new ExtendedHashSet<>();
        _activePatterns = new ExtendedHashSet<>();
    }

    public Set<Pattern> findAllPatterns(HashMap<Integer, ArrayList<Log>> logsGroupedByLength) {
        _potentialPatterns.clear();
        _activePatterns.clear();
         //Divides the logos into groups according to the amount of spaces in the sentence and checks common
        // patterns between them. It's based on the assumption that identical patterns will have the same
        // amount of spaces.
        for (var logs : logsGroupedByLength.values()) {
            for (var log : logs) {
                findAllPatternsInGroup(log);
            }
            _potentialPatterns.clear();
        }
        return _activePatterns;
    }

    private void findAllPatternsInGroup(Log currentLog) {
        if (currentLog.getExtractedPatterns().stream().noneMatch(currentPattern -> (matchPatterns(currentPattern,
                _potentialPatterns, true) || matchPatterns(currentPattern, _activePatterns, false)))) {
            // when there is no match to the existing patterns, the new patterns are added to the potential patterns set.
            var extractedPatterns = currentLog.getExtractedPatterns();
            _potentialPatterns.addAll(extractedPatterns);
        }
    }

    private boolean matchPatterns(Pattern currentPattern, ExtendedHashSet<Pattern> patterns, boolean isPotential) {
        var pattern = patterns.getOrDefault(currentPattern, null);
        if (pattern != null) {
            pattern.addLog(currentPattern.getFirstLog());
            if (isPotential) {
                _activePatterns.add(pattern);
                pattern.getFirstLog().getExtractedPatterns().forEach(_potentialPatterns::remove);
                return true;
            }
        }
        return false;
    }
}
