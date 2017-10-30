package com.melot;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: TestGenerateMyBatis3
 * @author: shengjian
 * @date: 2017/10/30
 * @copyright: Copyright (c)2017
 * @company: melot
 * <p>
 * Modification History:
 * Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2017/10/30           shengjian     1.0
 */
public class TestGenerateMyBatis3 {

    @Test()
    public void testGenerateMyBatis3WithSongjmConfig() throws Exception {
        List<String> warnings = new ArrayList<String>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(this.getClass().getClassLoader().getResourceAsStream("generatorConfig-songjm.xml"));

        DefaultShellCallback shellCallback = new DefaultShellCallback(true);

        try {
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, shellCallback, warnings);
            myBatisGenerator.generate(null, null, null, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("============= warnings =================");
        for (String string : warnings) {
            System.out.println(string);
        }
        System.out.println("============= warnings =================");
    }
}
