import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Optional;

public class OutputHandler {

    public void extract(String fileName, Optional<HashSet<Pattern>> patterns) throws IOException {
        if (patterns.isPresent()) {
            Writer writer = new BufferedWriter(new FileWriter(fileName));
            for (Pattern pattern : patterns.get()) writer.write(pattern.toString());
            writer.close();
        } else {
            System.out.println("\nThere are no logs that are similar to each other.\n");
        }
    }
}
