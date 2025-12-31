package com.catodev.literalura.util;

public enum Languages {
    EN,
    ES,
    FR,
    IT,
    JA,
    KO,
    KW;

    public static boolean contains(String code) {
        try {
            valueOf(code.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}