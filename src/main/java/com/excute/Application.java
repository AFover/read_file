package com.excute;

import com.utlis.Utils;

public class Application {
    public static void main(String[] args) {
        String path="D:/data/nb.xlsx";
        String type="NB";
        Utils utils=new Utils();
        utils.readFile(path,type);

    }
}
