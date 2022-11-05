package com.application.mapper;

import com.application.model.StyleConfig;
import com.application.model.Style;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StyleMapper {

    public List<StyleConfig> map(Map<String, String> source) {
        List<StyleConfig> target = new ArrayList<>();
        source.forEach((k,v) -> target.add(mapSingle(k,v)));
        return target;
    }

    private StyleConfig mapSingle(String key, String value) {
        StyleConfig target = new StyleConfig();
        target.setValue(value);
        target.setStyle(Style.valueOf(mapStyleEnum(key)));
        return target;
    }

    private String mapStyleEnum(String value) {
        String result = value.toUpperCase();
        result = result.replace("-", "_");
        return result;
    }
}
