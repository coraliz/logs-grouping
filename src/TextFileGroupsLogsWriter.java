import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;


public class TextFileGroupsLogsWriter {

    private static final String CHANGING_WORDS_DELIMITER = ", ";

    public void write(String fileName, Set<Pattern> patterns) throws IOException {
        if (patterns.isEmpty()) {
            System.out.println("\nThere are no logs that have the same pattern.\n");
        } else {
            var writer = new BufferedWriter(new FileWriter(fileName));
            for (var pattern : patterns) {
                writer.write(formatPattern(pattern));
            }
            writer.close();
        }
    }

    private String formatPattern(Pattern pattern) {
        // todo: write this code better
        var patternLogs = pattern.getLogs();
        var logsDescription = new StringBuilder();
        var changingWordsDescription = new StringBuilder("The changing words: ");
        patternLogs.forEach(log -> logsDescription.append(log.getLogLine()).append("\n"));
        patternLogs.forEach(log -> changingWordsDescription.append(log.getLogLine().split(" ")[pattern.getChangingWordsIndex()]).append(CHANGING_WORDS_DELIMITER));
        changingWordsDescription.setLength(changingWordsDescription.length() - CHANGING_WORDS_DELIMITER.length());
        logsDescription.append(changingWordsDescription).append("\n\n");
        return logsDescription.toString();
    }
//
//    private String formatPattern(Pattern pattern) {
//        var patternLogs = pattern.getLogs();
//        var logsDescription = patternLogs.stream().map(Log::getLogLine).collect(Collectors.joining("\n"));
//        var changingWords = patternLogs.stream().map(log -> log.getLogLine().split(" ")[pattern.getChangingWordsIndex()]).stream().collect(Collectors.joining(", "));
//        return "%s \nThe changing words: %s\n\n".formatted(logsDescription, changingWords);
//    }
//
//    private String writePattern(Pattern pattern, BufferedWriter writer) throws IOException {
//        pattern.getLogs().forEach(log -> writer.write(log.getLogLine() + "\n"));
//        writer.write();
//        var patternLogs = pattern.getLogs();
//        StringBuilder logsDescription = new StringBuilder();
//        logsDescription = pattern.getLogs().map(log -> log.getLogLine()).collect( ... )
//        //var logsDescription = patternLogs.stream().map(Log::getLogLine).collect(Collectors.joining("\n"));
//        var changingWords = patternLogs.stream().map(log -> log.getLogLine().split(" ")[pattern.getChangingWordsIndex()]).stream().collect(Collectors.joining(", "));
//        return "%s \nThe changing words: %s\n\n".formatted(logsDescription, changingWords);
//    }
}
