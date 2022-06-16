package org.pj.metaverse.entity;

import lombok.Data;

/**
 * @author pengjie
 * @date 10:24 2022/5/27
 **/
@Data
public class BasePageParam {

    private String orderBy;

    private String orderSort;

    private Integer pageNo;

    private Integer pageSize;
}
