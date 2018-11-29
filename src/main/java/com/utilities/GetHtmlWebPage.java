package com.utilities;

import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GetHtmlWebPage {

    public static String Doctype() throws IOException {
        
        String fileName = System.getProperty("user.dir")+ "\\target\\surefire-reports\\emailable-report.html";

        Document doc = Jsoup.parse(new File(fileName), "utf-8");

        return doc.toString();
    }
}
