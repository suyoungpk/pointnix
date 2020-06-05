package net.ezens.pointnix.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "net.ezens.pointnix")
public class DatabaseConfig {

}
