package org.pj.metaverse.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回体
 * @author pengjie
 * @date 2022/6/18 14:47
 */
@ApiModel(value = "返回结果")
@Data
@AllArgsConstructor
public class DataResult<T> implements Serializable {

    @ApiModelProperty(value = "返回代码 200-成功")
    private Integer code;
    @ApiModelProperty(value = "返回信息(操作成功/失败)")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private T data;

    public static Integer SUCCESS_CODE = 200;
    public static String SUCCESS_MESSAGE = "操作成功";

    public static Integer ERROR_CODE = 500;
    public static String ERROR_MESSAGE = "服务器开小差了";

    public DataResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public DataResult(DataResult<Void> dataResult, T t) {
        this.code = dataResult.getCode();
        this.message = dataResult.getMessage();
        this.data = t;
    }

    public static DataResult<Void> error(){
        return new DataResult<>(ERROR_CODE,ERROR_MESSAGE);
    }
    public static DataResult<Void> success(){
        return new DataResult<>(SUCCESS_CODE,SUCCESS_MESSAGE);
    }


}
