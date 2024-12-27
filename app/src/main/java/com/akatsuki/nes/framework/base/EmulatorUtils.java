package com.akatsuki.nes.framework.base;

import android.content.Context;
import android.os.Environment;

import java.io.File;

import com.akatsuki.nes.framework.EmulatorException;

public class EmulatorUtils {

    public static String getBaseDir(Context context) {
        File dir = null;
        if (dir == null) {
            if(!new File(context.getFilesDir() + "/AutoSave").exists()) {
                new File(context.getFilesDir() + "/AutoSave").mkdir();
            }
            dir = new File(context.getFilesDir() + "/AutoSave");
        }
        if (dir == null || !dir.exists()) {
            throw new EmulatorException("No working directory");
        }
        return dir.getAbsolutePath();
    }
}
