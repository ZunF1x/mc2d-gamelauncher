package fr.mc2d.gamelauncher.util;

import fr.mc2d.gamelauncher.util.logger.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ProcessLogManager extends Thread {

    private boolean print;

    private final InputStreamReader inputStreamReader;
    private final BufferedReader reader;

    private BufferedWriter writer;

    public ProcessLogManager(InputStream input) {
        this(input, null);
    }

    public ProcessLogManager(InputStream input, Path toWrite) {
        this.inputStreamReader = new InputStreamReader(input, StandardCharsets.UTF_8);
        this.reader = new BufferedReader(this.inputStreamReader);
        this.print = true;

        if (toWrite == null) return;

        try {
            this.writer = Files.newBufferedWriter(toWrite);
        } catch (IOException e) {
            Logger.LOGGER.err(e.getMessage());
        }
    }

    @Override
    public void run() {
        String line;

        try {
            while ((line = this.reader.readLine()) != null) {
                if (this.print) Logger.LOGGER.info(line);

                if(this.writer == null) continue;

                try {
                    this.writer.write(line + "\n");
                } catch (IOException e) {
                    Logger.LOGGER.err(e.getMessage());
                }
            }
        } catch (IOException e) {
            Logger.LOGGER.err(e.getMessage());

            this.interrupt();
        }

        if (this.writer == null) return;

        try {
            this.writer.close();
            this.inputStreamReader.close();
        } catch (IOException e) {
            Logger.LOGGER.err(e.getMessage());
        }
    }

    public boolean isPrint() {
        return this.print;
    }

    public void setPrint(boolean print) {
        this.print = print;
    }
}
