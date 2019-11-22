package com.horse.mybatisGeneral;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @crea : Created by intelliJ IDEA 17.2
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 09 26 2017 11:04
 * @desc :
 */
public class MybatisGeneral {

    public static void main(String[] args) throws Exception {

        List<String> warnings = new ArrayList<String>();
        File configFile = new File(MybatisGeneral.class.getResource("/generatorConfig_windows.xml").getFile());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
