package com.horse.mybatisGeneral;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * @create: Created by intelliJIDEA
 * @author: Gopan
 * @e-mail: 15923508369@163.com
 * @gmdate: 10/09/2020 16:59  星期四 (dd/MM/YYYY HH:mm)
 * @sidesc:
 */
public class CustomizeBatisPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        // 添加domain的import
        topLevelClass.addImportedType("lombok.Data");
        // 添加domain的注解
        topLevelClass.addAnnotation("@Data");
        return true;
    }

    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                       IntrospectedTable introspectedTable, ModelClassType modelClassType) {

        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {


        interfaze.addJavaDocLine("/**");
        interfaze.addJavaDocLine(" * This class corresponds to the database table "+ introspectedTable.getFullyQualifiedTable());
        interfaze.addJavaDocLine(" * ");
        interfaze.addJavaDocLine(" * <p> create  created by horse-generator</p>  ");
        interfaze.addJavaDocLine(" * <p>   time  " + DateUtils.getDateString() +  "  (dd/MM/YYYY HH:mm)</p>  ");
        interfaze.addJavaDocLine(" * <p>  email  15923508369@163.com</p>  ");
        interfaze.addJavaDocLine(" * @author   Gopan");
        interfaze.addJavaDocLine(" * @version  1.0.0");
        interfaze.addJavaDocLine(" */");
        return true;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                              IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        // 不生成getter
        return false;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                              IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        // 不生成setter
        return false;
    }


}
