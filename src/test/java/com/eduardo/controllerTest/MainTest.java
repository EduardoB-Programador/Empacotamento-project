package com.eduardo.controllerTest;

import com.eduardo.controller.Main;
import org.junit.jupiter.api.*;

import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainTest {

    @Test
    void manualInputTest() {
        System.out.println(Main.manualInput());
    }
}
