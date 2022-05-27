package org.pj.metaverse.workflow.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pj.metaverse.workflow.entity.vo.TaskVo;

import java.util.List;

/**
 * @author pengjie
 * @date 10:35 2022/5/27
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataResult<T> {

    private Integer code;
    private String message;
    private T data;
    private Long totalCount;

    public static <T> DataResult<List<T>> successRows(Long totalCount, List<T> data) {

        return new DataResult<>(200,"查询成功",data,totalCount);
    }
}
