package org.pj.metaverse.codegen;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.stereotype.Indexed;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author pengjie
 * @date 10:10 2022/5/26
 **/
@Data
public class BaseEntity implements Serializable {

    private String id;

    private String tenantId;

    private Integer sort;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

    private String deleted;

    private String orderBy;
}
