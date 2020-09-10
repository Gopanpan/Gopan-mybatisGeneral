package com.horse.mybatisGeneral;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Set;


/**
 * <p> create  Pan Pan worked hard to achieve </p>
 * <p>   time  10/09/2020 18:19  星期四 (dd/MM/YYYY HH:mm) </p>
 * <p>  email  15923508369@163.com </p>
 *
 * @author Gopan
 * @version 1.0.0
 */
public class CustomizeGenerator implements CommentGenerator {



    private Properties properties;

    /** 是否去除自动生成的注解 */
    private boolean suppressAllComments;

   /** 是否添加实体类上的注释，默认 true */
    private boolean addRemarkComments;

    /** 是否添加实体类 get set方法注释 默认false  */
    private boolean suppressGetSetComments;


    public CustomizeGenerator() {
        super();
        properties = new Properties();
        suppressAllComments = false;
        addRemarkComments = true;
        suppressGetSetComments = false;
    }






    @Override
    public void addModelClassComment(TopLevelClass topLevelClass,
                                     IntrospectedTable introspectedTable) {
        if (suppressAllComments  || !addRemarkComments) {
            return;
        }
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * This class corresponds to the database table "+ introspectedTable.getFullyQualifiedTable());
        topLevelClass.addJavaDocLine(" * ");
        topLevelClass.addJavaDocLine(" * <p> create  created by horse-generator</p>  ");
        topLevelClass.addJavaDocLine(" * <p>   time  " + DateUtils.getDateString() +  "  (dd/MM/YYYY HH:mm)</p>  ");
        topLevelClass.addJavaDocLine(" * <p>  email  15923508369@163.com</p>  ");
        topLevelClass.addJavaDocLine(" * @author   Gopan");
        topLevelClass.addJavaDocLine(" * @version  1.0.0");
        topLevelClass.addJavaDocLine(" */");
    }



    /**
     * 此方法将数据库字段注释映射到实体对象的javaDoc注解上
     * @param field
     * @param introspectedTable
     * @param introspectedColumn
     */
    @Override
    public void addFieldComment(Field field,
                                IntrospectedTable introspectedTable,
                                IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }
        String remarks = introspectedColumn.getRemarks();

        StringBuilder builder = new StringBuilder();
        builder.append(introspectedTable.getFullyQualifiedTable());
        builder.append('.');
        builder.append(introspectedColumn.getActualColumnName() );

        field.addJavaDocLine("/** " +  remarks +  "  "+  builder.toString() + " */");

    }


    /**
     * 此方法生成接口方法javaDoc注释
     * @param method
     * @param introspectedTable
     */
    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * ");
        method.addJavaDocLine(" * <p>   time  " + DateUtils.getDateString() +  "  (dd/MM/YYYY HH:mm)</p>  ");
        method.addJavaDocLine(" * <p>  email  15923508369@163.com</p>  ");
        method.addJavaDocLine(" * @author  Gopan");
        method.addJavaDocLine(" */");
    }


    @Override
    public void addGetterComment(Method method,
                                 IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }

        if(!suppressGetSetComments){
            return;
        }

        StringBuilder builder = new StringBuilder();

        method.addJavaDocLine("/**"); 
        method.addJavaDocLine(" * This method was generated by MyBatis Generator.");

        builder.append(" * This method returns the value of the database column ");
        builder.append(introspectedTable.getFullyQualifiedTable());
        builder.append('.');
        builder.append(introspectedColumn.getActualColumnName());
        method.addJavaDocLine(builder.toString());

        method.addJavaDocLine(" *"); 
        builder.setLength(0);
        builder.append(" * @return the value of ");
        builder.append(introspectedTable.getFullyQualifiedTable());
        builder.append('.');
        builder.append(introspectedColumn.getActualColumnName());
        method.addJavaDocLine(builder.toString());
        method.addJavaDocLine(" */");
    }


    @Override
    public void addSetterComment(Method method,
                                 IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }

        if(!suppressGetSetComments){
            return;
        }
        StringBuilder sb = new StringBuilder();

        method.addJavaDocLine("/**"); 
        method.addJavaDocLine(" * This method was generated by MyBatis Generator.");

        sb.append(" * This method sets the value of the database column "); 
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append('.');
        sb.append(introspectedColumn.getActualColumnName());
        method.addJavaDocLine(sb.toString());

        method.addJavaDocLine(" *"); 

        Parameter parm = method.getParameters().get(0);
        sb.setLength(0);
        sb.append(" * @param "); 
        sb.append(parm.getName());
        sb.append(" the value for "); 
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append('.');
        sb.append(introspectedColumn.getActualColumnName());
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine(" */");
    }


    @Override
    public void addClassComment(InnerClass innerClass,
                                IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
        addClassComment(innerClass,introspectedTable);
    }


    @Override
    public void addClassComment(InnerClass innerClass,
                                IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        innerClass.addJavaDocLine("/**");
        innerClass .addJavaDocLine(" * This class was generated by MyBatis Generator.");
        sb.append(" * This class corresponds to the database table ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        innerClass.addJavaDocLine(sb.toString());
        innerClass.addJavaDocLine(" */");
    }



    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        // add no file level comments by default
    }

    @Override
    public void addComment(XmlElement xmlElement) {
        if (suppressAllComments) {
            return;
        }

    }

    @Override
    public void addRootComment(XmlElement rootElement) {
        // add no document level comments by default
    }
    @Override
    public void addConfigurationProperties(Properties properties) {

    }


    @Override
    public void addEnumComment(InnerEnum innerEnum,
                               IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        innerEnum.addJavaDocLine("/**");
        innerEnum.addJavaDocLine(" * This enum was generated by MyBatis Generator.");

        sb.append(" * This enum corresponds to the database table ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        innerEnum.addJavaDocLine(sb.toString());
        innerEnum.addJavaDocLine(" */");
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();

        field.addJavaDocLine("/**");
        field.addJavaDocLine(" * This field was generated by MyBatis Generator.");
        sb.append(" * This field corresponds to the database table ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        field.addJavaDocLine(sb.toString());
        field.addJavaDocLine(" */");
    }






    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {

    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> set) {

    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {

    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> set) {

    }

    @Override
    public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {

    }






}
