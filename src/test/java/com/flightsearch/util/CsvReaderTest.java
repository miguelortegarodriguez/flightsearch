package com.flightsearch.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class CsvReaderTest {

    @Test
    public void read_csv_should_return_a_stream_of_mapped_data() {
        Stream<TestData> result = CsvReader.readCsv("test.csv", this::mapLineToTestData);
        assertTrue(result.anyMatch(t -> t.col1.equals("foo") && t.col2.equals("bar") && t.col3.equals(1)));
        result = CsvReader.readCsv("test.csv", this::mapLineToTestData);
        assertTrue(result.anyMatch(t -> t.col1.equals("lorem") && t.col2.equals("ipsum") && t.col3.equals(2)));
    }

    private TestData mapLineToTestData(String[] csvLineData) {
        return new TestData(csvLineData[0], csvLineData[1], Integer.valueOf(csvLineData[2]));
    }

    public static class TestData {

        public final String col1;
        public final String col2;
        public final Integer col3;

        public TestData(String col1, String col2, Integer col3) {
            this.col1 = col1;
            this.col2 = col2;
            this.col3 = col3;
        }
    }
}
