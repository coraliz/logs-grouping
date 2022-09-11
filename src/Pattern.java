import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pattern {
    final private String wildcard;
    final private List<String> logs;
    final private List<String> words;

    public Pattern(String wildcard, String changingWord, String firstLog) {
        this.wildcard = wildcard;
        this.words = new ArrayList<>();
        this.logs = new ArrayList<>();
        this.logs.add(firstLog);
        this.words.add(changingWord);
    }

    public void addLog(String additionalLog, String changingWord) {
        this.logs.add(additionalLog);
        this.words.add(changingWord);
    }

    public String getWildcard(){
        return this.wildcard;
    }

    public String getFirstLog(){
        return this.logs.get(0);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pattern pattern = (Pattern) o;
        return Objects.equals(wildcard, pattern.wildcard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wildcard);
    }

    @Override
    public String toString() {
        StringBuilder patternDescription = new StringBuilder();
        boolean isFirstWord = true;
        for(String log : this.logs){
            patternDescription.append(log).append("\n");
        }
        patternDescription.append("The changing words: ");
        for(String word : this.words){
            if (isFirstWord){
                patternDescription.append(word);
                isFirstWord = false;
            }
            else{
                patternDescription.append(", ").append(word);
            }
        }
        patternDescription.append("\n\n");
        return patternDescription.toString();
    }
}
