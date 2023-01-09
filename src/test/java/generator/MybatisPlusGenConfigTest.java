package generator;

import com.demo.shardingsearchdemo.ShardingSearchDemoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author patrick_peng
 * @description
 * @date 2023-01-09 23:05
 **/
@SpringBootTest(classes = ShardingSearchDemoApplication.class)
@Configuration
public class MybatisPlusGenConfigTest {

    @Autowired
    private MybatisPlusGenConfig mybatisPlusGenConfig;

    @Test
    public void generate() {
        mybatisPlusGenConfig.generate("cart_item");
    }
}