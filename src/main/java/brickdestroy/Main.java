package brickdestroy;

import java.awt.EventQueue;

import brickdestroy.main.GameFrame;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new GameFrame());
    }

}
