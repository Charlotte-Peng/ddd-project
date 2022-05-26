/**
 *
 */
package org.pj.metaverse.common.result;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.pj.metaverse.common.enums.ResponseEnum;

import java.io.Serializable;

/**
 * @author zfj
 * @version 1.0
 * @ClassName: DataResult
 * @Description: 返回数据实体
 * @date 2018年5月17日 下午5:54:26
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

    public DataResult(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }

    public DataResult(ResponseEnum responseEnum, T t) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
        this.data = t;
    }

    public DataResult(DataResult dataResult, T t) {
        this.code = dataResult.getCode();
        this.message = dataResult.getMessage();
        this.data = t;
    }

    public static DataResult<Void> error(){
        return new DataResult<>(ResponseEnum.SYSTEM_ERROR);
    }
    public static DataResult<Void> success(){
        return new DataResult<>(ResponseEnum.SYSTEM_ERROR);
    }


}
