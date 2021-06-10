package com.share.charge.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.share.charge.mybatis.generator.mapper")
public class MyBatisConfig {
}
