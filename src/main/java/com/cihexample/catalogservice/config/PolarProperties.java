package com.cihexample.catalogservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "polar")  // @ConfigurationProperties 빈을 통해 사죵자 정의 속성을 정의했기 때문에
                                // 새로고침 이벤트인 RefreshScopeRefreshEvent가 발생하면 기본적으로 자동 적용되어
                                // 최신 설정 값을 다시 로드 한다
                                // http://localhost:9001/actuator/refresh 가 post로 호출 되면 ( 사용자 가 호출 )
public class PolarProperties {
    private String greeting;

    public String getGreeting(){
        return this.greeting;
    }
    public void setGreeting(String greeting){
        this.greeting = greeting;
    }

}
