基于NostalgiaLite工程抽取的Nes模拟器，使用Gradle8.9编译。修复了FEEUX模块编译出错的问题。
由于Android高版本屏蔽掉了SD卡读取的权限，所以这个工程使用asset进行保存nes游戏Rom。
首先在com.akatsuki.nes.NesApplication下进行了asset/nes目录下的游戏复制，然后在com.akatsuki.nes.framework.utils.SDCardUtil的getAllStorageLocations函数增加了应用自身的文件系统的路径。
如果找不到游戏的话修改一下com.akatsuki.nes.framework.utils.SDCardUtil下的getAllStorageLocations函数。在函数内部添加一下游戏目录的绝对路径。例如本应用就添加了sdcards.add("/data/user/0/com.akatsuki.nes/files");
后续可能会继续研究这个抽取的工程，然后在此之上进行优化。
