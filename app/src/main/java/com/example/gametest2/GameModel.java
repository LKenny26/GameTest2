package com.example.gametest2;

import android.widget.EditText;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GameModel {
    EditText et;
    Set<String> spellCheckTable = new HashSet<>();
    File dictionary = new File("words_alpha.txt");
    Scanner scan;
}
