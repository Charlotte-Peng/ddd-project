package org.pj.metaverse.common.config;

import de.huxhorn.sulky.ulid.ULID;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author pengjie
 * @date 15:15 2022/6/13
 **/
@Component
public class ULIDAutoImport {
    @Bean
    public ULID ulid() {
        return new ULID();
    }
}
