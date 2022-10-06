import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;

public class TextFileGroupsLogsWriter {

    public void write(String fileName, HashSet<Pattern> patterns) throws IOException {
        if (patterns.isEmpty()) {
            System.out.println("\nThere are no logs that have the same pattern.\n");
        } else {
            Writer writer = new BufferedWriter(new FileWriter(fileName));
            for (Pattern pattern : patterns) writer.write(formatPattern(pattern));
            writer.close();
        }
    }

    public String formatPattern(Pattern pattern) {
        // todo: think of a nicer way to write this code
        StringBuilder patternDescription = new StringBuilder();
        StringBuilder changingWordsDescription = new StringBuilder("The changing words: ");
        boolean isFirstLog = true;
        for(Log log : pattern.getLogs()){
            var logLine = log.getLogLine();
            patternDescription.append(logLine).append("\n");
            var changingWord = logLine.split(" ")[pattern.getChangingWordsIndex()];
            if (!isFirstLog){
                changingWordsDescription.append(", ").append(changingWord);
            }
            else{
                changingWordsDescription.append(changingWord);
                isFirstLog = false;
            }
        }
        changingWordsDescription.append("\n\n");
        patternDescription.append(changingWordsDescription);
        return patternDescription.toString();
    }
}
