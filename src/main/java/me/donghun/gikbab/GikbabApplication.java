package me.donghun.gikbab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;


@EnableConfigurationProperties(StorageProperties.class)
//이는 아래 세개의 에노테이션의 합으로 볼 수 있다.
@SpringBootApplication

//@SpringBootConfiguration
/*
빈을 두 번 등록
1. ComponentScan
2. EnableAutoConfiguration 얘를 통해 읽은 Bean 등록
(따라서 AutoConfiguration에 있는 똑같은 빈을 직접 등록한다면 직접 등록한 것이 무시됨)
(이를 해결하려면 AutoConfiguration의 빈에 @ConditionalOnMissingBean 추가해야함)
(이런 에노테이션이 있기 때문에 customizing이 가능한 것. 오버라이딩 느낌으로)
 */
//@EnableAutoConfiguration // (생략 가능) spring.factories
//@ComponentScan
/*
ComponentScan은 Component 에노테이션이 붙은 클래스들을 스캔하여 빈으로 등록
스캔 범위는 ComponentScan이 붙어 있는 클래스를 포함하는 패키지 하위에 있는 모든 클래스
 */
public class GikbabApplication {

    public static void main(String[] args) {
        SpringApplication.run(GikbabApplication.class, args);
    }

}
