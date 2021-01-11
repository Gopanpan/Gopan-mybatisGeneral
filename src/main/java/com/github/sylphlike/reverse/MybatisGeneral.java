package com.github.sylphlike.reverse;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>   time  11/09/2020 11:22  星期五 (dd/MM/YYYY HH:mm)
 * <p>  email  15923508369@163.com
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public class MybatisGeneral {

    public static void main(String[] args) throws Exception {

        List<String> warnings = new ArrayList<String>();

        //File configFile = new File(MybatisGeneral.class.getResource("/funit-exercise.xml").getFile());
        //File configFile = new File(MybatisGeneral.class.getResource("/funit-interview.xml").getFile());
        //File configFile = new File(MybatisGeneral.class.getResource("/unite-staff.xml").getFile());
        File configFile = new File(MybatisGeneral.class.getResource("/unite-demo.xml").getFile());

        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
