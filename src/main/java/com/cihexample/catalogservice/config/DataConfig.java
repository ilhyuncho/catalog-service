package com.cihexample.catalogservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

@Configuration
@EnableJdbcAuditing // 지속성 엔티티에 대한 감사를 활성화
public class DataConfig {
    // JDBC 관련 설정을 이 클래스에 모아 놓음
}
