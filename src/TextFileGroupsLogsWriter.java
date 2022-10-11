import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;
import java.util.stream.Collectors;

public class TextFileGroupsLogsWriter {

    public void write(String fileName, Set<Pattern> patterns) throws IOException {
        if (patterns.isEmpty()) {
            System.out.println("\nThere are no logs that have the same pattern.\n");
        } else {
            Writer writer = new BufferedWriter(new FileWriter(fileName));
            for (Pattern pattern : patterns) {
                writer.write(formatPattern(pattern));
            }
            writer.close();
        }
    }

    private String formatPattern(Pattern pattern) {
        var patternLogs = pattern.getLogs();
        var logsDescription = patternLogs.stream().map(Log::getLogLine).collect(Collectors.joining("\n"));
        var changingWords = patternLogs.stream().map(log -> log.getLogLine().split(" ")[pattern.getChangingWordsIndex()]).collect(Collectors.joining(", "));
        return "%s \nThe changing words: %s\n\n".formatted(logsDescription, changingWords);
    }
}
