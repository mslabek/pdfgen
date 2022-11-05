package com.application.config;

import com.lowagie.text.pdf.BaseFont;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class PdfConfig {

    @Bean
    public BaseFont getDefaultBaseFont() throws IOException {
        return BaseFont.createFont("noto-serif/NotoSerif-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    }

}
