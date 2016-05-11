package com.flightsearch.util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Stream;

public abstract class CsvReader {

    public static <T> Stream<T> readCsv(String path, Function<String[], T> mapperFunction) {
        try {
            return Files.lines(Paths.get(CsvReader.class.getClassLoader().getResource(path).toURI()))
                    .map(line -> line.split(","))
                    .map(mapperFunction);
        } catch (Exception e) {
            throw new RuntimeException("Error reading csv file " + path, e);
        }
    }

}
