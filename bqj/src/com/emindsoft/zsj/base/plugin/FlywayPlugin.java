package com.emindsoft.zsj.base.plugin;

import com.jfinal.log.Logger;
import com.jfinal.plugin.IPlugin;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationVersion;

/**
 * Created by zfk on 15-4-9.
 * 数据库自动升级插件
 */
public class FlywayPlugin implements IPlugin {

    private Logger logger = com.jfinal.log.Logger.getLogger(getClass());

    private String migrationFilesLocation = "classpath:sql/";
    
    private String url = "";
    private String user = "";
    private String password = "";

    public FlywayPlugin(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public boolean start() {
        Flyway flyway = new Flyway();
        flyway.setValidateOnMigrate(false);
        flyway.setDataSource(url, user, password);
        flyway.setLocations(migrationFilesLocation);
        flyway.setBaselineOnMigrate(true);
        flyway.setBaselineVersion(MigrationVersion.fromVersion("0.0.0.0"));
        flyway.migrate();
        logger.info("flyway start!");
        return true;
    }

    @Override
    public boolean stop() {
//        Flyway flyway = new Flyway();
//        flyway.setDataSource(url, user, password);
//        flyway.setLocations(migrationFilesLocation);
//        flyway.clean();
        logger.info("flyway stop!");
        return true;
    }
}