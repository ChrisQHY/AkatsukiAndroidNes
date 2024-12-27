package com.akatsuki.nes

import android.app.Application
import com.akatsuki.nes.framework.base.EmulatorHolder
import java.io.File
import java.io.FileOutputStream

class NesApplication:Application() {

    override fun onCreate() {
        super.onCreate()

        assets.list("nes")?.let {nesFiles ->
            for(nesFile in nesFiles) {
                val inputStream = assets.open("nes/$nesFile")
                val outFile = File(filesDir, nesFile)
                if(outFile.exists()) {
                    inputStream.close()
                    continue
                }
                val outputStream = FileOutputStream(outFile)
                val buffer = ByteArray(1024)
                var read:Int
                while(inputStream.read(buffer).also {read = it} != -1) {
                    outputStream.write(buffer, 0, read)
                }
                outputStream.flush()
                outputStream.close()
                inputStream.close()
            }
        }

        EmulatorHolder.setEmulatorClass(NesEmulator::class.java)
    }
}