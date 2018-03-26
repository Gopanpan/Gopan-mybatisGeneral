package bing.Pan.mybatisGeneral;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;

import org.mybatis.generator.api.dom.xml.XmlElement;

import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;



/**
 * @crea : Created by intelliJ IDEA 17.2
 * @auth : bing.Pan
 * @mail : 15923508369@163.com
 * @date : 03 24 2018 19:04
 * @desc :
 */
public class CustomizeGenerator implements CommentGenerator {

    /** The properties. */
    private Properties properties;

    /** The suppress date. */
    private boolean suppressDate;

    /** The suppress all comments. */
    private boolean suppressAllComments;

    /** The addition of table remark's comments.
     * If suppressAllComments is true, this option is ignored*/
    private boolean addRemarkComments;

    private boolean suppressGetSetComments;

    private SimpleDateFormat dateFormat;

    /**
     * Instantiates a new default comment generator.
     */
    public CustomizeGenerator() {
        super();
        properties = new Properties();
        suppressDate = false;
        suppressAllComments = false;
        addRemarkComments = false;
        suppressGetSetComments = true;
    }


    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        // add no file level comments by default
    }

    /**
     * Adds a suitable comment to warn users that the element was generated, and when it was generated.
     *
     * @param xmlElement
     *            the xml element
     */
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
        this.properties.putAll(properties);

        suppressDate = isTrue(properties
                .getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));

        suppressAllComments = isTrue(properties
                .getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));

        addRemarkComments = isTrue(properties
                .getProperty(PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS));

        String dateFormatString = properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_DATE_FORMAT);
        if (StringUtility.stringHasValue(dateFormatString)) {
            dateFormat = new SimpleDateFormat(dateFormatString);
        }
    }

    /**
     * This method adds the custom javadoc tag for. You may do nothing if you do not wish to include the Javadoc tag -
     * however, if you do not include the Javadoc tag then the Java merge capability of the eclipse plugin will break.
     *
     * @param javaElement
     *            the java element
     * @param markAsDoNotDelete
     *            the mark as do not delete
     */
    protected void addJavadocTag(JavaElement javaElement,
                                 boolean markAsDoNotDelete) {
        javaElement.addJavaDocLine(" *");
        StringBuilder sb = new StringBuilder();
        sb.append(" * ");
        sb.append("@ author : bing.Pan");
        if (markAsDoNotDelete) {
            sb.append(" do_not_delete_during_merge");
        }
        String s = getDateString();
        if (s != null) {
            sb.append(' ');
            sb.append(s);
        }
        javaElement.addJavaDocLine(sb.toString());
    }

    /**
     * This method returns a formated date string to include in the Javadoc tag
     * and XML comments. You may return null if you do not want the date in
     * these documentation elements.
     *
     * @return a string representing the current timestamp, or null
     */
    protected String getDateString() {
        if (suppressDate) {
            return null;
        } else if (dateFormat != null) {
            return dateFormat.format(new Date());
        } else {
            return new Date().toString();
        }
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
    public void addModelClassComment(TopLevelClass topLevelClass,
                                     IntrospectedTable introspectedTable) {
        if (suppressAllComments  || !addRemarkComments) {
            return;
        }
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" *@create : Created by intelliJ IDEA");
        topLevelClass.addJavaDocLine(" *@author : bing.Pan");
        topLevelClass.addJavaDocLine(" *@e-mail : 15923508369@163.com");
        topLevelClass.addJavaDocLine(" *@gmdate : " + getDateString());
        topLevelClass.addJavaDocLine(" *@sidesc : This class corresponds to the database table "+
                introspectedTable.getFullyQualifiedTable());
        topLevelClass.addJavaDocLine(" */");
    }


    public void addEnumComment(InnerEnum innerEnum,
                               IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        innerEnum.addJavaDocLine("/**"); 
        innerEnum
                .addJavaDocLine(" * This enum was generated by MyBatis Generator."); 

        sb.append(" * This enum corresponds to the database table "); 
        sb.append(introspectedTable.getFullyQualifiedTable());
        innerEnum.addJavaDocLine(sb.toString());

        addJavadocTag(innerEnum, false);

        innerEnum.addJavaDocLine(" */"); 
    }

    
    public void addFieldComment(Field field,
                                IntrospectedTable introspectedTable,
                                IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }
        String remarks = introspectedColumn.getRemarks();

        StringBuilder sb = new StringBuilder();
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append('.');
        sb.append(introspectedColumn.getActualColumnName() );

        field.addJavaDocLine("/**");
        field.addJavaDocLine(" *@comment : " + remarks);
        field.addJavaDocLine(" *@sidesc  : " + sb.toString());

        field.addJavaDocLine(" */");
    }


    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        field.addJavaDocLine("/**"); 
        field
                .addJavaDocLine(" * This field was generated by MyBatis Generator."); 

        sb.append(" * This field corresponds to the database table "); 
        sb.append(introspectedTable.getFullyQualifiedTable());
        field.addJavaDocLine(sb.toString());

        addJavadocTag(field, false);

        field.addJavaDocLine(" */"); 
    }


    public void addGeneralMethodComment(Method method,
                                        IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }

        StringBuilder sb = new StringBuilder();


        sb.append("This method corresponds to the database table ");
        sb.append(introspectedTable.getFullyQualifiedTable());

        method.addJavaDocLine("/**");
        method.addJavaDocLine(" *@create  : Created by intelliJ IDEA");
        method.addJavaDocLine(" *@author  : bing.Pan");
        method.addJavaDocLine(" *@e-mail  : 15923508369@163.com");
        method.addJavaDocLine(" *@gmdate  : " + getDateString());
        method.addJavaDocLine(" *@sidesc  : " + sb.toString());

        method.addJavaDocLine(" */");
    }


    public void addGetterComment(Method method,
                                 IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }

        if(suppressGetSetComments){
            return;
        }

        StringBuilder sb = new StringBuilder();

        method.addJavaDocLine("/**"); 
        method
                .addJavaDocLine(" * This method was generated by MyBatis Generator."); 

        sb.append(" * This method returns the value of the database column "); 
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append('.');
        sb.append(introspectedColumn.getActualColumnName());
        method.addJavaDocLine(sb.toString());

        method.addJavaDocLine(" *"); 

        sb.setLength(0);
        sb.append(" * @return the value of "); 
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append('.');
        sb.append(introspectedColumn.getActualColumnName());
        method.addJavaDocLine(sb.toString());

        addJavadocTag(method, false);

        method.addJavaDocLine(" */"); 
    }


    @Override
    public void addSetterComment(Method method,
                                 IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }

        if(suppressGetSetComments){
            return;
        }
        StringBuilder sb = new StringBuilder();

        method.addJavaDocLine("/**"); 
        method
                .addJavaDocLine(" * This method was generated by MyBatis Generator."); 

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

        addJavadocTag(method, false);

        method.addJavaDocLine(" */"); 
    }


    @Override
    public void addClassComment(InnerClass innerClass,
                                IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
        if (suppressAllComments) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        innerClass.addJavaDocLine("/**");
        innerClass
                .addJavaDocLine(" * This class was generated by MyBatis Generator.");

        sb.append(" * This class corresponds to the database table ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        innerClass.addJavaDocLine(sb.toString());

        addJavadocTag(innerClass, markAsDoNotDelete);

        innerClass.addJavaDocLine(" */");
    }
}
