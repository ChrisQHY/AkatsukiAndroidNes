package com.akatsuki.nes;

import com.akatsuki.nes.framework.base.JniBridge;

public class Core extends JniBridge {
    private static Core instance = new Core();

    static {
        System.loadLibrary("nes");
    }

    private Core() {
    }

    public static Core getInstance() {
        return instance;
    }

}
