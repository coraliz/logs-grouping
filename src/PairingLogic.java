import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PairingLogic {
    private static final String WORD_REPLACEMENT = "?";
    private final HashSet<Pattern> availablePatterns;
    private final HashSet<Pattern> usedPatterns;
    private final Map<Integer, List<String>> logsByLength;

    public PairingLogic(Map<Integer, List<String>> logsByLength){
        this.logsByLength = logsByLength;
        this.availablePatterns = new HashSet<>();
        this.usedPatterns = new HashSet<>();
    }

    private void removeAvailablePatterns(String log){
        availablePatterns.removeIf(currentPattern -> (currentPattern.getFirstLog()).equals(log));
    }

    private String getWildcard(String[] logWithoutTime, int i){
        String[] tempSplitLog = logWithoutTime.clone();
        tempSplitLog[i] = WORD_REPLACEMENT;
        return String.join(" ", tempSplitLog);
    }

    private void findAllPatternsInGroup(String currentLog) {
        //02-01-2012 10:14:00 Nadav loves animals  -> [02-01-2012, 10:14:00, Nadav loves animals]
        // -> [Nadav, loves ,animals]
        String[] currentLogWithoutTime = (currentLog.split(" ", 3)[2]).split(" ");
        for (int i = 0; i < currentLogWithoutTime.length; i++) {
            String wildcard = getWildcard(currentLogWithoutTime, i);
            Optional<Pattern> answer = this.hasWildcard(availablePatterns, wildcard);
            String changingWord = currentLogWithoutTime[i];
            if (answer.isPresent()) { // in available
                Pattern patternWithCurrentWildcard = answer.get();
                patternWithCurrentWildcard.addLog(currentLog, changingWord);
                this.usedPatterns.add(patternWithCurrentWildcard);
                this.removeAvailablePatterns(currentLog);
                this.removeAvailablePatterns(patternWithCurrentWildcard.getFirstLog());
                break;
            } else {
                answer = this.hasWildcard(usedPatterns, wildcard);
                if (answer.isPresent()) {
                    Pattern patternWithCurrentWildcard = answer.get();
                    patternWithCurrentWildcard.addLog(currentLog, changingWord);
                    this.removeAvailablePatterns(currentLog);
                    break;
                } else {
                    // didn't find a match
                    Pattern currentLogPattern = new Pattern(wildcard, changingWord, currentLog);
                    availablePatterns.add(currentLogPattern);
                }
            }
        }
    }

    private Optional<Pattern> hasWildcard(HashSet<Pattern> patterns, String wildcard){
        for (Pattern pattern : patterns) {
            if (wildcard.equals(pattern.getWildcard())){
                return Optional.of(pattern);
            }
        }
        return Optional.empty();
    }

    public void findAllPatterns(){
        for (List<String> logs : logsByLength.values()) {
            for (String log : logs) {
                this.findAllPatternsInGroup(log);
            }
        }
        //reset memory
        this.availablePatterns.clear();
    }

    public Optional<HashSet<Pattern>> getPatterns(){
        if (this.usedPatterns.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(this.usedPatterns);
    }

}
