package ru.trainithard.pollerbot.service.component;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ColumnOutputFormatter {
    public String format(List<String> strings, int numberOfColumns) {
        Collections.sort(strings);
        String[] stringsArray = String.join(" ", strings).split(" ");

        StringBuilder sb = new StringBuilder();
        for (int rowIndex = 0; rowIndex < getNumberOfWordsInColumn(stringsArray, numberOfColumns); rowIndex++) {
            for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
                if (rowIndex + numberOfColumns * columnIndex < stringsArray.length) {
                    sb.append(String.format("%-15s", stringsArray[rowIndex + numberOfColumns * columnIndex]));
                }
            }
            sb.append("\n");
        }
        sb.insert(0, "```\n");
        sb.append("```");
        return sb.toString();
    }

    private int getNumberOfWordsInColumn(String[] keywords, int columns) {
        if (keywords.length <= columns) {
            return keywords.length;
        }
        return Double.valueOf(
                Math.ceil(keywords.length / (double) columns))
                .intValue();
    }
}
