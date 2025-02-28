package com.example.demo;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class OcrService {
    
    private final ITesseract tesseract;

    public OcrService() {
        this.tesseract = new Tesseract();
        // 设置jna库路径（根据实际安装路径调整）
        System.setProperty("jna.library.path", "/usr/local/Cellar/tesseract/5.5.0/lib");
        // 设置训练数据路径（需下载语言包）
        this.tesseract.setDatapath("src/main/resources/tessdata");
        this.tesseract.setLanguage("chi_sim+eng"); // 中英文识别
    }

    public String recognizeText(MultipartFile file) throws Exception {
        File tempFile = File.createTempFile("ocr-", file.getOriginalFilename());
        file.transferTo(tempFile);
        
        try {
            return tesseract.doOCR(tempFile);
        } finally {
            tempFile.delete();
        }
    }
}
