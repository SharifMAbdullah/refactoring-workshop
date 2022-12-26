package RefactorPractice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlaintextToHtmlConverter {
    String source;
    int i;
    List<String> result;
    List<String> convertedLine;
    String characterToConvert;

    public String toHtml() throws Exception {
        String text = read();
        String htmlLines = basicHtmlEncode(text);
        return htmlLines;
    }

    protected String read() throws IOException {
        String fileName = "";
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    private String basicHtmlEncode(String source) {
        this.source = source;
        i = 0;
        result = new ArrayList<>();
        convertedLine = new ArrayList<>();
        characterToConvert = pickCharAndAdvance();

        while (i <= this.source.length()) {
            characterConversion();

            if (i >= source.length()) break;
            characterToConvert = pickCharAndAdvance();
        }
        addANewLine();
        String finalResult = String.join("<br />", result);
        return finalResult;
    }

    private String pickCharAndAdvance() {
        char c = source.charAt(i);
        i += 1;
        return String.valueOf(c);
    }

    private void addANewLine() {
        String line = String.join("", convertedLine);
        result.add(line);
        convertedLine = new ArrayList<>();
    }

    private void characterConversion(){
        switch (characterToConvert) {
            case "<":
                convertedLine.add("&lt;");
                break;
            case ">":
                convertedLine.add("&gt;");
                break;
            case "&":
                convertedLine.add("&amp;");
                break;
            case "\n":
                addANewLine();
                break;
            default:
                convertedLine.add(characterToConvert);;
        }
    }

}