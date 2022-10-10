import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.stream.Collectors;

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

    private String formatPattern(Pattern pattern) {
        // todo: make sure it's ok like that. it seems unreadable.
        var patternLogs = pattern.getLogs();
        return patternLogs.stream().map(Log::getLogLine).collect(Collectors.joining("\n")) + "\n" + "The changing words: "
                + patternLogs.stream().map(log -> log.getLogLine().split(" ")[pattern.getChangingWordsIndex()]).collect(Collectors.joining(", ")) + "\n\n";
    }
}
