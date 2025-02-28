package com.example.demo;

import com.example.demo.OcrService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ocr")
@RequiredArgsConstructor
public class OcrController {
    private final OcrService ocrService;

    @PostMapping("/recognize")
    public ResponseEntity<?> recognizeText(@RequestParam("file") MultipartFile file) {
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("text", ocrService.recognizeText(file));
            response.put("filename", file.getOriginalFilename());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "OCR处理失败: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
