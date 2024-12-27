package com.akatsuki.nes.framework.ui.gamegallery;

import java.io.File;
import java.util.ArrayList;

import com.akatsuki.nes.framework.utils.annotations.Column;
import com.akatsuki.nes.framework.utils.annotations.ObjectFromOtherTable;
import com.akatsuki.nes.framework.utils.annotations.Table;

@Table
public class ZipRomFile {

    @Column(isPrimaryKey = true)
    public long _id;

    @Column
    public String hash;

    @Column
    public String path;

    @ObjectFromOtherTable(columnName = "zipfile_id")
    public ArrayList<GameDescription> games = new ArrayList<>();

    public ZipRomFile() {
    }

    public static String computeZipHash(File zipFile) {
        return zipFile.getAbsolutePath().concat("-" + zipFile.length()).hashCode() + "";
    }
}
