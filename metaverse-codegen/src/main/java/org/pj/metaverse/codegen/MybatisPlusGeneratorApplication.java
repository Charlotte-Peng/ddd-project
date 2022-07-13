package org.pj.metaverse.codegen;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;

import java.io.File;
import java.sql.*;
import java.util.*;


/**
 * @author admin
 */
public class MybatisPlusGeneratorApplication {
    private static final String DRIVER = "jdbc";
    private static final String TYPE = "mysql";
    private static final String ADDRESS = "10.8.8.4";
    private static final String PORT = "3306";
    private static final String ARG = "useUnicode=true&useSSL=false&characterEncoding=utf8";
    private static final String PACKAGE_ADDRESS = "org.pj.";
    public static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    public static final String USER_NAME = "root";

    public static void main(String[] args) {
        MybatisPlusGeneratorApplication application = new MybatisPlusGeneratorApplication();
        application.run();
    }

    private void run() {
        String type = scanner("0为生成所有表，1为输入单表");
        String schema = scanner("输入库名");
//        String schema = "metaverse_user";
        this.start(type, schema);

    }

    private void start(String type, String schema) {
        // 初始化数据连接
        DataSourceConfig dataSourceConfig = initDataSourceConfig(schema);
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator(dataSourceConfig);
        String projectName = scanner("请输入项目名");
        // 初始化全局配置
        GlobalConfig globalConfig = initGlobalConfig(projectName);
        mpg.global(globalConfig);
        // 初始化包配置
//        String packageName = scanner("模块名");
        PackageConfig packageConfig = initPackageConfig(projectName);
        mpg.packageInfo(packageConfig);
        if("0".equals(type)){
            // 策略配置
            String query = query(schema);
            StrategyConfig strategyConfig = initStrategyConfig(query.split(","));
            mpg.strategy(strategyConfig);
        }else if ("1".equals(type)){
            // 策略配置
            String query = scanner("请输入表名多个逗号隔开");
            String[] split = query.split(",");
            List<String> tablesU = new ArrayList<>();
            for (String s3 : split) {
                String trim = s3.trim();
                tablesU.add(trim);
            }
            String[] tables = tablesU.toArray(new String[0]);
            StrategyConfig strategyConfig = initStrategyConfig(tables);
            mpg.strategy(strategyConfig);
        }
        // 开始执行
        mpg.execute();
    }

    private GlobalConfig initGlobalConfig(String projectName) {
        // 设置作者
        String name = scanner("请输入作者名");
        // 全局配置
        return new GlobalConfig.Builder()
//                .fileOverride()
                // 禁止打开输出目录
                .disableOpenDir()
                .outputDir(System.getProperty("user.dir") + "/" + projectName + "/src/main/java")
                .author(name)
                .enableSwagger()
                .dateType(DateType.TIME_PACK)
                .commentDate("yyyy-MM-dd HH:mm:ss")
                .build();
    }

    private DataSourceConfig initDataSourceConfig(String schema) {
        // 数据源设置
        return new DataSourceConfig.Builder(DRIVER + ":" + TYPE + "://" + ADDRESS + ":" + PORT + "/" + schema + "?" + ARG,USER_NAME,"root")
                .dbQuery(new MySqlQuery())
                .schema(schema)
                .typeConvert(new MySqlTypeConvert())
                .keyWordsHandler(new MySqlKeyWordsHandler())
                .build();
    }

