package com.eduardo.controllerTest;

import com.eduardo.controller.MyFileReader;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyFileReaderTest {

    @Test
    void getFromFileTest() {
        Map<String, Object> valuesFromFile = MyFileReader.getFromFile();
        assertNotNull(valuesFromFile);

        System.out.println(valuesFromFile);

        System.out.println(valuesFromFile.get("truck"));
        assertNotNull(valuesFromFile.get("truck"));

        System.out.println(valuesFromFile.get("warehouse"));
        assertNotNull(valuesFromFile.get("warehouse"));

        System.out.println(valuesFromFile.get("containers"));
        assertNotNull(valuesFromFile.get("containers"));
    }
}
