package com.john.common.file;

/**
 * Copyright: 河北省通信建设有限公司网络分公司<p>
 * Author: John<p>
 * Description: <p>
 * Date: 2019/11/21 14:37<p>
 * Updater：<p>
 */
public enum FileType {
    MEDIA_MP4("mp4"), MEDIA_AVI("avi"), MEDIA_RMVB("rmvb"), MEDIA_3GP("3gp"), //多媒体
    XLS("xls"), XLSX("xlsx"), DOC("doc"), DOCX("docx"), PPT("ppt"), PPTX("pptx"), PDF("pdf"), //office
    TXT("txt"),//txt
    ZIP("zip"),RAR("rar"),RAR5("rar5"),//压缩包
    NON("");//未完善的文件类型
    String type;

    FileType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static FileType getFileTypeByPath(String path) {
        path=path.toLowerCase();
        for (FileType value : FileType.values()) {
            if (path.endsWith(value.type)) {
                return value;
            }
        }
        return NON;
    }

    public static boolean isVideo(FileType fileType) {
        if (fileType == FileType.MEDIA_MP4 || fileType == FileType.MEDIA_RMVB ||
                fileType == FileType.MEDIA_3GP || fileType == FileType.MEDIA_AVI) {
            return true;
        }
        return false;
    }
}

