package com.my.app;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;

public class MainApplication {
    private static String driver = "com.mysql.cj.jdbc.Driver";

    public static void main(String[] args) throws Exception {
        Class.forName(driver);
        FastAutoGenerator.create(
                        "jdbc:mysql://127.0.0.1:3306/my_app?serverTimezone=GMT%2B8&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true",
                        "root", "2023")
                .globalConfig(
                        builder -> {
                            builder
                                    .disableOpenDir()
                                    .author("ms") // 设置作者
                                    .fileOverride() // 覆盖已生成文件
                                    .outputDir("gen/src/main/java"); // 指定输出目录
                        })
                .packageConfig(
                        builder -> {
                            // 设置父包名
                            builder.parent("com.my.app.mysql");
                            builder.moduleName("my_app");
                        })
                .strategyConfig(
                        builder -> {
                            // 数据库表
                            builder.addInclude("m_user");
                            builder.entityBuilder().idType(IdType.ASSIGN_ID);
                            builder.entityBuilder().logicDeleteColumnName("deleted");
                            builder.entityBuilder().logicDeletePropertyName("deleted");
                            builder.entityBuilder().versionColumnName("version");
                            builder.entityBuilder().versionPropertyName("version");
                            builder.entityBuilder().enableChainModel();
                            builder.entityBuilder().addTableFills(new Column("create_time", FieldFill.INSERT));
                            builder.entityBuilder().addTableFills(new Property("update_time", FieldFill.INSERT_UPDATE));
                            builder.entityBuilder().enableFileOverride();
                        })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
