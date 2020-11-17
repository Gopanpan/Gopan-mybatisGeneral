package com.horse.mybatisGeneral;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>   time  11/09/2020 11:22  星期五 【dd/MM/YYYY HH:mm】 </p>
 * <p>  email  15923508369@163.com </p>
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public class MybatisGeneral {

    public static void main(String[] args) throws Exception {

        List<String> warnings = new ArrayList<String>();
        //File configFile = new File(MybatisGeneral.class.getResource("/funit-exercise.xml").getFile());
        File configFile = new File(MybatisGeneral.class.getResource("/unite-staff.xml").getFile());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
