package com.gift.baseinfo.main.enums;

/**
 * @author liuch
 * @title: BillStateCodes
 * @description: 文件类型映射字典
 * @date 2021/8/31 19:08
 */
public enum FileTypeEnums {
    /**
     * JPG
     */
    JPG("JPG","data:image/jpeg;base64,"),
    /**
     * JPEG
     */
    JPEG("JPEG","data:image/jpeg;base64,"),
    /**
     * PNG
     */
    PNG("PNG","data:image/png;base64,"),
    /**
     * GIF
     */
    GIF("GIF","data:image/gif;base64,"),
    /**
     * ICO
     */
    ICO("ICO","  data:image/x-icon;base64,"),
    /**
     * BMP
     */
    BMP("BMP","data:image/bmp;base64,"),
    /**
     * SVG
     */
    SVG("SVG","data:image/svg+xml;base64,"),
    /**
     * TXT
     */
    TXT("TXT","data:text/plain;base64,"),
    /**
     * DOC
     */
    DOC("DOC","data:application/msword;base64,"),
    /**
     * DOCX
     */
    DOCX("DOCX","data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64,"),
    /**
     * XLS
     */
    XLS("XLS","data:application/vnd.ms-excel;base64,"),
    /**
     * XLSX
     */
    XLSX("XLSX","data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64,"),
    /**
     * PPT
     */
    PPT("PPT","data:application/vnd.ms-powerpoint;base64,"),
    /**
     * PPTX
     */
    PPTX("PPTX","data:application/vnd.openxmlformats-officedocument.presentationml.presentation;base64,"),
    /**
     * PDF
     */
    PDF("PDF","data:application/pdf;base64,")
    ;

    private String extend;
    private String dataType;

    FileTypeEnums(String extend, String dataType) {
        this.extend = extend;
        this.dataType = dataType;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     * @Author liuch
     * @Description 获取文件类型base64头
     * @Date 2021/8/31 19:20
     * @param [extend]
     * @return com.hd.basic.enums.FileTypeEnums
     */
    public static FileTypeEnums getFileType(String extend){
        for (FileTypeEnums type : FileTypeEnums.values()) {
            if(type.getExtend().equals(extend.toUpperCase())){
                return type;
            }
        }
        return null;
    }
}
