//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.api.common.utils;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;

public final class PathUtils {
    private PathUtils() {
    }

    public static String getPathString(String fileName) {
        if (fileName == null) {
            throw new NullPointerException("文件名字不能为空");
        } else {
            URL path = Thread.currentThread().getContextClassLoader().getResource(fileName);
            if (path != null && path.getPath().contains(".jar!")) {
                return fileName;
            } else {
                return path == null ? "" : path.getPath();
            }
        }
    }

    public static Path getPath(String fileName) {
        File file = new File(getPathString(fileName));
        return file.toPath();
    }
}