    private PackageConfig initPackageConfig(String projectName) {
        String projectPath = System.getProperty("user.dir");
        String mavenPath = "\\src\\main\\java\\";
        String srcPath = projectPath + "/" + projectName + mavenPath;
        Map<String, String> packageInfo = new HashMap<>(5);
        packageInfo.put(ConstVal.CONTROLLER, PACKAGE_ADDRESS  + ".controller");
        packageInfo.put(ConstVal.SERVICE, PACKAGE_ADDRESS  + ".service");
        packageInfo.put(ConstVal.SERVICE_IMPL, PACKAGE_ADDRESS  + ".service.impl");
        packageInfo.put(ConstVal.ENTITY, PACKAGE_ADDRESS  + ".entity");
        packageInfo.put(ConstVal.MAPPER, PACKAGE_ADDRESS  + ".mapper");
        Map<OutputFile, String> pathInfo = new HashMap<>(6);
        pathInfo.put(OutputFile.controller, srcPath + packageInfo.get(ConstVal.CONTROLLER).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(OutputFile.service, srcPath + packageInfo.get(ConstVal.SERVICE).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(OutputFile.serviceImpl, srcPath + packageInfo.get(ConstVal.SERVICE_IMPL).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(OutputFile.entity, srcPath + packageInfo.get(ConstVal.ENTITY).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(OutputFile.mapper, srcPath + packageInfo.get(ConstVal.MAPPER).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(OutputFile.mapperXml, projectName + "\\src\\main\\resources\\mapper\\");
        return new PackageConfig.Builder()
                .parent(PACKAGE_ADDRESS.substring(0,PACKAGE_ADDRESS.length()-1))
//                .moduleName(null)
                .entity("entity")
                .service("service")
                .serviceImpl("service.impl")
                .mapper("mapper")
                .xml("mapper.xml")
                .controller("controller")
                .other("other")
                .pathInfo(pathInfo)
                .build();
    }


    private StrategyConfig initStrategyConfig(String[] split) {
        return new StrategyConfig.Builder()
                // 策略
                // 开启大写命名
                .enableCapitalMode()
                // 开启跳过视图
                .enableSkipView()
                // 禁用 sql 过滤
                .disableSqlFilter()
                // 增加表匹配(内存过滤)
                .addInclude(split)
                // 实体
                .entityBuilder()
                // 父类class
                .superClass(BaseEntity.class)
                // 禁用生成 serialVersionUID
//                .disableSerialVersionUID()
                // 开启链式模型
                .enableChainModel()
                // 开启 lombok 模型
                .enableLombok()
                // 开启 Boolean 类型字段移除 is 前缀
                .enableRemoveIsPrefix()
                // 开启生成实体时生成字段注解
//                .enableTableFieldAnnotation()
                // 乐观锁字段名(数据库)
                .versionColumnName("version")
                // 乐观锁属性名(实体)
                .versionPropertyName("version")
                // 逻辑删除字段名(数据库)
                .logicDeleteColumnName("deleted")
                // 逻辑删除属性名(实体)
                .logicDeletePropertyName("deleteFlag")
                // 数据库表映射到实体的命名策略
                .naming(NamingStrategy.underline_to_camel)
                // 数据库表字段映射到实体的命名策略
                .columnNaming(NamingStrategy.underline_to_camel)
                // 添加忽略字段
//                .addIgnoreColumns("age")
                // 添加表字段填充
                .addTableFills(new Column("create_time", FieldFill.INSERT))
                // 添加表字段填充
                .addTableFills(new Property("updateTime", FieldFill.UPDATE))
                // 全局主键类型
                .idType(IdType.AUTO)
                // 格式化文件名称
                .formatFileName("%sEntity")
                .controllerBuilder()
                .enableRestStyle()
                .build();
    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.nextLine();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static String query(String schema) {
        Connection conn = null;
        Statement stmt = null;
        StringBuilder builder = new StringBuilder();
        try{
            //1.加载数据驱动
            Class.forName(DRIVER_CLASS_NAME);
            //2.获得数据库连接
            conn = DriverManager.getConnection(DRIVER + ":" + TYPE + "://" + ADDRESS + ":" + PORT + "/" + schema + "?" + ARG, USER_NAME, "root");
            //3.创建语句
            String sql = "show tables from "+schema + ";";
            //返回一个执行sql的句柄
            stmt = conn.createStatement();
            //4.执行查询
            ResultSet rs = stmt.executeQuery(sql);
            //5.遍历结果
            while(rs.next()) {
                builder.append(rs.getString("Tables_in_"+schema)).append(",");
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            //6.关闭数据库连接
            if (stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null) {
                try{
                    conn.close();
                }catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }

}
